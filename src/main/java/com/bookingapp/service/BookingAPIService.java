package com.bookingapp.service;

import com.bookingapp.entity.EventActivityAndPlay;
import com.bookingapp.entity.User;
import com.bookingapp.service.impl.ApiResponse;

public interface BookingAPIService {

	public ApiResponse registerUser(String userName, String password);
	public ApiResponse login(String userName, String password);
	public ApiResponse bookEvent(String userName, EventActivityAndPlay bookingData);
	public ApiResponse getUserBookings(String userName);
	public ApiResponse getUserInfo(String userName);
	public ApiResponse updateUserInfo(String userName, User user);
}
