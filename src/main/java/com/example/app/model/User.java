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

    public static final class Builder {
        private String login;
        private String password;
        private Profile profile;
        private Set<Product> product;
        private Set<DailyMeal> dailyMeal;

        public Builder login(String login) {
            this.login=login;
            return this;
        }

        public Builder password(String password) {
            this.password=password;
            return this;
        }

        public Builder profile(Profile profile) {
            this.profile=profile;
            return this;
        }

        public Builder product(Set<Product> product) {
            this.product=product;
            return this;
        }

        public Builder dailyMeal(Set<DailyMeal> dailyMeal) {
            this.dailyMeal=dailyMeal;
            return this;
        }

        public User build() {
            User user = new User();
            user.login = this.login;
            user.password = this.password;
            user.profile = this.profile;
            user.product = this.product;
            user.dailyMeal = this.dailyMeal;
            return user;
        }
    }

    public Set<DailyMeal> getDailyMeal() {
        return dailyMeal;
    }

    public Set<Product> getProduct() {
        return product;
    }

    public Long getUserid() {
        return userid;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }


    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public void setDailyMeal(Set<DailyMeal> dailyMeal) {
        this.dailyMeal = dailyMeal;
    }
}
