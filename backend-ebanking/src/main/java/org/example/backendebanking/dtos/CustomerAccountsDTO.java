package org.example.backendebanking.dtos;

import lombok.Data;
import org.example.backendebanking.enums.AccountStatus;
import org.example.backendebanking.enums.OperationType;

@Data
public class CustomerAccountsDTO {
    private Long customer_id;
    private String id;
    private double balance;
    private AccountStatus status;
    private String type;
}
