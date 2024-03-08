/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.bankingsystem.Service;

import com.test.bankingsystem.DTO.AccountDTO;
import com.test.bankingsystem.Model.Account;
import com.test.bankingsystem.Repository.BankingSystemAccountRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author shavone
 */
@Service
public class BankingSystemAccountServiceImpl implements BankingSystemAccountService {
    
    private BankingSystemAccountRepository bankAccountRepo;
    
    private static final long MIN_ACCOUNT_NUMBER = 10000000L; // Minimum 8-digit number
    private static final long MAX_ACCOUNT_NUMBER = 99999999L; // Maximum 8-digit number
    
    @Autowired
    public BankingSystemAccountServiceImpl(BankingSystemAccountRepository repo) {
        bankAccountRepo = repo;
    }

    
    @Override
    @Transactional
    public List<Account> getAllAccounts(int id) {
        return bankAccountRepo.getAllAccounts(id);
    }
    
    @Override
    @Transactional
    public Account findByAccId(Long id) {
        return bankAccountRepo.findByAccId(id);
    }
    
    @Override
    @Transactional
    public Account createAcc(int id) {
            
        Long accountId = generateAccountNumber();
        System.out.println("Cust id -> "+id);
        
        Account account = new Account();
        account.setId(accountId);
        account.setCustid(id);
        account.setStatus(1);
        account.setAmount(0.00);
        return bankAccountRepo.createAcc(id, account);
    }
    
    @Override
    @Transactional
    public Account updateAcc(Long id, Account account) {
        return bankAccountRepo.updateAcc(id, account);
    }

    @Override
    @Transactional
    public void deleteAcc(Long id) {
        bankAccountRepo.deleteAcc(id);
    }
    
    @Override
    public AccountDTO toAccDTO(Account acc){
        AccountDTO dto = new AccountDTO();
        dto.setId(acc.getId());
        dto.setCustid(acc.getCustid());
        dto.setStatus(acc.getStatus() == 1 ? "Open": acc.getStatus()== 2 ? "Closed": "");
        dto.setAmount(acc.getAmount());
        return dto;
    }
    
    // Generate a random 8-digit account number
    public static Long generateAccountNumber() {
        Random random = new Random();
        Long math = MIN_ACCOUNT_NUMBER + random.nextLong() % (MAX_ACCOUNT_NUMBER - MIN_ACCOUNT_NUMBER + 1);
        return Math.abs(math);
    }
}
