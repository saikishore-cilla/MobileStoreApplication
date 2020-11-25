package com.mobile.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import com.mobile.application.model.Item;


public interface ItemRepository extends JpaRepository<Item, Integer>{

}
