package com.example.app.service;

import com.example.app.model.Product;
import com.example.app.model.User;

import java.util.List;

public interface ProductService {
    Product save(Product product);
    void update(Product product);

    List<Product> todayEaten(User user);
}
