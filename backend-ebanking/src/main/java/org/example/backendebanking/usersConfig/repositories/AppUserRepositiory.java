package org.example.backendebanking.usersConfig.repositories;

import org.example.backendebanking.usersConfig.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepositiory extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
