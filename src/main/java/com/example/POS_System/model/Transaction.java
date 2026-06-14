package com.example.POS_System.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("transactionId")
    @Column(name = "transaction_id", unique = true, nullable = false)
    private String transactionId;

    @JsonProperty("customerName")
    @Column(name = "customer_name")
    private String customerName;

    @JsonProperty("paymentMethod")
    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @JsonProperty("subtotal")
    private double subtotal;
    
    @JsonProperty("vatTax")
    @Column(name = "vat_tax")
    private double vatTax;
    
    @JsonProperty("grandTotal")
    @Column(name = "grand_total")
    private double grandTotal;

    @JsonProperty("createdAt")
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @JsonProperty("cartItems")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    private List<TransactionItem> cartItems;


    public Transaction() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getVatTax() { return vatTax; }
    public void setVatTax(double vatTax) { this.vatTax = vatTax; }

    public double getGrandTotal() { return grandTotal; }
    public void setGrandTotal(double grandTotal) { this.grandTotal = grandTotal; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public List<TransactionItem> getCartItems() { return cartItems; }
    public void setCartItems(List<TransactionItem> cartItems) { this.cartItems = cartItems; }
}