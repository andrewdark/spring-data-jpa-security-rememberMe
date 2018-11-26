package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.repositories.UserRepository;
import ua.pp.darknsoft.services.UserService;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @GetMapping(value = {"index", "/"})
    public String index(Model dasModel) {

        dasModel.addAttribute("users", userService.findAll());
        return "index";
    }
}
