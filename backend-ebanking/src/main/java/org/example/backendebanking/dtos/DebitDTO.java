package org.example.backendebanking.dtos;

import lombok.Data;

@Data
public class DebitDTO {

    private String account;
    private double amount;
    private String description;
}
