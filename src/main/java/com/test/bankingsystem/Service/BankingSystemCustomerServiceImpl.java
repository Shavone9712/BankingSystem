package com.test.bankingsystem.Service;

import com.test.bankingsystem.DTO.AccountDTO;
import com.test.bankingsystem.DTO.CustomerDTO;
import com.test.bankingsystem.Model.Account;
import com.test.bankingsystem.Model.Customer;
import com.test.bankingsystem.Repository.BankingSystemAccountRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.bankingsystem.Repository.BankingSystemCustomerRepository;

@Service
public class BankingSystemCustomerServiceImpl implements BankingSystemCustomerService{
    private BankingSystemCustomerRepository bankRepo;
    
    @Autowired
    public BankingSystemCustomerServiceImpl(BankingSystemCustomerRepository repo) {
        bankRepo = repo;
    }

    @Override
    @Transactional
    public List<Customer> getAllCustomers() {
        return bankRepo.getAllCustomers();
    }
        
    @Override
    @Transactional
    public Customer findByCustId(int id) {
        return bankRepo.findByCustId(id);
    }
    
    @Override
    public Customer createCust(Customer customer) {
        return bankRepo.createCust(customer);
    }

    
    @Override
    public CustomerDTO toCustDTO(Customer cust){
        CustomerDTO dto = new CustomerDTO();
        dto.setId(cust.getId());
        dto.setName(cust.getName());
        
        return dto;
    }
}
