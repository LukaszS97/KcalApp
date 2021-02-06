package com.example.app.controller;

import com.example.app.dao.ProductDao;
import com.example.app.dao.UserDao;
import com.example.app.model.Product;
import com.example.app.model.User;
import com.example.app.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ProductDao productDao;
    private final UserDao userDao;

    public ProductController(ProductService productService, ProductDao productDao, UserDao userDao) {
        this.productService = productService;
        this.productDao = productDao;
        this.userDao = userDao;
    }

    @GetMapping("/product")
    public String productPage(Model model) {
        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping("/product")
    public String addProduct(@Valid @ModelAttribute(value = "product") Product product, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            return "product";
        }
        product.setUser(userDao.findByLogin(principal.getName()));
        productService.save(product);
        return "redirect:/product/list";
    }

    @GetMapping("/product/list")
    public String productListPage(Model model, Principal principal) {
        User user = userDao.findByLogin(principal.getName());
        model.addAttribute("productList", productService.todayEaten(user));
        return "productList";
    }

    @GetMapping("/product/delete/{id}")
    String deleteKcal(@ModelAttribute("id") Long id){
        productDao.delete(productDao.findById(id).get());
        return "redirect:/product/list?deleted";
    }

    @GetMapping("/product/edit/{id}")
    String getEditPage(@PathVariable("id") Optional<Long> id, Model model) {
        if(id.isPresent()) {
            try {
                Optional<Product> product = productDao.findById(id.get());
                model.addAttribute("product", product.get());
                return "editProduct";
            } catch (NoSuchElementException e) {
                return "redirect:/product/list?notFound";
            }
        } else {
            return "redirect:/product/list";
        }
    }

    @PostMapping("/product/edit")
    String editWallet(@Valid @ModelAttribute(value = "product") Product product, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()){
            return "redirect:/product/edit/"+product.getProductid()+"?fail";
        }
        product.setUser(userDao.findByLogin(principal.getName()));
        productService.update(product);
        return "redirect:/product/list?edited";
    }
}
