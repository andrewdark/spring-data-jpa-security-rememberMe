package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.services.AppUserService;

@Controller
public class MainController {

    @Autowired
    AppUserService appUserService;

    @GetMapping(value = "login")
    public String login(Model dasModel) {
        return "login";
    }

    @GetMapping(value = {"index", "/"})
    public String index(Model dasModel) {

        dasModel.addAttribute("users", appUserService.findAll());
        return "index";
    }


}
