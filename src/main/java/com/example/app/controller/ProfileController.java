package com.example.app.controller;

import com.example.app.dao.ProfileDao;
import com.example.app.dao.UserDao;
import com.example.app.dto.HomeProfileDto;
import com.example.app.model.Profile;
import com.example.app.model.User;
import com.example.app.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class ProfileController {

    private final UserDao userDao;
    private final ProfileDao profileDao;
    private final ProfileService profileService;

    public ProfileController(UserDao userDao, ProfileDao profileDao, ProfileService profileService) {
        this.userDao = userDao;
        this.profileDao = profileDao;
        this.profileService = profileService;
    }

    @GetMapping("/home")
        public String homePage(Model model, Principal principal) {
        User user = userDao.findByLogin(principal.getName());
        if(user.getProfile() != null) {
            model.addAttribute("home", profileService.home(user));
        } else {
            model.addAttribute("home", new HomeProfileDto());
        }
        return "home";
        }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        User user = userDao.findByLogin(principal.getName());
        if(user.getProfile()==null) {
            model.addAttribute("profile", new Profile());
        } else {
            model.addAttribute("profile", profileDao.findById(user.getProfile().getProfileid()));
        }
        return "profile";
    }

    @PostMapping("/profile")
    public String profile(@Valid @ModelAttribute(value = "profile") Profile profile, BindingResult binding, Principal principal) {
        if(binding.hasErrors()){
            return "profile";
        }
            profile.setUser(userDao.findByLogin(principal.getName()));
            profileService.save(profile);
            return "redirect:/profile?success";
    }
}
