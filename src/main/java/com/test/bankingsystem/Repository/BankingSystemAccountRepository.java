/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.bankingsystem.Repository;

import com.test.bankingsystem.Model.Account;
import java.util.List;

/**
 *
 * @author shavone
 */
public interface BankingSystemAccountRepository {
    public List<Account> getAllAccounts(int id);
    public Account findByAccId(Long id);
    public Account createAcc(int id, Account account);
    public Account updateAcc(Long id, Account account);
    public void deleteAcc(Long id);
}
