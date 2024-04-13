package org.example.backendebanking.usersConfig.service;

import org.example.backendebanking.usersConfig.entities.AppRole;
import org.example.backendebanking.usersConfig.entities.AppUser;
import org.example.backendebanking.usersConfig.repositories.AppRoleRepository;
import org.example.backendebanking.usersConfig.repositories.AppUserRepositiory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityAccountServiceImpl implements SecurityAccountService {

    private AppRoleRepository appRoleRepository;
    private AppUserRepositiory appUserRepositiory;

    public SecurityAccountServiceImpl(AppRoleRepository appRoleRepository, AppUserRepositiory appUserRepositiory) {
        this.appRoleRepository = appRoleRepository;
        this.appUserRepositiory = appUserRepositiory;
    }

    @Override
    public AppUser addNewUser(AppUser appUser) {
        return appUserRepositiory.save(appUser);
    }

    @Override
    public AppRole addNewRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser name = appUserRepositiory.findByUsername(username);
        AppRole role = appRoleRepository.findByRoleName(roleName);

        name.getRoles().add(role);
    }

    @Override
    public AppUser userByUsername(String username) {
        return appUserRepositiory.findByUsername(username);
    }

    @Override
    public List<AppUser> listUsers() {
        return appUserRepositiory.findAll();
    }
}
