package ua.pp.darknsoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.pp.darknsoft.models.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
}
