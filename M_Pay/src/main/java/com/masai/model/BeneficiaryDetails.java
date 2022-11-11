package com.masai.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name should not be blank")
	@NotNull(message = "Name should not be null")
	@Size(min = 3,max = 20,message = "Name should be minimum 3 and maximum 20 characters")
	private String name;
	@Column(unique = true)
	@Size(min=10,max=10,message = "Mobile number should be 10 digits")
	@NotBlank(message="Mobile number should not be blank")
	@NotNull(message = "Mobile number should not be null")
	private String mobileNumber;
	
	@ManyToOne
	@JoinColumn(name = "wallet_id")
	private Wallet wallet;
}
