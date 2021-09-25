package com.bitrix24.webhook.controller;

import com.bitrix24.webhook.service.MainServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    @Autowired
    final MainServiceImpl mainService;

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @GetMapping(value = "/main")
    public ModelAndView findAll() {
        return new ModelAndView("main");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @GetMapping(value = "/statements")
    public ModelAndView statements(Model model, MainServiceImpl mainService) throws JsonProcessingException {
        model.addAttribute("list", mainService.deserialization(mainService.findAll()));
        return new ModelAndView("statements");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @GetMapping(value = "/order")
    public ModelAndView orders() {
        return new ModelAndView("order");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @PostMapping(value = "/statements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView statements(@RequestBody String id, Model model, MainServiceImpl mainService) throws JsonProcessingException {
        String[] arrayId = id.split("=");
        mainService.changeStatusToAccept(arrayId[1]);
        model.addAttribute("list", mainService.deserialization(mainService.findAll()));
        return new ModelAndView("statements");
    }
}
