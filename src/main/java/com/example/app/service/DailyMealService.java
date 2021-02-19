package com.example.app.service;

import com.example.app.model.DailyMeal;

import java.security.Principal;

public interface DailyMealService {
    DailyMeal save(DailyMeal dailyMeal, Principal principal);

    void update(DailyMeal dailyMeal);
}
