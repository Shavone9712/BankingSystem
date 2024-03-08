package com.test.bankingsystem.Service;

import com.test.bankingsystem.DTO.AccountDTO;
import com.test.bankingsystem.DTO.CustomerDTO;
import com.test.bankingsystem.Model.Account;
import com.test.bankingsystem.Model.Customer;
import java.util.List;

public interface BankingSystemCustomerService {
    public List<Customer> getAllCustomers();
    
    public Customer findByCustId(int id);
    public Customer createCust(Customer customer);
    public CustomerDTO toCustDTO(Customer cust);
}
