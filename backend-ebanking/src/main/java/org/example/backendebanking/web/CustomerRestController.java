package org.example.backendebanking.web;

import lombok.AllArgsConstructor;
import org.example.backendebanking.dtos.CustomerDTO;
import org.example.backendebanking.entities.Customer;
import org.example.backendebanking.exception.CustomerNotFoundException;
import org.example.backendebanking.services.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerRestController {
    private AccountService accountService;

    @GetMapping("/customers")
    public List<CustomerDTO> listCustomers() {
        return accountService.listCustomers();
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword" , defaultValue = "") String keyword) {
        return accountService.searchCustomers(keyword);
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long id) throws CustomerNotFoundException {
        return accountService.getCustomer(id);
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return accountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customer/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return accountService.saveCustomer(customerDTO);
    }

    @DeleteMapping("/customer/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        accountService.deleteCustomer(customerId);
    }
}
