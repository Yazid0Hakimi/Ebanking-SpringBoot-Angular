package org.example.backendebanking.dtos;

import lombok.Data;
import org.example.backendebanking.enums.AccountStatus;

import java.util.Date;

@Data
public class AccountDTO {
    private String id;
    private double balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private String type;
}
