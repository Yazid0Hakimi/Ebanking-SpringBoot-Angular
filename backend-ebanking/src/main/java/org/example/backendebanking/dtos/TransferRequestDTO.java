package org.example.backendebanking.dtos;

import lombok.Data;
import org.example.backendebanking.enums.AccountStatus;

import java.util.Date;

@Data
public class TransferRequestDTO {
    private String accountSource;
    private String accountDestination;
    private double amount;
    private String description;
}
