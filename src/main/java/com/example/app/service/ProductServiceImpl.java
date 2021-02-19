package com.example.app.service;

import com.example.app.dao.ProductDao;
import com.example.app.dao.UserDao;
import com.example.app.model.Product;
import com.example.app.model.User;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;
    private final UserDao userDao;

    public ProductServiceImpl(ProductDao productDao, UserDao userDao) {
        this.productDao = productDao;
        this.userDao = userDao;
    }

    @Override
    public Product save(Product product, Principal principal) {
        double percentage100grams = calculateWeightTo100G(product.getWeight());
        Product productToSave = new Product.Builder()
                .name(product.getName().substring(0,1).toUpperCase() + product.getName().toLowerCase().substring(1))
                .weight(Math.round(product.getWeight()*percentage100grams)/100.0)
                .carbohydrates(Math.round(product.getCarbohydrates()*percentage100grams)/100.0)
                .fat(Math.round(product.getFat()*percentage100grams)/100.0)
                .protein(Math.round(product.getProtein()*percentage100grams)/100.0)
                .kcal(Math.round(product.getKcal()*percentage100grams)/100.0)
                .user(userDao.findByLogin(principal.getName()))
                .build();
        return productDao.save(productToSave);
    }

    @Override
    public void update(Product product, Principal principal) {
        Optional<Product> productToupdate = productDao.findById(product.getProductid());
        double percentage100grams = calculateWeightTo100G(product.getWeight());
        productToupdate = Optional.of(new Product.Builder()
                .productid(product.getProductid())
                .name(product.getName().substring(0,1).toUpperCase() + product.getName().toLowerCase().substring(1))
                .weight(Math.round(product.getWeight()*percentage100grams)/100.0)
                .carbohydrates(Math.round(product.getCarbohydrates()*percentage100grams)/100.0)
                .fat(Math.round(product.getFat()*percentage100grams)/100.0)
                .protein(Math.round(product.getProtein()*percentage100grams)/100.0)
                .kcal(Math.round(product.getKcal()*percentage100grams)/100.0)
                .user(userDao.findByLogin(principal.getName()))
                .build());
        productDao.save(productToupdate.get());
    }

    @Override
    public List<Product> productsList(User user) {
        List<Product> sortedProducts = productDao.findAll().stream()
                .sorted(Comparator.comparing(Product::getName))
                .collect(Collectors.toList());
        return sortedProducts;
    }

    public Double calculateWeightTo100G(double weight) {
        double x = 10000/weight;
        return x;
    }
}
