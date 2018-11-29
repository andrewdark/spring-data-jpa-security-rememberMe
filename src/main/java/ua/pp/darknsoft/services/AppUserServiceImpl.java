package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.repositories.UserRepository;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    @Override
    public Page<AppUser> getAll(Pageable page) {
        return userRepository.findAll(page);
    }

    @Override
    public Page<AppUser> getAllEnabled(Pageable page) {
        return userRepository.findAllByEnabled(true, page);
    }

    @Override
    public Page<AppUser> getAllDisabled(Pageable page) {
        return userRepository.findAllByEnabled(false, page);
    }

    @Transactional(readOnly = true)
    @Override
    public Boolean isExists(AppUser appUser) {
        return userRepository.existsByUserName(appUser.getUserName().toLowerCase());
    }

    @Override
    public AppUser createAppUser(AppUser appUserForm) {
        AppUser savedUser = new AppUser();
        appUserForm.setUserName(appUserForm.getUserName().toLowerCase());
        appUserForm.setEncryptedPassword(bCryptPasswordEncoder.encode(appUserForm.getEncryptedPassword()));
        if (!isExists(appUserForm))
            savedUser = userRepository.save(appUserForm);

        return savedUser;
    }
}
