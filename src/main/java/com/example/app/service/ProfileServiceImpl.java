package com.example.app.service;

import com.example.app.dao.ProductDao;
import com.example.app.dao.ProfileDao;
import com.example.app.dao.UserDao;
import com.example.app.dto.HomeProfileDto;
import com.example.app.model.Product;
import com.example.app.model.Profile;
import com.example.app.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService{

    private final UserDao userDao;
    private final ProfileDao profileDao;
    private final ProductDao productDao;

    public ProfileServiceImpl(UserDao userDao, ProfileDao profileDao, ProductDao productDao) {
        this.userDao = userDao;
        this.profileDao = profileDao;
        this.productDao = productDao;
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
        List<Product> list = productDao.findAllByUser(user);
        HomeProfileDto home = new HomeProfileDto();
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        for(int i = 0; i<list.size(); i++) {
            if(date1.toString().equals(list.get(i).getDate().toString())) {
                home.setEatenKcal(home.getEatenKcal() + list.get(i).getKcal());
                home.setFat(home.getFat() + list.get(i).getFat());
                home.setProtein(home.getProtein() + list.get(i).getProtein());
                home.setCarbohydrates(home.getCarbohydrates() + list.get(i).getCarbohydrates());
            }
        }
        home.setImie(user.getProfile().getName());
        home.setNazwisko(user.getProfile().getSurname());
        home.setKcalToEat(user.getProfile().getKcal());

        return home;
    }

    public double calculateKcal(float wzrost, float waga , int wiek) {
        double bmr = 66 + (14*waga) + (5*wzrost) - (7*wiek);
        return bmr;
    }
}
