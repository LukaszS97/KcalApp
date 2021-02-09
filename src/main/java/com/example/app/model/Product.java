package com.example.app.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;
    @Pattern(regexp = "[a-zA-Z0-9]{2,20}", message = "Podaj nazwę poprawnie.")
    private String name;
    @NotNull
    @Min(value = 0, message = "Minimalna waga dla posiłku to: 0kcal.")
    private double weight;
    @NotNull
    @Min(value = 0, message = "Minimalna ilość kcal dla posiłku to: 0kcal.")
    private double kcal;
    @NotNull
    @Min(value = 0, message = "Minimalna ilość białka dla posiłku to: 0.")
    private double protein;
    @NotNull
    @Min(value = 0, message = "Minimalna ilość tłuszczu dla posiłku to: 0.")
    private double fat;
    @NotNull
    @Min(value = 0, message = "Minimalna ilość węglowodanów dla posiłku to: 0.")
    private double carbohydrates;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<DailyMeal> dailyMeal;

    public Set<DailyMeal> getDailyMeal() {
        return dailyMeal;
    }

    public void setDailyMeal(Set<DailyMeal> dailyMeal) {
        this.dailyMeal = dailyMeal;
    }

    public Long getProductid() {
        return productid;
    }

    public void setProductid(Long productid) {
        this.productid = productid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
