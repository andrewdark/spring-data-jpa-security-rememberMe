package ua.pp.darknsoft.services;

import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.models.AppUser;

import java.util.List;

public interface AppUserService {

    List<AppUser> findAll();

    @Transactional(readOnly = true)
    Boolean isExists(AppUser appUser);

    AppUser createAppUser(AppUser appUserForm);
}
