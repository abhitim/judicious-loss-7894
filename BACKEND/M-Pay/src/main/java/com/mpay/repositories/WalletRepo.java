package com.mpay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpay.model.Customer;
import com.mpay.model.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer> {


}
