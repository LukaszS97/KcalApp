package com.example.app.service;

import com.example.app.dao.ProductDao;
import com.example.app.model.Product;
import com.example.app.model.User;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product save(Product product) {
        Product productToSave = new Product();
        productToSave.setName(product.getName());
        productToSave.setCarbohydrates(product.getCarbohydrates());
        productToSave.setFat(product.getFat());
        productToSave.setKcal(product.getKcal());
        productToSave.setProtein(product.getProtein());
        productToSave.setUser(product.getUser());
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        productToSave.setDate(date1);
        return productDao.save(productToSave);
    }

    @Override
    public void update(Product product) {
        Optional<Product> productToupdate = productDao.findById(product.getProductid());
        productToupdate.get().setName(product.getName());
        productToupdate.get().setDate(product.getDate());
        productToupdate.get().setKcal(product.getKcal());
        productToupdate.get().setCarbohydrates(product.getCarbohydrates());
        productToupdate.get().setProtein(product.getProtein());
        productToupdate.get().setFat(product.getFat());
        productDao.save(productToupdate.get());
    }

    @Override
    public List<Product> todayEaten(User user) {
        List<Product> list = productDao.findAllByUser(user);
        List<Product> listToReturn = new ArrayList<Product>();
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        for(int i = 0; i<list.size(); i++) {
            if(date1.toString().equals(list.get(i).getDate().toString())) {
                listToReturn.add(list.get(i));
            } 
        }
        return listToReturn;
    }
}
