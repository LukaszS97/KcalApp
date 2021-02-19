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

import java.security.Principal;
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
    public void save(Profile profile, Principal principal) {
        User user = userDao.findByLogin(principal.getName());
        if(user.getProfile()==null) {
            Profile profileToSave = new Profile.Builder()
                    .name(profile.getName())
                    .surname(profile.getSurname())
                    .weight(profile.getWeight())
                    .height(profile.getHeight())
                    .age(profile.getAge())
                    .kcal(calculateKcal(profile.getHeight(), profile.getWeight(), profile.getAge()))
                    .user(user)
                    .build();
            profileDao.save(profileToSave);
        } else {
            Optional<Profile> profileToEdit = profileDao.findById(profile.getProfileid());
            profileToEdit = Optional.of(new Profile.Builder()
                    .id(profile.getProfileid())
                    .name(profile.getName())
                    .surname(profile.getSurname())
                    .weight(profile.getWeight())
                    .height(profile.getHeight())
                    .age(profile.getAge())
                    .kcal(calculateKcal(profile.getHeight(), profile.getWeight(), profile.getAge()))
                    .user(user)
                    .build());
            profileDao.save(profileToEdit.get());
        }
    }

    @Override
    public HomeProfileDto home(User user) {
        List<DailyMeal> list = dailyMealDao.findAllByUser(user);
        HomeProfileDto home = new HomeProfileDto();
        List<DailyMeal> mealsList = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();

        double eatenKcal = 0, fat = 0, protein = 0, carbohydrates = 0;
        for(int i = 0; i<list.size(); i++) {
            if(format.format(date).equals(format.format(list.get(i).getDate()))) {
                mealsList.add(list.get(i));
                eatenKcal += roundToOneDecimalPlace( list.get(i).getWeight()*list.get(i).getProduct().getKcal());
                fat += roundToOneDecimalPlace(  list.get(i).getWeight()*list.get(i).getProduct().getFat());
                protein += roundToOneDecimalPlace( list.get(i).getWeight()*list.get(i).getProduct().getProtein());
                carbohydrates += roundToOneDecimalPlace( list.get(i).getWeight()*list.get(i).getProduct().getCarbohydrates());
            }
        }
        home = new HomeProfileDto.Builder()
                .dailyMeals(mealsList)
                .name(user.getProfile().getName())
                .surname(user.getProfile().getSurname())
                .kcalToEat(user.getProfile().getKcal())
                .eatenKcal(eatenKcal)
                .fat(fat)
                .carbohydrates(carbohydrates)
                .protein(protein)
                .build();
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
