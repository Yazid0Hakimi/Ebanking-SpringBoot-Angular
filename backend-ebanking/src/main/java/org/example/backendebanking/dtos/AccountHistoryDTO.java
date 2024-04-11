package org.example.backendebanking.dtos;

import lombok.Data;

import java.util.List;
@Data
public class AccountHistoryDTO {
    private String accountId;
    private double balance;
    private int currentPage;
    private int totalePages;
    private int pageSize;
    private List<OperationDTO> operationDTOS;
}
