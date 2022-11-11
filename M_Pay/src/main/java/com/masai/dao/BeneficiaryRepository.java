package com.masai.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.BeneficiaryDetails;

@Repository
public interface BeneficiaryRepository extends JpaRepository<BeneficiaryDetails, Integer>{
	@Query("from BeneficiaryDetails where mobileNumber=?1")
	public BeneficiaryDetails getByMobile(String mobile);
	public List<BeneficiaryDetails> findByMobileNumber(String mobileNo);
}
