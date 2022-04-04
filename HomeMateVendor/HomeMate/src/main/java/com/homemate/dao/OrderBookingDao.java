package com.homemate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homemate.entities.OrderBookingTbl;
import com.homemate.repository.OrederBookingRepository;

@Service
public class OrderBookingDao {

	@Autowired
	OrederBookingRepository repo;

	public List<OrderBookingTbl> getAllOrders(int id) {
		List<OrderBookingTbl> list = repo.getorderByVendorId(id);
		return list;
	}


	public List<OrderBookingTbl> getAllSchedule(int id) {
		List<OrderBookingTbl> list = repo.getScheduledorderByVendorId(id);
		return list;
	}

	public void findAndDeletetheService(int id) {
		repo.deleteById(id);
		
	}


	public List<OrderBookingTbl> getFeedbackReview(int id) {
		List<OrderBookingTbl> list = repo.getFeedbackByVendorID(id);
		return list;
	}
	
	public void acceptTheService(int id) {
		OrderBookingTbl order = repo.getById(id);
		order.setBookingStatus("Scheduled");
		repo.save(order);
	}


}
