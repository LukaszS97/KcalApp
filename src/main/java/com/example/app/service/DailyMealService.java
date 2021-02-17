package com.example.app.service;

import com.example.app.model.DailyMeal;

public interface DailyMealService {
    DailyMeal save(DailyMeal dailyMeal);

    void update(DailyMeal dailyMeal);
}
