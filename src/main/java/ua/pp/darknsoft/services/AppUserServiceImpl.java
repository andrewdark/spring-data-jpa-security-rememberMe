package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<AppUser> findAll() {
        return userRepository.findAll();
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
