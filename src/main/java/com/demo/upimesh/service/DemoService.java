package com.demo.upimesh.service;

import com.demo.upimesh.model.Account;
import com.demo.upimesh.model.AccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DemoService {

    @Autowired
    private AccountRepository accounts;

    @PostConstruct
    public void seedAccounts() {
        if (accounts.count() == 0) {
            accounts.save(new Account("alice@demo", "Alice", new BigDecimal("5000.00")));
            accounts.save(new Account("bob@demo", "Bob", new BigDecimal("1000.00")));
            accounts.save(new Account("carol@demo", "Carol", new BigDecimal("2500.00")));
            accounts.save(new Account("dave@demo", "Dave", new BigDecimal("500.00")));
        }
    }
}