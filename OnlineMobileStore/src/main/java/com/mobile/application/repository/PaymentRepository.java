package com.mobile.application.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mobile.application.model.Payment;
@Repository
public interface PaymentRepository extends CrudRepository<Payment,Integer > {

}
