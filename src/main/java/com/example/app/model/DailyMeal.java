package com.example.app.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="dailyMeals")
public class DailyMeal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyMealId;
    private Date date;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

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
