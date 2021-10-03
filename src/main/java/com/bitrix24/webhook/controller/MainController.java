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
    public ModelAndView statementsGet(Model model, MainServiceImpl mainService) throws JsonProcessingException {
        model.addAttribute("entity", mainService.deserialization(mainService.findOne(getId())));
        return new ModelAndView("statements");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @GetMapping(value = "/list-of-statements")
    public ModelAndView listOfStatements(Model model, MainServiceImpl mainService) throws JsonProcessingException {
        model.addAttribute("list", mainService.deserialization(mainService.findAll()));
        return new ModelAndView("listOfStatements");
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'BOSS')")
    @PostMapping(value = "/list-of-statements", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView listOfStatements(@RequestBody String id) {
        String[] arrayId = id.split("=");
        setId(arrayId[1]);
        return new ModelAndView("redirect:/statements");
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
        mainService.changeStatusToAcceptOrCancel(arrayId[1]);
        model.addAttribute("list", mainService.deserialization(mainService.findAll()));
        return new ModelAndView("redirect:/list-of-statements");
    }

    private String resultId;

    public void setId(String id) {
        resultId = id;
    }

    public String getId() {
        return resultId;
    }
}
