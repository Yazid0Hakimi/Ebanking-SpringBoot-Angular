package org.example.backendebanking;

import org.example.backendebanking.entities.*;
import org.example.backendebanking.exception.AccountNotFoundException;
import org.example.backendebanking.exception.BalanceNotSufficientException;
import org.example.backendebanking.exception.CustomerNotFoundException;
import org.example.backendebanking.mappers.AccountMapperImpl;
import org.example.backendebanking.usersConfig.entities.AppRole;
import org.example.backendebanking.usersConfig.entities.AppUser;
import org.example.backendebanking.usersConfig.service.SecurityAccountService;
import org.example.backendebanking.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendEbankingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendEbankingApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(SecurityAccountService securityAccountService, AccountService accountService, AccountMapperImpl accountMapperImpl) {
        return args -> {
            Stream.of("USER",
                    "ADMIN",
                    "MANAGER",
                    "CUSTOMER_MANAGER",
                    "PRODUCT_MANAGER"
            ).forEach(roleName -> {
                AppRole role = new AppRole();
                role.setRoleName(roleName);
                securityAccountService.addNewRole(role);
            });

            Stream.of("user1",
                    "user2",
                    "Admin1",
                    "Admin2",
                    "manager1",
                    "manager2"
            ).forEach(userName -> {
                AppUser user = new AppUser();
                user.setUsername(userName);
                user.setPassword("1234");
                user.setRoles(new ArrayList<>());

                securityAccountService.addNewUser(user);
            });

            Stream.of("Ahmed", "Mohamed", "Ali").forEach(cust -> {
                Customer customer = new Customer();
                customer.setName(cust);
                customer.setEmail(cust + "@gmail.com");
                accountService.saveCustomer(accountMapperImpl.customerToCustomerDTO(customer));
            });

            accountService.listCustomers().forEach(cust -> {
                try {
                    accountService.saveCurrentAccount(1000, cust.getId(), 1000);
                    accountService.saveSavingAccount(1000, cust.getId(), 3.2);
                    accountService.getAccounts().forEach(account -> {
                                try {
                                    accountService.credit(account.getId(), 10000 * Math.random() * 8000, "Initial balance");
                                    accountService.debit(account.getId(), 84530, "Initial balance");
                                } catch (AccountNotFoundException e) {
                                    e.printStackTrace();
                                } catch (BalanceNotSufficientException e) {
                                    e.printStackTrace();
                                }
                            }
                    );
                } catch (CustomerNotFoundException e) {
                    e.printStackTrace();
                }
            });

        };
    }

}
