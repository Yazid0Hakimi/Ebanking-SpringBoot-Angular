package org.example.backendebanking.repositories;

import org.example.backendebanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustumerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByNameContains(String Keyword);
}
