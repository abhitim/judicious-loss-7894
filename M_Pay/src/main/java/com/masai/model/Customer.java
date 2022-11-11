package com.masai.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotBlank(message = "Name should not be blank")
	@NotNull(message = "Name should not be null")
	@Size(min = 3,max = 20,message = "Name should be minimum 3 and maximum 20 characters")
	private String CustomerName;
	@NotBlank(message = "Mobile number should not be blank")
	@NotNull(message = "Mobile number should not be null")
	@Size(min = 10,max = 10,message = "Mobile number should be 10 digits")
	@Column(unique = true)
	private String Mobile;
	@NotBlank(message = "Password should not be blank")
	@NotNull(message = "Password should not be null")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$",message =
	"At least one upper case and one lower case English letter, At least one special characters and At least one digit ,should be minimum 8 in length ")
	private String CustomerPassword;
	
	@OneToOne(mappedBy = "customer")
	private Wallet wallets;
	
}
