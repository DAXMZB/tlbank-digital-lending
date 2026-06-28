package com.tlbank.lending.domain.application.repository;

import java.util.List;
import java.util.Optional;

import com.tlbank.lending.domain.application.Application;
import com.tlbank.lending.domain.application.ApplicationId;
import com.tlbank.lending.domain.application.ApplicationStatus;

/**
 * Domain repository port for {@link Application} persistence.
 */
public interface ApplicationRepository {

    Optional<Application> findById(ApplicationId id);

    Application save(Application application);

    List<Application> findByStatus(ApplicationStatus status);
}
