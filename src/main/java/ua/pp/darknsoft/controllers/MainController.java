package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.pp.darknsoft.services.AppUserService;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    AppUserService appUserService;

    @GetMapping(value = "/login")
    public String login(Model dasModel) {
        dasModel.addAttribute("headers", "no_auth");
        dasModel.addAttribute("bodies", "login");
        return "index";
    }

    @GetMapping(value = {"/registration", "/reg"})
    public String registration(Model dasModel) {

        dasModel.addAttribute("headers", "no_auth");
        dasModel.addAttribute("bodies", "registration");
        return "index";
    }

    @GetMapping(value = {"/index", "/"})
    public String index(Model dasModel, Principal principal, Authentication authentication) {
        String headers = "no_auth";
        String bodies = "no_auth";

        if (principal != null) {
            System.out.println("UserName: " + principal.getName());
            headers = "head_user";
        }

        if (authentication != null) {
            System.out.println("authentication.getCredentials(): ");
            System.out.println(authentication.getCredentials());
            if(authentication.getAuthorities().iterator().hasNext()){
                System.out.println("authentication.getAuthorities().iterator(): ");
                System.out.println(authentication.getAuthorities().iterator().next());
            }
            System.out.println("authentication.getDetails(): ");
            System.out.println(authentication.getDetails());
            System.out.println("authentication.getPrincipal(): ");
            System.out.println(authentication.getPrincipal());
            System.out.println("authentication.isAuthenticated(): ");
            System.out.println(authentication.isAuthenticated());

            if (authentication.getAuthorities()
                    .contains(new SimpleGrantedAuthority("ROLE_USER"))) {
                headers = "head_user";
                bodies = "body_user";
                System.out.println("ROLE_USER - " + authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority("ROLE_USER")));
            }
            if (authentication.getAuthorities()
                    .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                headers = "head_admin";
                bodies = "body_admin";
                System.out.println("ROLE_ADMIN - " + authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
            }
        }


        dasModel.addAttribute("headers", headers);
        dasModel.addAttribute("bodies", bodies);
        return "index";
    }

    @GetMapping(value = "/userPage")
    public String userPage(Model dasModel) {
        dasModel.addAttribute("headers", "head_user");
        dasModel.addAttribute("bodies", "body_user");
        return "index";
    }

    @GetMapping(value = "/adminPage")
    public String adminPage(Model dasModel) {
        dasModel.addAttribute("headers", "head_admin");
        dasModel.addAttribute("bodies", "body_admin");
        return "index";
    }

    @GetMapping(value = "/403")
    public String error403(Model dasModel) {
        dasModel.addAttribute("headers", "head_403");
        dasModel.addAttribute("bodies", "body_403");
        return "index";
    }
}
