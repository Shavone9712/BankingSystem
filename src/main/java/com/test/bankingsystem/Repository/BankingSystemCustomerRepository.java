/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.bankingsystem.Repository;

import com.test.bankingsystem.Model.Account;
import com.test.bankingsystem.Model.Customer;
import java.util.List;

/**
 *
 * @author shavone
 */
public interface BankingSystemCustomerRepository {
    public List<Customer> getAllCustomers();
    public Customer findByCustId(int id);
    public Customer createCust(Customer customer);
}
