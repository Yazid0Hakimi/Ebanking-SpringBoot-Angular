package org.example.backendebanking.usersConfig.repositories;

import org.example.backendebanking.usersConfig.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName(String roleName);
}
