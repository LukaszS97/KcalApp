package com.example.app.service;

import com.example.app.dao.DailyMealDao;
import com.example.app.dao.ProductDao;
import com.example.app.dao.ProfileDao;
import com.example.app.dao.UserDao;
import com.example.app.dto.HomeProfileDto;
import com.example.app.model.DailyMeal;
import com.example.app.model.Product;
import com.example.app.model.Profile;
import com.example.app.model.User;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final UserDao userDao;
    private final ProfileDao profileDao;
    private final ProductDao productDao;
    private final DailyMealDao dailyMealDao;

    public ProfileServiceImpl(UserDao userDao, ProfileDao profileDao, ProductDao productDao, DailyMealDao dailyMealDao) {
        this.userDao = userDao;
        this.profileDao = profileDao;
        this.productDao = productDao;
        this.dailyMealDao = dailyMealDao;
    }

    @Override
    public void save(Profile profile) {
        User user = userDao.findByLogin(profile.getUser().getLogin());
        if(user.getProfile()==null) {
            Profile profileToSave = new Profile();
            profileToSave.setUser(profile.getUser());
            profileToSave.setName(profile.getName());
            profileToSave.setSurname(profile.getSurname());
            profileToSave.setWeight(profile.getWeight());
            profileToSave.setHeight(profile.getHeight());
            profileToSave.setAge(profile.getAge());
            profileToSave.setKcal(calculateKcal(profile.getHeight(), profile.getWeight(), profile.getAge()));
            profileDao.save(profileToSave);
        } else {
            Optional<Profile> profileToSave = profileDao.findById(profile.getProfileid());
            profileToSave.get().setProfileid(profile.getProfileid());
            profileToSave.get().setUser(profile.getUser());
            profileToSave.get().setName(profile.getName());
            profileToSave.get().setSurname(profile.getSurname());
            profileToSave.get().setWeight(profile.getWeight());
            profileToSave.get().setHeight(profile.getHeight());
            profileToSave.get().setAge(profile.getAge());
            profileToSave.get().setKcal(calculateKcal(profile.getHeight(), profile.getWeight(), profile.getAge()));
            profileDao.save(profileToSave.get());
        }
    }

    @Override
    public HomeProfileDto home(User user) {
        List<DailyMeal> list = dailyMealDao.findAllByUser(user);
        HomeProfileDto home = new HomeProfileDto();
        List<DailyMeal> mealsList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        for(int i = 0; i<list.size(); i++) {
            if(format.format(date).equals(format.format(list.get(i).getDate()))) {
                mealsList.add(list.get(i));
                home.setEatenKcal(roundToOneDecimalPlace(home.getEatenKcal() + list.get(i).getWeight()*list.get(i).getProduct().getKcal()));
                home.setFat(roundToOneDecimalPlace( home.getFat() + list.get(i).getWeight()*list.get(i).getProduct().getFat()));
                home.setProtein(roundToOneDecimalPlace( home.getProtein() + list.get(i).getWeight()*list.get(i).getProduct().getProtein()));
                home.setCarbohydrates(roundToOneDecimalPlace( home.getCarbohydrates() + list.get(i).getWeight()*list.get(i).getProduct().getCarbohydrates()));
            }
        }
        home.setDailyMeals(mealsList);
        home.setImie(user.getProfile().getName());
        home.setNazwisko(user.getProfile().getSurname());
        home.setKcalToEat(user.getProfile().getKcal());

        return home;
    }

    public double calculateKcal(float wzrost, float waga , int wiek) {
        double bmr = 66 + (14*waga) + (5*wzrost) - (7*wiek);
        return bmr;
    }

    public Double roundToOneDecimalPlace(double number) {
        number *= 10;
        number = Math.round(number);
        return number/10;
    }
}
