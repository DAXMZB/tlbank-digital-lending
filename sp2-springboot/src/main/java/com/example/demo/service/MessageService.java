package com.example.demo.service;

public interface MessageService {
	void init();
    void refreshCache();
    String getMessage(String key);
}
