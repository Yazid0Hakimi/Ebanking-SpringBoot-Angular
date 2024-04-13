package org.example.backendebanking.usersConfig.service;

import org.example.backendebanking.usersConfig.entities.AppRole;
import org.example.backendebanking.usersConfig.entities.AppUser;

import java.util.List;

public interface SecurityAccountService {
    AppUser addNewUser(AppUser appUser);

    AppRole addNewRole(AppRole appRole);

    void addRoleToUser(String username, String roleName);

    AppUser userByUsername(String username);

    List<AppUser> listUsers();
}
