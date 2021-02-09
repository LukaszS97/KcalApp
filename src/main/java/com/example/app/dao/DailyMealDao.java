package com.example.app.dao;

import com.example.app.model.DailyMeal;
import org.springframework.data.repository.CrudRepository;

public interface DailyMealDao extends CrudRepository<DailyMeal, Long> {
}
