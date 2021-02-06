package com.example.app.service;

import com.example.app.dto.HomeProfileDto;
import com.example.app.model.Profile;
import com.example.app.model.User;

public interface ProfileService {

    void save(Profile profile);

    HomeProfileDto home(User user);
}
