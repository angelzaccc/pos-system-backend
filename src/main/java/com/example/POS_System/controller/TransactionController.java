package com.example.POS_System.controller;

import com.example.POS_System.model.Transaction;
import com.example.POS_System.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "https://pos-system-frontend-git-main-my-project-zacarias.vercel.app")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;


   @PostMapping
public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
    try {
 
        if (transaction.getSubtotal() > 0) {

            double calculatedVat = transaction.getSubtotal() * 0.12; 
            transaction.setVatTax(calculatedVat);
            
            transaction.setGrandTotal(transaction.getSubtotal() + calculatedVat);
        }

        Transaction savedTx = transactionRepository.save(transaction);
        return new ResponseEntity<>(savedTx, HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        try {
            List<Transaction> transactions = transactionRepository.findAll();
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}