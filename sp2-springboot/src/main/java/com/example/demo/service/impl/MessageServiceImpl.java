package com.example.demo.service.impl;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ErrorMessage;
import com.example.demo.repository.ErrorMessageRepository;
import com.example.demo.service.MessageService;

import jakarta.annotation.PostConstruct;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
    private ErrorMessageRepository repository;

    private Map<String, String> messageCache;

    @Override
    @PostConstruct
    public void init() {
        refreshCache();
    }

    @Override
    public void refreshCache() {
        messageCache = repository.findAll().stream()
            .collect(Collectors.toMap(ErrorMessage::getMsgKey, ErrorMessage::getMsgContent));
    }

    @Override
    public String getMessage(String key) {
        return messageCache.getOrDefault(key, "Unknown Error: " + key);
    }
}
