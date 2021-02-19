package com.example.app.controller;

import com.example.app.dao.UserDao;
import com.example.app.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute(value = "user") User user, BindingResult binding) {
        if(binding.hasErrors()) {
            return "register";
        } else if(userDao.findByLogin(user.getLogin()) == null) {
            User userToSave = new User.Builder()
                    .login(user.getLogin())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .build();
            userDao.save(userToSave);
            return "redirect:/login";
        } else {
            return "redirect:/register?fail";
        }
    }
}
