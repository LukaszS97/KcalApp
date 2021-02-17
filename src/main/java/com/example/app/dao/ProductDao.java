package com.example.app.dao;

import com.example.app.model.Product;
import com.example.app.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDao extends CrudRepository<Product, Long> {

    List<Product> findAllByUser(User user);

    List<Product> findAllByProductid(Long id);

    List<Product> findAll();
}
