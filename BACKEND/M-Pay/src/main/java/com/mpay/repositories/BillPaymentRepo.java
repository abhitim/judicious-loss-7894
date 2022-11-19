package com.mpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpay.model.BillPayment;

@Repository
public interface BillPaymentRepo extends JpaRepository<BillPayment, Integer> {

}
