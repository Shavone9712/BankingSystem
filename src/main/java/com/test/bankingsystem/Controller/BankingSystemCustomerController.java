package com.test.bankingsystem.Controller;

import com.test.bankingsystem.DTO.AccountDTO;
import com.test.bankingsystem.DTO.CustomerDTO;
import com.test.bankingsystem.Model.Account;
import com.test.bankingsystem.Model.Customer;
import com.test.bankingsystem.Service.BankingSystemAccountService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.test.bankingsystem.Service.BankingSystemCustomerService;

/**
 *
 * @author shavone
 */
@RestController
public class BankingSystemCustomerController {
    @Autowired
    BankingSystemCustomerService service;
    @Autowired
    BankingSystemAccountService accountService;
        
    @GetMapping(value="/getAllCustomers")
    public List<CustomerDTO> getAllCustomers(){
        CustomerDTO dto = new CustomerDTO();
        List<CustomerDTO> custList = new ArrayList<>();
        List<Customer> customers = service.getAllCustomers();
        for(Customer c : customers){
            dto = service.toCustDTO(c);
            AccountDTO accDTO = new AccountDTO();
            List<AccountDTO> dtoList = new ArrayList<>();
            List<Account> accList = accountService.getAllAccounts(c.getId());
            for(Account list : accList){
                accDTO = accountService.toAccDTO(list);
                dtoList.add(accDTO);
            }
            dto.setAccountList(dtoList);
            custList.add(dto);
        }
        return custList;
    }
    
    @PostMapping(value="/newCustomer")
    public Customer newCustomer(@RequestBody Customer customer) {        
        customer = service.createCust(customer);
        return customer;
    }
    
    @GetMapping(value="/getCustomer/{id}")
    public CustomerDTO getCustomer(@PathVariable int id) {        
        Customer cust = service.findByCustId(id);
        CustomerDTO dto = service.toCustDTO(cust);
        AccountDTO accDTO = new AccountDTO();
        List<AccountDTO> dtoList = new ArrayList<>();
        List<Account> accList = accountService.getAllAccounts(cust.getId());
        for(Account list : accList){
            accDTO = accountService.toAccDTO(list);
            dtoList.add(accDTO);
        }
        dto.setAccountList(dtoList);
        return dto;
    }
    
    
}
