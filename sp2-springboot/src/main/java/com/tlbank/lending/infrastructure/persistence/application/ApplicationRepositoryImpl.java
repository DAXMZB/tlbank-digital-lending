package com.tlbank.lending.infrastructure.persistence.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tlbank.lending.domain.application.Address;
import com.tlbank.lending.domain.application.Applicant;
import com.tlbank.lending.domain.application.Application;
import com.tlbank.lending.domain.application.ApplicationId;
import com.tlbank.lending.domain.application.ApplicationStatus;
import com.tlbank.lending.domain.application.CardProductId;
import com.tlbank.lending.domain.application.DocumentInfo;
import com.tlbank.lending.domain.application.Email;
import com.tlbank.lending.domain.application.MobileNumber;
import com.tlbank.lending.domain.application.WorkflowHistory;
import com.tlbank.lending.domain.application.repository.ApplicationRepository;
import com.tlbank.lending.infrastructure.persistence.product.CardProductEntity;
import com.tlbank.lending.infrastructure.persistence.product.CardProductJpaRepository;

import lombok.RequiredArgsConstructor;

/**
 * JPA-backed implementation of the {@link ApplicationRepository} domain port.
 */
@Repository
@RequiredArgsConstructor
public class ApplicationRepositoryImpl implements ApplicationRepository {

    private final ApplicationJpaRepository applicationJpaRepository;
    private final CardProductJpaRepository cardProductJpaRepository;

    @Override
    public Optional<Application> findById(ApplicationId id) {
        return applicationJpaRepository.findByApplicationNo(id.value()).map(this::toDomain);
    }

    @Override
    public Application save(Application application) {
        ApplicationEntity entity = applicationJpaRepository.findByApplicationNo(application.getApplicationId().value())
                .map(existing -> {
                    applyToEntity(existing, application);
                    return existing;
                })
                .orElseGet(() -> toEntity(application));

        return toDomain(applicationJpaRepository.save(entity));
    }

    @Override
    public List<Application> findByStatus(ApplicationStatus status) {
        return applicationJpaRepository.findByStatus(status).stream()
                .map(this::toDomain)
                .toList();
    }

    private Application toDomain(ApplicationEntity entity) {
        CardProductId cardProductId = resolveCardProductId(entity.getProductId());

        return Application.builder()
                .applicationId(ApplicationId.of(entity.getApplicationNo()))
                .applicant(toApplicant(entity))
                .cardProductId(cardProductId)
                .status(entity.getStatus())
                .workflowHistories(new ArrayList<>(entity.getWorkflowHistories().stream()
                        .map(history -> WorkflowHistory.builder()
                                .historyId(history.getId())
                                .applicationId(ApplicationId.of(entity.getApplicationNo()))
                                .fromStatus(history.getFromStatus())
                                .toStatus(history.getToStatus())
                                .operator(history.getOperator())
                                .remark(history.getComment())
                                .operatedAt(history.getActionAt())
                                .build())
                        .toList()))
                .documentInfos(new ArrayList<>(entity.getDocuments().stream()
                        .map(doc -> new DocumentInfo(
                                doc.getDocumentType(),
                                doc.getFileName(),
                                doc.getFilePath(),
                                doc.getFileSize(),
                                doc.getUploadedAt()))
                        .toList()))
                .submittedAt(entity.getSubmittedAt())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private Applicant toApplicant(ApplicationEntity entity) {
        ApplicantEmbeddable applicant = entity.getApplicant();
        AddressEmbeddable address = entity.getAddress();

        if (applicant == null || address == null) {
            return null;
        }

        return new Applicant(
                applicant.getFullName(),
                applicant.getNationalId(),
                MobileNumber.of(applicant.getMobile()),
                Email.of(applicant.getEmail()),
                applicant.getDateOfBirth(),
                new Address(address.getCity(), address.getDistrict(), address.getStreet(), address.getZipCode())
        );
    }

    private ApplicationEntity toEntity(Application application) {
        Long productId = resolveProductId(application.getCardProductId());

        ApplicationEntity entity = ApplicationEntity.builder()
                .applicationNo(application.getApplicationId().value())
                .productId(productId)
                .status(application.getStatus())
                .submittedAt(application.getSubmittedAt())
                .applicant(toApplicantEmbeddable(application.getApplicant()))
                .address(toAddressEmbeddable(application.getApplicant()))
                .workflowHistories(new ArrayList<>())
                .documents(new ArrayList<>())
                .build();

        syncWorkflowHistories(entity, application);
        syncDocuments(entity, application);
        return entity;
    }

    private void applyToEntity(ApplicationEntity entity, Application application) {
        entity.setProductId(resolveProductId(application.getCardProductId()));
        entity.setStatus(application.getStatus());
        entity.setSubmittedAt(application.getSubmittedAt());
        entity.setApplicant(toApplicantEmbeddable(application.getApplicant()));
        entity.setAddress(toAddressEmbeddable(application.getApplicant()));
        syncWorkflowHistories(entity, application);
        syncDocuments(entity, application);
    }

    private void syncWorkflowHistories(ApplicationEntity entity, Application application) {
        List<WorkflowHistoryEntity> existing = entity.getWorkflowHistories();
        int existingCount = existing.size();
        List<WorkflowHistory> domainHistories = application.getWorkflowHistories();

        for (int i = existingCount; i < domainHistories.size(); i++) {
            WorkflowHistory history = domainHistories.get(i);
            existing.add(WorkflowHistoryEntity.builder()
                    .application(entity)
                    .fromStatus(history.getFromStatus())
                    .toStatus(history.getToStatus())
                    .operator(history.getOperator())
                    .comment(history.getRemark())
                    .actionAt(history.getOperatedAt())
                    .build());
        }
    }

    private void syncDocuments(ApplicationEntity entity, Application application) {
        List<ApplicationDocumentEntity> existing = entity.getDocuments();
        int existingCount = existing.size();
        List<DocumentInfo> domainDocuments = application.getDocumentInfos();

        for (int i = existingCount; i < domainDocuments.size(); i++) {
            DocumentInfo doc = domainDocuments.get(i);
            existing.add(ApplicationDocumentEntity.builder()
                    .application(entity)
                    .documentType(doc.documentType())
                    .fileName(doc.fileName())
                    .filePath(doc.storagePath())
                    .fileSize(doc.fileSize())
                    .uploadedAt(doc.uploadedAt())
                    .build());
        }
    }

    private ApplicantEmbeddable toApplicantEmbeddable(Applicant applicant) {
        if (applicant == null) {
            return null;
        }
        return ApplicantEmbeddable.builder()
                .fullName(applicant.fullName())
                .nationalId(applicant.nationalId())
                .mobile(applicant.mobile().value())
                .email(applicant.email().value())
                .dateOfBirth(applicant.dateOfBirth())
                .build();
    }

    private AddressEmbeddable toAddressEmbeddable(Applicant applicant) {
        if (applicant == null) {
            return null;
        }
        Address address = applicant.address();
        return AddressEmbeddable.builder()
                .city(address.city())
                .district(address.district())
                .street(address.street())
                .zipCode(address.zipCode())
                .build();
    }

    private Long resolveProductId(CardProductId cardProductId) {
        return cardProductJpaRepository.findByProductCode(cardProductId.value())
                .map(CardProductEntity::getId)
                .orElseGet(() -> {
                    try {
                        long numericId = Long.parseLong(cardProductId.value());
                        return cardProductJpaRepository.findById(numericId)
                                .map(CardProductEntity::getId)
                                .orElseThrow(() -> new IllegalArgumentException(
                                        "Card product not found: " + cardProductId.value()));
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Card product not found: " + cardProductId.value());
                    }
                });
    }

    private CardProductId resolveCardProductId(Long productId) {
        return cardProductJpaRepository.findById(productId)
                .map(entity -> CardProductId.of(entity.getProductCode()))
                .orElse(CardProductId.of(String.valueOf(productId)));
    }
}
