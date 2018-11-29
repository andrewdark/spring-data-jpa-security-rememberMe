package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.pp.darknsoft.services.AppUserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AppUserService appUserService;

    @GetMapping(value = "/users")
    public String showUser(Pageable page, Model dasModel) {
        dasModel.addAttribute("allUsers",appUserService.getAll(page));
        dasModel.addAttribute("headers", "role_admin");
        dasModel.addAttribute("bodies", "user_list_paginator");
        return "adminPage";
    }
    @GetMapping(value = "/disabledusers")
    public String showDisabledUser(Pageable page, Model dasModel) {
        dasModel.addAttribute("allUsers",appUserService.getAllDisabled(page));
        dasModel.addAttribute("headers", "role_admin");
        dasModel.addAttribute("bodies", "user_list_paginator");
        return "adminPage";
    }
}
