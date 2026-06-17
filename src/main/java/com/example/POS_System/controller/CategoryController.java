package com.example.POS_System.controller;

import com.example.POS_System.model.MenuItem;
import com.example.POS_System.repository.MenuItemRepository;
import com.example.POS_System.model.Category;
import com.example.POS_System.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("/api")
// 🚀 Wildcard allows your live Vercel domains to fetch data securely without cors errors
@CrossOrigin(origins = "*") 
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    // 🚀 Test Route
    @GetMapping("/test")
    public String test() {
        return "Backend is working";
    }

    // 🚀 RESTORED: Get all categories
    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // 🚀 RESTORED: Get all drink menu items
    @GetMapping("/menu-items")
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // ==========================================
    // PUT (Update Item)
    // ==========================================
    @PutMapping(value = "/menu-items/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<MenuItem> updateMenuItem(
            @PathVariable Long id,
            @RequestPart("item") MenuItem updatedItem, 
            @RequestPart(value = "file", required = false) MultipartFile file) {
        
        return menuItemRepository.findById(id)
            .map(existingItem -> {
                try {
                    existingItem.setName(updatedItem.getName());
                    existingItem.setPrice(updatedItem.getPrice());
                    existingItem.setCategoryName(updatedItem.getCategoryName());
                    
                    if (file != null && !file.isEmpty()) {
                        // ✨ Clean Cloud-Safe Relative Path
                        String uploadDir = "src/main/resources/static/assets/icons/";
                        String fileName = file.getOriginalFilename();
                        
                        if (fileName != null) {
                            Path copyLocation = Paths.get(uploadDir + fileName);
                            Files.createDirectories(copyLocation.getParent()); // Creates folder if missing
                            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                            existingItem.setImageUrl("assets/icons/" + fileName);
                        }
                    }
                    
                    MenuItem savedItem = menuItemRepository.save(existingItem);
                    return ResponseEntity.ok(savedItem);
                    
                } catch (IOException e) {
                    System.err.println("File system tracking transfer failed: " + e.getMessage());
                    return ResponseEntity.status(500).<MenuItem>build();
                }
            })
            .orElse(ResponseEntity.notFound().build());
    }

    // =======================================
    // POST (Create Item)
    // =======================================
    @PostMapping(value = "/menu-items", consumes = {"multipart/form-data"})
    public ResponseEntity<MenuItem> createMenuItem(
            @RequestParam("name") String name,
            @RequestParam("price") double price,
            @RequestParam("categoryId") int categoryId,
            @RequestParam("categoryName") String categoryName,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            MenuItem newItem = new MenuItem();
            newItem.setName(name);
            newItem.setPrice(price);
            newItem.setCategoryName(categoryName);
            newItem.setCategoryId(categoryId);

            if (file != null && !file.isEmpty()) {
                // ✨ FIXED: Removed C:/Users/... absolute local path
                String uploadDir = "src/main/resources/static/assets/icons/";
                String fileName = file.getOriginalFilename();
                
                if (fileName != null) {
                    Path copyLocation = Paths.get(uploadDir + fileName);
                    Files.createDirectories(copyLocation.getParent()); // Creates folder if missing
                    Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
                    newItem.setImageUrl("assets/icons/" + fileName);
                }
            } else {
                newItem.setImageUrl("assets/icons/default.png");
            }

            MenuItem savedItem = menuItemRepository.save(newItem);
            return ResponseEntity.ok(savedItem);

        } catch (IOException e) {
            System.err.println("Database entry write failure: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    // =======================================
    // DELETE (Remove Item)
    // =======================================
    @DeleteMapping("/menu-items/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        return menuItemRepository.findById(id)
            .map(item -> {
                menuItemRepository.delete(item);
                return ResponseEntity.ok().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
    }
}