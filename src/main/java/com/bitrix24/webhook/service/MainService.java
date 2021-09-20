package com.bitrix24.webhook.service;

import com.bitrix24.webhook.entity.MainEntity;
import com.bitrix24.webhook.entity.OwnResponseEntity;

import java.util.List;

public interface MainService {
    OwnResponseEntity findAll();
}
