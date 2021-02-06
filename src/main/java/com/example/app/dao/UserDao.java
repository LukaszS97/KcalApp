package com.example.app.dao;

import com.example.app.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

    public User findByLogin(String login);
}
