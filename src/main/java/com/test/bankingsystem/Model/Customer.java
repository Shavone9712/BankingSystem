package com.test.bankingsystem.Model;

import com.test.bankingsystem.DTO.CustomerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;
    
    @Column(name="name")
    private String name;

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
    
    @Override
    public String toString(){
        return "Cusr ID: "+id+"\nCust name: "+name;
    }
    
    public final void init(CustomerDTO dto){
        if(dto != null){
            id = dto.getId();
            name = dto.getName();            
        }
    }
    
}
