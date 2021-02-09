package com.example.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    @Pattern(regexp = "[a-zA-Z0-9]{2,20}", message = "Login bez polskich znaków oraz symboli, od 2 do 20 znaków")
    private String login;
    @NotBlank(message="Podaj hasło")
    private String password;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Profile profile;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Product> product;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<DailyMeal> dailyMeal;

    public Set<DailyMeal> getDailyMeal() {
        return dailyMeal;
    }

    public void setDailyMeal(Set<DailyMeal> dailyMeal) {
        this.dailyMeal = dailyMeal;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
