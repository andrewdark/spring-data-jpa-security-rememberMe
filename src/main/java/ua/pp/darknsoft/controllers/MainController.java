package ua.pp.darknsoft.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.darknsoft.models.AppUser;
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
        dasModel.addAttribute("appUser", new AppUser());
        dasModel.addAttribute("headers", "no_auth");
        dasModel.addAttribute("bodies", "registration");
        return "index";
    }

    @PostMapping(value = "/registration")
    public String saveRegister(Model model, //
                               @ModelAttribute("appUser") @Validated AppUser appUserForm, //
                               BindingResult result, //
                               final RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("headers", "no_auth");
            model.addAttribute("bodies", "registration");
            return "index";
        }
        AppUser newUser = null;

        if (appUserService.isExists(appUserForm)) {
            model.addAttribute("errorMessage", "Error: " + "User " + appUserForm.getUserName() + " is exist");
            model.addAttribute("headers", "no_auth");
            model.addAttribute("bodies", "registration");
            return "index";
        }

        try {
            newUser = appUserService.createAppUser(appUserForm);
        }
        // Other error!!
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            model.addAttribute("headers", "no_auth");
            model.addAttribute("bodies", "registration");
            return "index";
        }

        redirectAttributes.addFlashAttribute("flashUser", newUser);

        return "redirect:/registerSuccessful";
    }

    @GetMapping(value = "/registerSuccessful")
    public String registerSuccessful(Model dasModel) {
        dasModel.addAttribute("headers", "no_auth");
        dasModel.addAttribute("bodies", "registerSuccessful");
        return "index";
    }

    @GetMapping(value = {"/index", "/"})
    public String index(Model dasModel, Principal principal, Authentication authentication) {
        String headers = "no_auth";
        String bodies = "no_auth";

        if (principal != null) {
            System.out.println("UserName: " + principal.getName());
            headers = "role_user";
            bodies = "auth";
        }

        if (authentication != null) {
            System.out.println("authentication.getCredentials(): ");
            System.out.println(authentication.getCredentials());
            if (authentication.getAuthorities().iterator().hasNext()) {
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
                headers = "role_user";
                bodies = "role_user";
                System.out.println("ROLE_USER - " + authentication.getAuthorities()
                        .contains(new SimpleGrantedAuthority("ROLE_USER")));
            }
            if (authentication.getAuthorities()
                    .contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                headers = "role_admin";
                bodies = "role_admin";
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
        dasModel.addAttribute("headers", "role_user");
        dasModel.addAttribute("bodies", "role_user");
        return "index";
    }

    @GetMapping(value = "/adminPage")
    public String adminPage(Model dasModel) {
        dasModel.addAttribute("headers", "role_admin");
        dasModel.addAttribute("bodies", "role_admin");
        return "index";
    }

    @GetMapping(value = "/403")
    public String error403(Model dasModel) {
        dasModel.addAttribute("headers", "error_403");
        dasModel.addAttribute("bodies", "error_403");
        return "index";
    }
}
