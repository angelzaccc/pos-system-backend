package com.example.POS_System.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "menu_items")
@JsonIgnoreProperties(ignoreUnknown = true) // Prevents crashes if frontend sends unexpected data
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private int categoryId;

    private String name;
    private double price;

    @Column(name = "category_name") 
    private String categoryName;

    @Column(name = "image_url")     
    private String imageUrl;

  
    @Transient
    private int quantity = 1;

    @Transient
    private String selectedCupSize = "S"; 

    @Transient
    private int selectedIceLevel = 30;

    @Transient
    private int selectedSugarLevel = 30;

    // Default Constructor (Required by JPA/Jackson)
    public MenuItem() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getSelectedCupSize() { return selectedCupSize; }
    public void setSelectedCupSize(String selectedCupSize) { this.selectedCupSize = selectedCupSize; }

    public int getSelectedIceLevel() { return selectedIceLevel; }
    public void setSelectedIceLevel(int selectedIceLevel) { this.selectedIceLevel = selectedIceLevel; }

    public int getSelectedSugarLevel() { return selectedSugarLevel; }
    public void setSelectedSugarLevel(int selectedSugarLevel) { this.selectedSugarLevel = selectedSugarLevel; }
}