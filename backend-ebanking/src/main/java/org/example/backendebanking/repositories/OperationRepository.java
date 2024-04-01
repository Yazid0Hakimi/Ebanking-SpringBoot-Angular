package org.example.backendebanking.repositories;

import org.example.backendebanking.entities.Account;
import org.example.backendebanking.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long>{
    List<Operation> findByAccount_Id(String id);
    Page<Operation> findByAccount_Id(String id, Pageable pageable );
}