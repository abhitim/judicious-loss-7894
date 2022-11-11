package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.BillPayment;

@Repository
public interface BillPaymentDao extends JpaRepository<BillPayment, Integer> {

}