package ua.pp.darknsoft.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.models.AppUser;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUserName(String userName);

    Boolean existsByUserName(String userName);

    Page<AppUser> findAllByEnabled(Boolean enabled, Pageable pageable);
}
