package com.nit.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class UserEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String  username;
	private String  password;
	private String  email;
	private String  dob;
	private Long    phoneno;
	private String  country;
	

}
