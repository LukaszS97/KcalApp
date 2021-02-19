package com.example.app.dto;

import com.example.app.model.DailyMeal;

import java.util.List;

public class HomeProfileDto {

    private double kcalToEat;
    private double eatenKcal;
    private String name;
    private String surname;
    private double fat;
    private double protein;
    private double carbohydrates;
    private List<DailyMeal> dailyMeals;


    public static final class Builder {
        private double kcalToEat;
        private double eatenKcal;
        private String name;
        private String surname;
        private double fat;
        private double protein;
        private double carbohydrates;
        private List<DailyMeal> dailyMeals;

        public Builder kcalToEat(double kcalToEat) {
            this.kcalToEat=kcalToEat;
            return this;
        }

        public Builder eatenKcal(double eatenKcal) {
            this.eatenKcal=eatenKcal;
            return this;
        }

        public Builder name(String name) {
            this.name=name;
            return this;
        }

        public Builder surname(String surname) {
            this.surname=surname;
            return this;
        }

        public Builder fat(double fat) {
            this.fat=fat;
            return this;
        }

        public Builder protein(double protein) {
            this.protein=protein;
            return this;
        }

        public Builder carbohydrates(double carbohydrates) {
            this.carbohydrates=carbohydrates;
            return this;
        }

        public Builder dailyMeals(List<DailyMeal> dailyMeals) {
            this.dailyMeals=dailyMeals;
            return this;
        }

        public HomeProfileDto build() {
            HomeProfileDto homeProfileDto = new HomeProfileDto();
            homeProfileDto.kcalToEat = this.kcalToEat;
            homeProfileDto.eatenKcal = this.eatenKcal;
            homeProfileDto.name = this.name;
            homeProfileDto.surname = this.surname;
            homeProfileDto.fat = this.fat;
            homeProfileDto.protein = this.protein;
            homeProfileDto.carbohydrates = this.carbohydrates;
            homeProfileDto.dailyMeals = this.dailyMeals;
            return homeProfileDto;
        }
    }

    public double getKcalToEat() {
        return kcalToEat;
    }

    public void setKcalToEat(double kcalToEat) {
        this.kcalToEat = kcalToEat;
    }

    public double getEatenKcal() {
        return eatenKcal;
    }

    public void setEatenKcal(double eatenKcal) {
        this.eatenKcal = eatenKcal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public List<DailyMeal> getDailyMeals() {
        return dailyMeals;
    }

    public void setDailyMeals(List<DailyMeal> dailyMeals) {
        this.dailyMeals = dailyMeals;
    }
}
