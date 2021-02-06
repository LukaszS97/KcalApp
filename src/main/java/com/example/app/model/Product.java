package com.example.app.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Date;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productid;
    @Pattern(regexp = "[a-zA-Z0-9]{2,20}", message = "Podaj nazwę poprawnie.")
    private String name;
    @NotNull
    @Min(value = 0, message = "Minimalna ilość kcal dla posiłku to: 0kcal.")
    private int kcal;
    @NotNull
    @Min(value = 0, message = "Minimalna ilość białka dla posiłku to: 0.")
    private int protein;
    @NotNull
    @Min(value = 0, message = "Minimalna ilość tłuszczu dla posiłku to: 0.")
    private int fat;
    @NotNull
    @Min(value = 0, message = "Minimalna ilość węglowodanów dla posiłku to: 0.")
    private int carbohydrates;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
