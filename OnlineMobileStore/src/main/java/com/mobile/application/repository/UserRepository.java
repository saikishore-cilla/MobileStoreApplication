package com.mobile.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.mobile.application.model.User;

@EnableJpaRepositories
public interface UserRepository  extends JpaRepository<User, String> {

}