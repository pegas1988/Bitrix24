package com.bitrix24.webhook.controller;

import com.bitrix24.webhook.service.MainServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    @Autowired
    final MainServiceImpl mainService;

    @GetMapping(value = "/main")
    public String findAll() {
        return "main";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @GetMapping(value = "/list-of-statements")
    public String listOfStatements(HashMap<String, Object> model, MainServiceImpl mainService) throws JsonProcessingException {
        model.put("list", mainService.deserialization(mainService.findAll()));
        return "statementsList";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @PostMapping(value = "/list-of-statements")
    public String listOfStatements(@RequestBody String id) {
        String[] arrayId = id.split("=");
        setId(arrayId[1]);
        return "redirect:/statement";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @GetMapping(value = "/order")
    public String orders() {
        return "order";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @PostMapping(value = "/statement")
    public String statementsPost(@RequestBody String id, String comment, MainServiceImpl mainService) {
        String[] arrayId = id.split("=");
        mainService.changeStatusToAcceptOrCancel(arrayId[2], comment);
        return "redirect:/list-of-statements";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @GetMapping(value = "/statement")
    public String statements(HashMap<String, Object> model, MainServiceImpl mainService) throws JsonProcessingException {
        model.put("user", mainService.deserialization(mainService.findOne(getId())));
        return "statement";
    }

    private String id;

    private void setId(String id) {
        this.id = id;
    }

    private String getId() {
        return id;
    }
}
