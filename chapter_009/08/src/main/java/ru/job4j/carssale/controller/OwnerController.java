package ru.job4j.carssale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.carssale.service.OwnerService;

@Controller
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public String getOwnersList(ModelMap modelMap) {
        modelMap.addAttribute("owners", ownerService.findAll());
        return "ownersList";
    }

    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String getAccessDeniedPage() {
        return "accessDenied";
    }
}
