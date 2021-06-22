package com.bookingapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {

	private String userId;
	private String eventType;
	private String eventName;
	private String location;
	private String eventPrice;
	private String eventDate;
	private String eventImg;
	private String timeSlot;
	private int ticketCount;
}
