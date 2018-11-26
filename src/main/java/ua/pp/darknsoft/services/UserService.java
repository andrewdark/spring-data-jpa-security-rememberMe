package ua.pp.darknsoft.services;

import ua.pp.darknsoft.models.AppUser;

import java.util.List;

public interface UserService {

    List<AppUser> findAll();
}
