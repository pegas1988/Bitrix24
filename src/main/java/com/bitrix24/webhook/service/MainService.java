package com.bitrix24.webhook.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface MainService {
    String findAll() throws JsonProcessingException;
}
