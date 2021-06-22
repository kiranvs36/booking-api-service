package com.bookingapp.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bookingapp.dao.BookingAPIDao;
import com.bookingapp.dao.UserDao;
import com.bookingapp.entity.EventActivityAndPlay;
import com.bookingapp.entity.User;
import com.bookingapp.service.impl.ApiResponse;

@SpringBootTest
public class BookingControllerTest {
	
	@Autowired
	BookingController bookingController;
	
	@MockBean
	private UserDao userDao;
	@MockBean
	private BookingAPIDao bookingDao;
	
	@Test
	public void testLogin() {
		Optional<User> existingUser = Optional.ofNullable(new User());
		existingUser.get().setPassword("password");
		Mockito.when(userDao.findById("userName")).thenReturn(existingUser);
		
		ApiResponse responseSuccess = bookingController.login("userName", "password");
		Assertions.assertEquals(200, responseSuccess.getCode(), "Test for Success Login");
		
		existingUser.get().setPassword("invaid");
		ApiResponse responseFailPassword = bookingController.login("userName", "password");
		Assertions.assertEquals(500, responseFailPassword.getCode(), "Test for Invalid passowrd");
		
		Mockito.when(userDao.findById("userName")).thenReturn(null);
		ApiResponse responseInavidUser = bookingController.login("userName", "password");
		Assertions.assertEquals(500, responseInavidUser.getCode(), "Test for invalid UserName");
	}
	
	@Test
	public void testRegisterUser() {
		Optional<User> newUser = Optional.empty();
		Mockito.when(userDao.findById("userName")).thenReturn(newUser);
		
		ApiResponse responseSuccess = bookingController.registerUser("userName", "password");
		Assertions.assertEquals(200, responseSuccess.getCode(), "Test for register user success");
		
		Optional<User> existingUser = Optional.ofNullable(new User());
		Mockito.when(userDao.findById("userName")).thenReturn(existingUser);
		ApiResponse responseFailPassword = bookingController.registerUser("userName", "password");
		Assertions.assertEquals(500, responseFailPassword.getCode(), "Test for Existing User");
		
	}
	
	@Test
	public void testBookEvent() {
		EventActivityAndPlay event = new EventActivityAndPlay();
		Mockito.when(bookingDao.save(Mockito.any())).thenReturn(event);
		
		ApiResponse responseSuccess = bookingController.bookEvent(event, "userName");
		Assertions.assertEquals(200, responseSuccess.getCode(), "Test for Book event success");
		
		Mockito.when(bookingDao.save(Mockito.any())).thenReturn(null);
		ApiResponse responseFailPassword = bookingController.bookEvent(event, "userName");
		Assertions.assertEquals(500, responseFailPassword.getCode(), "Test for Book Event Failure");
		
	}
	
	@Test
	public void testGetUserBookings() {
		Mockito.when(bookingDao.findByUserId(Mockito.any())).thenReturn(new ArrayList<EventActivityAndPlay>());
		
		ApiResponse responseSuccess = bookingController.getUserBookings("userName");
		Assertions.assertEquals(200, responseSuccess.getCode(), "Test for GetUserBookings success");
		
		Mockito.when(bookingDao.findByUserId(Mockito.any())).thenReturn(null);
		ApiResponse responseFailPassword = bookingController.getUserBookings("userName");
		Assertions.assertEquals(500, responseFailPassword.getCode(), "Test for GetUserBookings Failure");
		
	}
	
	@Test
	public void testGetUserInfo() {
		Optional<User> existingUser = Optional.ofNullable(new User());
		Mockito.when(userDao.findById("userName")).thenReturn(existingUser);
				
		ApiResponse responseSuccess = bookingController.getUserInfo("userName");
		Assertions.assertEquals(200, responseSuccess.getCode(), "Test for getUserInfo success");
		
		Optional<User> newUser = Optional.empty();
		Mockito.when(userDao.findById("userName")).thenReturn(newUser);
		ApiResponse responseFailPassword = bookingController.getUserInfo("userName");
		Assertions.assertEquals(500, responseFailPassword.getCode(), "Test for getUserInfo Failure");
		
	}
	
	@Test
	public void testUpdateUserInfo() {
		Mockito.when(userDao.save(Mockito.any())).thenReturn(new User());
				
		ApiResponse responseSuccess = bookingController.updateUserInfo("userName", new User());
		Assertions.assertEquals(200, responseSuccess.getCode(), "Test for UpdateUserInfo success");
		
		Mockito.when(userDao.save(Mockito.any())).thenReturn(null);
		ApiResponse responseFailPassword = bookingController.updateUserInfo("userName", new User());
		Assertions.assertEquals(500, responseFailPassword.getCode(), "Test for UpdateUserInfo Failure");
		
	}

}
