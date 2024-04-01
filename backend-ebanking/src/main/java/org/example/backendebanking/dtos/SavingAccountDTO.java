package org.example.backendebanking.dtos;

import lombok.Data;
import org.example.backendebanking.enums.AccountStatus;
import java.util.Date;
@Data
public class SavingAccountDTO extends AccountDTO{
    private double interestRate;
}
