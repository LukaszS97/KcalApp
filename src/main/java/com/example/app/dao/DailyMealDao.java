package com.example.app.dao;

import com.example.app.model.DailyMeal;
import com.example.app.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DailyMealDao extends CrudRepository<DailyMeal, Long> {

    List<DailyMeal> findAllByUser(User user);
}
