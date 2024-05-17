package org.example.backendebanking.web;

import lombok.AllArgsConstructor;
import org.example.backendebanking.dtos.CustomerAccountsDTO;
import org.example.backendebanking.dtos.CustomerDTO;
import org.example.backendebanking.entities.Customer;
import org.example.backendebanking.exception.CustomerNotFoundException;
import org.example.backendebanking.services.AccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
@CrossOrigin("*")
public class CustomerRestController {
    private AccountService accountService;

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDTO> listCustomers() {
        return accountService.listCustomers();
    }

    @GetMapping("/customers/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")

    public List<CustomerDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
        return accountService.searchCustomers(keyword);
    }

    @GetMapping("/customers/{id}")
    @PreAuthorize("hasAuthority('SCOPE_USER')")

    public CustomerDTO getCustomer(@PathVariable(name = "id") Long id) throws CustomerNotFoundException {
        return accountService.getCustomer(id);
    }

    @PostMapping("/customer")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")

    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return accountService.saveCustomer(customerDTO);
    }

    @PutMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")

    public CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody CustomerDTO customerDTO) {
        customerDTO.setId(customerId);
        return accountService.saveCustomer(customerDTO);
    }

    @DeleteMapping("/customer/{customerId}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")

    public void deleteCustomer(@PathVariable Long customerId) {
        accountService.deleteCustomer(customerId);
    }

    @GetMapping("/customers/accounts/{id}")
    public List<CustomerAccountsDTO> getCustomerAccounts(@PathVariable(name = "id") Long id,
                                                         @RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "5") int size) throws CustomerNotFoundException {
        return accountService.customerAccounts(id, page, size);
    }
}
