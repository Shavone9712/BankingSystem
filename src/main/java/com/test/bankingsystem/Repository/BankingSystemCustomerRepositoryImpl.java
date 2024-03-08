package com.test.bankingsystem.Repository;

import com.test.bankingsystem.Model.Account;
import com.test.bankingsystem.Model.Customer;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BankingSystemCustomerRepositoryImpl implements BankingSystemCustomerRepository {
    private EntityManager entityManager;
    
    @Autowired
    public BankingSystemCustomerRepositoryImpl(EntityManager theEntityManger) {
        entityManager = theEntityManger;
    }
    
    @Override
    public List<Customer> getAllCustomers() {

        // get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        // create a query
        Query<Customer> theQuery
                = currentSession.createQuery("from Customer", Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }
    
    @Override
    public Customer findByCustId(int id) {

        Session currentSession = entityManager.unwrap(Session.class);

        Customer customer = currentSession.get(Customer.class, id);

        return customer;
    }
    
    @Override
    public Customer createCust(Customer customer) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.save(customer);        
        
        return customer;
    }
    
    
}
