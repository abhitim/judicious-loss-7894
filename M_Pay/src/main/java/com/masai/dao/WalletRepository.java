package com.masai.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Wallet;
@Repository
public interface WalletRepository extends JpaRepository<Wallet, Integer>{

}
