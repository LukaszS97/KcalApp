package com.example.app.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="dailyMeals")
public class DailyMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyMealId;
    @NotNull
    @Min(value = 0, message = "Minimalna waga dla posiłku to: 0kcal.")
    private double weight;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public static final class Builder {
        private Long dailyMealId;
        private double weight;
        private Date date;
        private User user;
        private Product product;

        public Builder dailyMealId(Long dailyMealId) {
            this.dailyMealId=dailyMealId;
            return this;
        }

        public Builder weight(double weight) {
            this.weight=weight;
            return this;
        }

        public Builder date(Date date) {
            this.date=date;
            return this;
        }

        public Builder user(User user) {
            this.user=user;
            return this;
        }

        public Builder product(Product product) {
            this.product=product;
            return this;
        }

        public DailyMeal build() {
            DailyMeal dailyMeal = new DailyMeal();
            dailyMeal.dailyMealId=dailyMealId;
            dailyMeal.date=date;
            dailyMeal.product=product;
            dailyMeal.user=user;
            dailyMeal.weight=weight;
            return dailyMeal;
        }
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Long getDailyMealId() {
        return dailyMealId;
    }

    public void setDailyMealId(Long dailyMealId) {
        this.dailyMealId = dailyMealId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
