package com.example.app.service;

import com.example.app.dto.HomeProfileDto;
import com.example.app.model.Profile;
import com.example.app.model.User;

import java.security.Principal;

public interface ProfileService {

    void save(Profile profile, Principal principal);

    HomeProfileDto home(User user);
}
