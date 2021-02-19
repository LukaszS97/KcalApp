package com.example.app.service;

import com.example.app.dao.DailyMealDao;
import com.example.app.dao.UserDao;
import com.example.app.model.DailyMeal;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;

@Service
public class DailyMealServiceImpl implements DailyMealService{

    private final DailyMealDao dailyMealDao;
    private final UserDao userDao;
    public DailyMealServiceImpl(DailyMealDao dailyMealDao, UserDao userDao) {
        this.dailyMealDao = dailyMealDao;
        this.userDao = userDao;
    }


    @Override
    public DailyMeal save(DailyMeal dailyMeal, Principal principal) {
        DailyMeal dailyMealToSave = new DailyMeal.Builder()
                .user(userDao.findByLogin(principal.getName()))
                .date(new Date())
                .product(dailyMeal.getProduct())
                .weight(dailyMeal.getWeight()/100)
                .build();
        return dailyMealDao.save(dailyMealToSave);
    }

    @Override
    public void update(DailyMeal dailyMeal) {
        Optional<DailyMeal> dailyMealToUpdate = dailyMealDao.findById(dailyMeal.getDailyMealId());
        dailyMealToUpdate.get().setWeight(dailyMeal.getWeight()/100);
        dailyMealDao.save(dailyMealToUpdate.get());
    }
}
