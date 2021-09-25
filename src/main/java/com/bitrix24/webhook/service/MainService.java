package com.bitrix24.webhook.service;

import com.bitrix24.webhook.entity.OwnResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MainService {
    OwnResponseEntity findAll() throws JsonProcessingException;
}
