package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.repositories.UserRepository;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }
}
