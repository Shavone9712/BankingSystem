/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.bankingsystem.Controller;

import com.test.bankingsystem.DTO.AccountDTO;
import com.test.bankingsystem.Model.Account;
import com.test.bankingsystem.Service.BankingSystemAccountService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author shavone
 */
@RestController
public class BankingSystemAccountController {
    @Autowired
    BankingSystemAccountService service;
    
    @GetMapping(value="/getAllAccounts/{custId}")
    public List<AccountDTO> getAllAccounts(@PathVariable int custId){
        AccountDTO dto = new AccountDTO();
        List<AccountDTO> accList = new ArrayList<>();
        List<Account> accounts = service.getAllAccounts(custId);
        for(Account c : accounts){
            dto = service.toAccDTO(c);
            accList.add(dto);
        }
        return accList;
    }
    
    @PostMapping(value="/newAccount/{custId}")
    public AccountDTO newAcc(@PathVariable int custId) {    
        Account account = service.createAcc(custId);
        return service.toAccDTO(account);
    }
    
    @GetMapping(value="/getAccount/{id}")
    public ResponseEntity<?> getAccount(@PathVariable Long id){
        Account acc = new Account();
        AccountDTO dto = new AccountDTO();
        if(id == null){
            return ResponseEntity.badRequest().body("Id invalid.");
        }else{
            acc = service.findByAccId(id);
            if(acc == null){
                return ResponseEntity.ofNullable("Account not found. Please check again your account ID.");
            }else{
                dto = service.toAccDTO(acc);
                return ResponseEntity.ok(dto);
            }
        }
    }
        
    @PatchMapping(value = "/deposit/{id}")
    public ResponseEntity<?> deposit(@PathVariable Long id, @RequestBody Account account) {                
        Account acc = new Account();
        if(id == null){
            return ResponseEntity.badRequest().body("Id invalid.");
        }else{
            acc = service.findByAccId(id);
            if(acc == null){
                return ResponseEntity.ofNullable("Account not found. Please check again your account ID.");
            }
        }
        if(account != null && account.getAmount() != null) {
            Double totalAmount = acc.getAmount() + account.getAmount();
            acc.setAmount(totalAmount);
            acc = service.updateAcc(acc.getId(), acc);            
            return ResponseEntity.ok(service.toAccDTO(acc));
        }else{
            return ResponseEntity.badRequest().body("Please enter an amount for deposit. ");
        }
    }
    
    @PatchMapping(value = "/withdraw/{id}")
    public ResponseEntity<?> withdraw(@PathVariable Long id, @RequestBody Account account) {        
        Account acc = new Account();
        if(id == null){
            return ResponseEntity.badRequest().body("Id invalid.");
        }else{
            acc = service.findByAccId(id);
            if(acc == null){
                return ResponseEntity.ofNullable("Account not found. Please check again your account ID.");
            }
        }
        if(account != null && account.getAmount() != null) {
            if(acc.getAmount()>=account.getAmount()){
                Double totalAmount = acc.getAmount() - account.getAmount();
                acc.setAmount(totalAmount);
                acc = service.updateAcc(acc.getId(), acc);
                return ResponseEntity.ok(service.toAccDTO(acc));
            }else{
                return ResponseEntity.badRequest().body("Insufficient balance");
            }            
        }else{
            return ResponseEntity.badRequest().body("Please enter an amount for deposit. ");
        }
    }
    
    @PatchMapping(value = "/closeAccount/{id}")
    public ResponseEntity<?> closeAcc(@PathVariable Long id) {        
        Account acc = new Account();
        if(id == null){
            return ResponseEntity.badRequest().body("Id invalid.");
        }else{
            acc = service.findByAccId(id);
            if(acc == null){
                return ResponseEntity.ofNullable("Account not found. Please check again your account ID.");
            }else{
                acc.setStatus(2);
                acc = service.updateAcc(acc.getId(), acc);                
            }
        }
        
        return ResponseEntity.ok("Account closed");
    }
    
    
}
