package com.bookingapp.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookingapp.entity.EventActivityAndPlay;

@Repository
public interface BookingAPIDao extends MongoRepository<EventActivityAndPlay, String> {

	public List<EventActivityAndPlay> findByUserId(String userId);
}
