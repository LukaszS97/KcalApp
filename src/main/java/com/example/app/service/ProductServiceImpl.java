package com.example.app.service;

import com.example.app.dao.ProductDao;
import com.example.app.model.Product;
import com.example.app.model.User;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product save(Product product) {
        Product productToSave = new Product();
        double percentage100grams = calculateWeightTo100G(product.getWeight());
        productToSave.setName(product.getName().substring(0,1).toUpperCase() + product.getName().toLowerCase().substring(1));
        productToSave.setWeight(Math.round(product.getWeight()*percentage100grams)/100.0);
        productToSave.setCarbohydrates(Math.round(product.getCarbohydrates()*percentage100grams)/100.0);
        productToSave.setFat(Math.round(product.getFat()*percentage100grams)/100.0);
        productToSave.setKcal(Math.round(product.getKcal()*percentage100grams)/100.0);
        productToSave.setProtein(Math.round(product.getProtein()*percentage100grams)/100.0);
        productToSave.setUser(product.getUser());
        return productDao.save(productToSave);
    }

    @Override
    public void update(Product product) {
        Optional<Product> productToupdate = productDao.findById(product.getProductid());
        double percentage100grams = calculateWeightTo100G(product.getWeight());
        productToupdate.get().setName(product.getName().substring(0,1).toUpperCase() + product.getName().toLowerCase().substring(1));
        productToupdate.get().setWeight(Math.round(product.getWeight()*percentage100grams)/100.0);
        productToupdate.get().setCarbohydrates(Math.round(product.getCarbohydrates()*percentage100grams)/100.0);
        productToupdate.get().setFat(Math.round(product.getFat()*percentage100grams)/100.0);
        productToupdate.get().setKcal(Math.round(product.getKcal()*percentage100grams)/100.0);
        productToupdate.get().setProtein(Math.round(product.getProtein()*percentage100grams)/100.0);
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
