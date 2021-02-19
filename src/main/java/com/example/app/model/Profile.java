package com.example.app.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;


@Entity
@Table(name="profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileid;
    @Pattern(regexp = "[a-zA-Z0-9]{2,20}", message = "Podaj imię poprawnie.")
    private String name;
    @Pattern(regexp = "[a-zA-Z0-9]{2,20}", message = "Podaj nazwisko poprawnie.")
    private String surname;
    @NotNull
    @Max(value = 400, message = "Maksymalna waga użytkownika to 400kg.")
    @Min(value = 30, message = "Minimalna waga użytkownika to 30kg.")
    private int weight;
    @NotNull
    @Max(value = 250, message = "Maksymalny wzrost użytkownika to 250cm.")
    @Min(value = 60, message = "Minimalny wzrost użytkownika to 60cm.")
    private int height;
    @NotNull
    @Max(value = 130, message = "Maksymalny wiek użytkownika to 130lat.")
    @Min(value = 5, message = "Minimalny wiek użytkownika to 5lat.")
    private int age;
    private double kcal;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public static final class Builder {
        private Long id;
        private String name;
        private String surname;
        private int weight;
        private int height;
        private int age;
        private double kcal;
        private User user;

        public Builder id(Long id) {
            this.id=id;
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

        public Builder weight(int weight) {
            this.weight=weight;
            return this;
        }

        public Builder height(int height) {
            this.height=height;
            return this;
        }

        public Builder age(int age) {
            this.age=age;
            return this;
        }

        public Builder kcal(double kcal) {
            this.kcal=kcal;
            return this;
        }

        public Builder user(User user) {
            this.user=user;
            return this;
        }

        public Profile build() {
            Profile profile = new Profile();
            profile.profileid = this.id;
            profile.name = this.name;
            profile.surname = this.surname;
            profile.weight = this.weight;
            profile.height = this.height;
            profile.age = this.age;
            profile.kcal = this.kcal;
            profile.user = this.user;
            return profile;
        }
    }


    public Long getProfileid() {
        return profileid;
    }

    public void setProfileid(Long profileid) {
        this.profileid = profileid;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
