package com.bookingapp.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookingapp.entity.EventActivityAndPlay;
import com.bookingapp.entity.User;
import com.bookingapp.service.impl.ApiResponse;
import com.bookingapp.service.impl.BookingAPIServiceImpl;

@RestController
@RequestMapping(path = "/booking")
public class BookingController {
	
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@Autowired
	private BookingAPIServiceImpl bookingAPIServiceImpl;
	
	@GetMapping(path = "/healthCheck")
	public Object preBuildProcess()  {
		logger.debug("preBuildProcess--> ");
		//buildService.preBuildProcess(buildAuditReq);
		Map<String,String> response = new HashMap<>();
		response.put("message", "PreBuildProcess is success");
		return "Its Working";
	}
	
	@CrossOrigin
	@GetMapping(path = "/login/{userId}/{password}", produces = "application/json")
	public ApiResponse login(@PathVariable("userId")String userId, @PathVariable("password")String password)  {
		logger.debug("login--> " + "userId-" + userId + " Password-"+password);
		ApiResponse response = bookingAPIServiceImpl.login(userId, password);
		return response;
	}
	
	@CrossOrigin
	@PostMapping(path = "/registerUser/{userId}/{password}")
	public ApiResponse registerUser(@PathVariable("userId")String userId, @PathVariable("password")String password)  {
		logger.debug("registerUser--> " + userId);
		ApiResponse response = bookingAPIServiceImpl.registerUser(userId, password);
		return response;
	}
	
	@CrossOrigin
	@PutMapping(path = "/book/{userId}")
	public ApiResponse bookEvent(@RequestBody EventActivityAndPlay bookingData, @PathVariable("userId")String userId)  {
		logger.debug("bookEvent for:" + userId + " Event:" + bookingData);
		ApiResponse response = bookingAPIServiceImpl.bookEvent(userId, bookingData);
		return response;
	}
	
	@CrossOrigin
	@GetMapping(path = "/myBookings/{userId}")
	public ApiResponse getUserBookings(@PathVariable("userId")String userId)  {
		logger.debug("getUserBookings for:" + userId);
		ApiResponse response = bookingAPIServiceImpl.getUserBookings(userId);
		return response;
	}
	
	@CrossOrigin
	@GetMapping(path = "/myInfo/{userId}")
	public ApiResponse getUserInfo(@PathVariable("userId")String userId)  {
		logger.debug("getUserInfo for:" + userId);
		ApiResponse response = bookingAPIServiceImpl.getUserInfo(userId);
		return response;
	}
	
	@CrossOrigin
	@PutMapping(path = "/updateUserInfo/{userId}")
	public ApiResponse updateUserInfo(@PathVariable("userId")String userId, @RequestBody User user)  {
		logger.debug("updateUserInfo for:" + userId);
		ApiResponse response = bookingAPIServiceImpl.updateUserInfo(userId, user);
		return response;
	}

}
