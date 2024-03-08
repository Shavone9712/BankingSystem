/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.bankingsystem.Repository;

import com.test.bankingsystem.Model.Account;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author shavone
 */
@Repository
public class BankingSystemAccountRepositoryImpl implements BankingSystemAccountRepository {
    
    private EntityManager entityManager;
    
    @Autowired
    public BankingSystemAccountRepositoryImpl(EntityManager theEntityManger) {
        entityManager = theEntityManger;
    }
    
    @Override
    public List<Account> getAllAccounts(int id) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Account> theQuery;
        if(id > 0){
            theQuery = currentSession.createQuery("from Account where custid = :id ", Account.class);
            theQuery.setParameter("id", id);
        }else{
            theQuery = currentSession.createQuery("from Account", Account.class);
            
        }
        // execute query and get result list
        List<Account> accounts = theQuery.getResultList();

        // return the results
        return accounts;
    }
    
    @Override
    public Account findByAccId(Long id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Account account = currentSession.get(Account.class, id);

        return account;
    }
    
    @Override
    public Account createAcc(int id, Account account) {
            
        // get the current hibernate session
        try {
            // get the current hibernate session
            Session currentSession = entityManager.unwrap(Session.class);

            currentSession.save(account);
            return account;
        } catch (Exception e) {
            // Log or handle the exception
            System.out.println(e);
            throw e;
        }
    }
    
    @Override
    public Account updateAcc(Long id, Account account) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);
        
        Account updatedAcc = this.findByAccId(id);
        if(updatedAcc == null){
            return account=null;
        }
        updatedAcc.setStatus(account.getStatus());
        updatedAcc.setAmount(account.getAmount());
        
        currentSession.saveOrUpdate(updatedAcc);
        return updatedAcc;
    }

    @Override
    public void deleteAcc(Long id) {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query theQuery
                = currentSession.createQuery(
                        "delete from Account where id = :id");
        theQuery.setParameter("id", id);

        theQuery.executeUpdate();

    }
}
