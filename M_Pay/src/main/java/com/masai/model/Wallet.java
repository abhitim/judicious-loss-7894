package com.masai.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int walletId;
	private BigDecimal balance;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id",referencedColumnName = "id")
	@JsonIgnore
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "wallet",orphanRemoval = true)
	@JsonIgnore
	private List<BeneficiaryDetails> beneficiary=new ArrayList<>();
}
