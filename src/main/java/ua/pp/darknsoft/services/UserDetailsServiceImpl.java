package ua.pp.darknsoft.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.models.AppRole;
import ua.pp.darknsoft.models.AppUser;
import ua.pp.darknsoft.models.RoledUser;
import ua.pp.darknsoft.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<AppUser> appUserOptional = userRepository.findByUserName(userName);
        if (appUserOptional.isEmpty()) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        AppUser appUser = appUserOptional.get();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<RoledUser> roledUsers = appUser.getRoledUsers();
        if (roledUsers != null && !roledUsers.isEmpty()) {
            for (RoledUser roledUser : roledUsers) {
                grantedAuthorities.add(new SimpleGrantedAuthority(roledUser.getAppRole().getRoleName()));
            }
        }

        return new org.springframework.security.core.userdetails.User(appUser.getUserName(),
                appUser.getEncryptedPassword(), grantedAuthorities);

    }
}
