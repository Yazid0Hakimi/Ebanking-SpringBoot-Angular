package org.example.backendebanking.dtos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.backendebanking.entities.Account;

import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;

}
