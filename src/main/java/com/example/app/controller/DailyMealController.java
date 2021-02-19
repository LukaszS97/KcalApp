package com.example.app.controller;

import com.example.app.dao.DailyMealDao;
import com.example.app.dao.ProductDao;
import com.example.app.dao.UserDao;
import com.example.app.model.DailyMeal;
import com.example.app.model.Product;
import com.example.app.service.DailyMealService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class DailyMealController {

    private final ProductDao productDao;
    private final UserDao userDao;
    private final DailyMealDao dailyMealDao;
    private final DailyMealService dailyMealService;

    public DailyMealController(ProductDao productDao, UserDao userDao, DailyMealDao dailyMealDao, DailyMealService dailyMealService) {
        this.productDao = productDao;
        this.userDao = userDao;
        this.dailyMealDao = dailyMealDao;
        this.dailyMealService = dailyMealService;
    }

    @GetMapping("/meal/add/{id}")
    String getAddMealPage (@PathVariable("id") Optional<Long> id, Model model) {
        if(id.isPresent()) {
            try {
                Optional<Product> product = productDao.findById(id.get());
                DailyMeal dailyMeal = new DailyMeal.Builder()
                        .product(product.get())
                        .build();
                model.addAttribute("dailyMeal", dailyMeal);
                return "addMeal";
            } catch (NoSuchElementException e) {
                return "redirect:/product/list?notFound";
            }
        } else {
            return "redirect:/product/list";
        }
    }

    @PostMapping("/meal/add")
    String addMeal(@Valid @ModelAttribute(value = "dailyMeal") DailyMeal dailyMeal, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()){
            return "redirect:/meal/add/"+dailyMeal.getProduct().getProductid()+"?fail";
        }
        dailyMealService.save(dailyMeal, principal);
        return "redirect:/product/list?added";
    }

    @GetMapping("/meal/delete/{id}")
    String deleteMeal(@ModelAttribute("id") Long id){
        dailyMealDao.delete(dailyMealDao.findById(id).get());
        return "redirect:/home?deleted";
    }

    @GetMapping("/meal/edit/{id}")
    String getEditMealPage(@PathVariable("id") Optional<Long> id, Model model) {
        if(id.isPresent()) {
            try {
                Optional<DailyMeal> dailyMeal = dailyMealDao.findById(id.get());
                dailyMeal.get().setWeight(dailyMeal.get().getWeight()*100);
                model.addAttribute("dailyMeal", dailyMeal.get());
                return "editMeal";
            } catch (NoSuchElementException e) {
                return "redirect:/home?notFound";
            }
        } else {
            return "redirect:/home";
        }
    }

    @PostMapping("/meal/edit")
    String editMeal(@Valid @ModelAttribute(value = "dailyMeal") DailyMeal dailyMeal, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()){
            return "redirect:/meal/edit/"+dailyMeal.getDailyMealId()+"?fail";
        }
        dailyMealService.update(dailyMeal);
        return "redirect:/home?edited";
    }
}