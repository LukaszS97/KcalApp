package com.example.app.service;

import com.example.app.dao.DailyMealDao;
import com.example.app.model.DailyMeal;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DailyMealServiceImpl implements DailyMealService{

    private final DailyMealDao dailyMealDao;

    public DailyMealServiceImpl(DailyMealDao dailyMealDao) {
        this.dailyMealDao = dailyMealDao;
    }


    @Override
    public DailyMeal save(DailyMeal dailyMeal) {
        DailyMeal dailyMealToSave = new DailyMeal();
        dailyMealToSave.setUser(dailyMeal.getUser());
        dailyMealToSave.setProduct(dailyMeal.getProduct());
        dailyMealToSave.setDate(new Date());
        dailyMealToSave.setWeight(dailyMeal.getWeight()/100);
        return dailyMealDao.save(dailyMealToSave);
    }

    @Override
    public void update(DailyMeal dailyMeal) {
        Optional<DailyMeal> dailyMealToUpdate = dailyMealDao.findById(dailyMeal.getDailyMealId());
        dailyMealToUpdate.get().setWeight(dailyMeal.getWeight()/100);
        dailyMealDao.save(dailyMealToUpdate.get());
    }
}
