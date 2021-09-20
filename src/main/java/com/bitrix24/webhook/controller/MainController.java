package com.bitrix24.webhook.controller;

import com.bitrix24.webhook.entity.OwnResponseEntity;
import com.bitrix24.webhook.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/*")
@RequiredArgsConstructor
public class MainController {

    @ResponseBody
    @PostMapping(value = "/find-all", consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<OwnResponseEntity> findAll(@RequestBody MainService mainService) {
        return new ResponseEntity<>(mainService.findAll(), HttpStatus.OK);
    }
}
