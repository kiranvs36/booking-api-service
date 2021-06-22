package com.bookingapp.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {

	private String userId;
	private String password;
	private String name;
	private long phone;
	private String email;
	private String address;
	private Date creationDate = new Date();
}
