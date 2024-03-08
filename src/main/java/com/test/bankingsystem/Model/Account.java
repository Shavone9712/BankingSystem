package com.test.bankingsystem.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author shavone
 */
@Entity
@Table(name="Account")
public class Account {
    @Id
    private Long id;
    
    @Column(name="custid")
    private int custid;
    
    @Column(name="status")
    private int status;
    
    @Column(name="amount")
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return "Account ID: "+id+"\nCust ID: "+custid+"\n Status: "+status+"\n Amount: RM "+amount;
    }
    
}
