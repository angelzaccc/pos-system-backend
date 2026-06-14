package com.example.POS_System.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_items")
public class TransactionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    @Column(name = "item_name", nullable = false)
    private String name;

    @JsonProperty("price")
    private double price;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("selectedCupSize")
    @Column(name = "selected_cup_size")
    private String selectedCupSize;

    @JsonProperty("selectedIceLevel")
    @Column(name = "selected_ice_level")
    private int selectedIceLevel;

    @JsonProperty("selectedSugarLevel")
    @Column(name = "selected_sugar_level")
    private int selectedSugarLevel;

    // Default Constructor
    public TransactionItem() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getSelectedCupSize() { return selectedCupSize; }
    public void setSelectedCupSize(String selectedCupSize) { this.selectedCupSize = selectedCupSize; }

    public int getSelectedIceLevel() { return selectedIceLevel; }
    public void setSelectedIceLevel(int selectedIceLevel) { this.selectedIceLevel = selectedIceLevel; }

    public int getSelectedSugarLevel() { return selectedSugarLevel; }
    public void setSelectedSugarLevel(int selectedSugarLevel) { this.selectedSugarLevel = selectedSugarLevel; }
}