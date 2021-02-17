package com.example.app.dto;

import com.example.app.model.DailyMeal;
import com.example.app.model.Product;

import java.util.List;

public class HomeProfileDto {

    private double kcalToEat;
    private double eatenKcal;
    private String imie;
    private String nazwisko;
    private double fat;
    private double protein;
    private double carbohydrates;
    private List<DailyMeal> dailyMeals;

    public List<DailyMeal> getDailyMeals() {
        return dailyMeals;
    }

    public void setDailyMeals(List<DailyMeal> dailyMeals) {
        this.dailyMeals = dailyMeals;
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

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
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
}
