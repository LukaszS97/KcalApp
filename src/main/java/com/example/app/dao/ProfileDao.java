package com.example.app.dao;

import com.example.app.model.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileDao extends CrudRepository<Profile, Long> {

}
