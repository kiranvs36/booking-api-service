package com.bookingapp.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingapp.dao.BookingAPIDao;
import com.bookingapp.dao.UserDao;
import com.bookingapp.entity.EventActivityAndPlay;
import com.bookingapp.entity.User;
import com.bookingapp.service.BookingAPIService;

@Service
public class BookingAPIServiceImpl implements BookingAPIService{
	private static final Logger logger = LoggerFactory.getLogger(BookingAPIServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private BookingAPIDao bookingDao;
	
	@Override
	public ApiResponse registerUser(String userName, String password) {	
		ApiResponse response = new ApiResponse();
		try {
			//Check for existing UserId
			logger.info("Registering User: " + userName);
			Optional<User> existingUser = userDao.findById(userName); 
			if(existingUser.isPresent()) {
				logger.info("User Exists: " + userName);
				response.setCode(ApiResponse.FAILED);
				response.setData("User Exists");
			}else {
				User userData = new User();
				userData.setUserId(userName);
				userData.setPassword(password);
				userDao.save(userData);
				response.setCode(ApiResponse.SUCCESS);
				response.setData("User Registered");
				logger.info("User Registered: " + userName);
			}						
		}catch(Exception e) {
			logger.error("Exception when inserting UserData " + e);
			response.setCode(ApiResponse.FAILED);
			response.setData("Internal Server Error, contact System Administrator.");
		}
		return response;
		
	}
	
	@Override
	public ApiResponse login(String userId, String password) {	
		ApiResponse response = new ApiResponse();
		try {
			//Check for existing UserId
			logger.info("Login User: " + userId);
			Optional<User> existingUser = userDao.findById(userId); 
			if(existingUser.isPresent()) {
				if(existingUser.get().getPassword().equals(password)) {
					response.setCode(ApiResponse.SUCCESS);
					response.setData("Login Success");
					logger.info("Login Success: " + userId);
				}else {
					response.setCode(ApiResponse.FAILED);
					response.setData("Invalid password");
					logger.info("Invalid password for UserId: " + userId);
				}		
			}else {
				response.setCode(ApiResponse.FAILED);
				response.setData("Invalid UserName");
				logger.info("Invalid UserName: " + userId);
			}						
		}catch(Exception e) {
			logger.error("Exception when inserting UserData " + e);
			response.setCode(ApiResponse.FAILED);
			response.setData("Internal Server Error, contact System Administrator.");
		}
		return response;
		
	}
	
	@Override
	public ApiResponse bookEvent(String userId, EventActivityAndPlay bookingData) {	
		ApiResponse response = new ApiResponse();
		try {
			logger.info("bookEvent for User: " + userId);
			EventActivityAndPlay event = bookingDao.save(bookingData); 
			if(event == null) {
				response.setCode(ApiResponse.FAILED);
				response.setData("Booking Failed");
				logger.info("bookEvent failed for User: " + userId);
			}else {
				response.setCode(ApiResponse.SUCCESS);
				response.setData("Booking Success");
				logger.info("bookEvent success for User: " + userId);
			}						
		}catch(Exception e) {
			logger.error("Exception when inserting UserData " + e);
			response.setCode(ApiResponse.FAILED);
			response.setData("Internal Server Error, contact System Administrator.");
		}
		return response;
		
	}
	
	@Override
	public ApiResponse getUserBookings(String userId) {	
		ApiResponse response = new ApiResponse();
		try {
			logger.info("bookEvent for User: " + userId);
			List<EventActivityAndPlay> event = bookingDao.findByUserId(userId);
			if(event != null) {
				response.setCode(ApiResponse.SUCCESS);
				response.setData(event);
				logger.info("getUserBookings success for User: " + userId);
			}else {
				response.setCode(ApiResponse.FAILED);
				response.setData(new ArrayList<>());
				logger.info("getUserBookings no data found for User: " + userId);
			}						
		}catch(Exception e) {
			logger.error("Exception when getting UserData " + e);
			response.setCode(ApiResponse.FAILED);
			response.setData("Internal Server Error, contact System Administrator.");
		}
		return response;
		
	}
	
	@Override
	public ApiResponse getUserInfo(String userId) {	
		ApiResponse response = new ApiResponse();
		try {
			logger.info("bookEvent for User: " + userId);
			Optional<User> userInfo = userDao.findById(userId);
			if(userInfo.isPresent()) {
				response.setCode(ApiResponse.SUCCESS);
				response.setData(userInfo.get());
				logger.info("getUserInfo success for User: " + userId);
			}else {
				response.setCode(ApiResponse.FAILED);
				response.setData(new Object());
				logger.info("no data found for User: " + userId);
			}						
		}catch(Exception e) {
			logger.error("Exception when getting UserData " + e);
			response.setCode(ApiResponse.FAILED);
			response.setData("Internal Server Error, contact System Administrator.");
		}
		return response;
		
	}
	
	@Override
	public ApiResponse updateUserInfo(String userId, User user) {	
		ApiResponse response = new ApiResponse();
		try {
			logger.info("bookEvent for User: " + userId);
			User userInfo = userDao.save(user);
			if(userInfo != null) {
				response.setCode(ApiResponse.SUCCESS);
				response.setData("User Info updated successfully");
				logger.info("getUserInfo success for User: " + userId);
			}else {
				response.setCode(ApiResponse.FAILED);
				response.setData("Unable to updated user info");
				logger.info("no data found for User: " + userId);
			}						
		}catch(Exception e) {
			logger.error("Exception when updating UserData " + e);
			response.setCode(ApiResponse.FAILED);
			response.setData("Internal Server Error, contact System Administrator.");
		}
		return response;
		
	}

}
