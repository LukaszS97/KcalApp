package com.example.app.service;

import com.example.app.model.Product;
import com.example.app.model.User;

import java.security.Principal;
import java.util.List;

public interface ProductService {
    Product save(Product product, Principal principal);
    void update(Product product, Principal principal);

    List<Product> productsList(User user);
}
