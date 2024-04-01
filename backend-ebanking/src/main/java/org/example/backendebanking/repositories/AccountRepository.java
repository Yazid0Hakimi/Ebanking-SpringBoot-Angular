package org.example.backendebanking.repositories;

import org.example.backendebanking.entities.Account;
import org.example.backendebanking.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
}
