package com.bitrix24.webhook.controller;

import com.bitrix24.webhook.service.MainService;
import com.bitrix24.webhook.service.MainServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/*")
@RequiredArgsConstructor
public class MainController {

    @Autowired
    final MainService mainService = new MainServiceImpl();

    @ResponseBody
    @GetMapping(value = "/find-all")
    public ResponseEntity<String> findAll(MainServiceImpl mainService) throws JsonProcessingException {
        return new ResponseEntity<>(mainService.findAll(), HttpStatus.OK);
    }
}
