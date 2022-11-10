package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String name;
	@Column(unique = true)
	@NotNull
	private String mobileNumber;
	@NotNull
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
	private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Customer(Integer id, @NotNull String name, @NotNull String mobileNumber,
			@NotNull @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$") String password) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}
	public Customer() {
		super();
	}
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
	
	
//	^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$
	
//	    At least one upper case English letter, (?=.*?[A-Z])
//	    At least one lower case English letter, (?=.*?[a-z])
//	    At least one digit, (?=.*?[0-9])
//	    At least one special character, (?=.*?[#?!@$%^&*-])
//	    Minimum eight in length .{8,} (with the anchors)

	
}
