package com.test.bankingsystem.DTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import java.util.List;

public class CustomerDTO {
    private Integer id;
    private String name;
    private List<AccountDTO> accountList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AccountDTO> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<AccountDTO> accountList) {
        this.accountList = accountList;
    }
    
    @Override
    public String toString(){
        return "Cusr ID: "+id+"\nCust name: "+name+"\nAccount List:"+accountList;
    }
}
