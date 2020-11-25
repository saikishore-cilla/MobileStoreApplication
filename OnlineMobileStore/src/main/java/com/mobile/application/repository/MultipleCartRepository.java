package com.mobile.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mobile.application.model.MultipleCart;
@Repository
public interface MultipleCartRepository extends CrudRepository<MultipleCart, String> {

}
