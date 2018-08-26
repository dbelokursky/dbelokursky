package ru.job4j.carssale.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.job4j.carssale.repositories.OwnerRepository;

@Controller
public class OwnerController {

    OwnerRepository ownerRepository;

    @Autowired
    public void setOwnerRepository(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @RequestMapping(value = "/ownerslist", method = RequestMethod.GET)
    public String getOwnersList(ModelMap modelMap) {
        modelMap.addAttribute("owners", ownerRepository.findAll());
        return "OwnersList";
    }

    @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
    public String getAccessDeniedPage() {
        return "AccessDenied";
    }
}
