package com.test.bankingsystem;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author shavone
 */
@SpringBootApplication
public class BankingSystem {
    private static final Logger LOG = LogManager.getLogger(BankingSystem.class);
    
    public static void main(String[] args) {
        SpringApplication.run(BankingSystem.class, args);
    }
    
}
