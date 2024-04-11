package org.example.backendebanking.repositories;

import org.example.backendebanking.entities.Account;
import org.example.backendebanking.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    Page<Account> findAccountByCustomerId(Long id, Pageable pageable );

}
