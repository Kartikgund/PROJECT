package com.homemate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.homemate.entities.OrderBookingTbl;

@Repository
public interface OrederBookingRepository extends JpaRepository<OrderBookingTbl, Integer>{

	//select * from order_booking_tbl o inner join services_tbl s on o.booking_service_id=s.service_id where s.service_vendor_id=1003;
	@Query(value = "select * from order_booking_tbl o inner join services_tbl s on o.booking_service_id=s.service_id where s.service_vendor_id=? and o.booking_status='Booking Pending'",nativeQuery = true)
	public List<OrderBookingTbl> getorderByVendorId(int vendorId);

	@Query(value = "select * from order_booking_tbl o inner join services_tbl s on o.booking_service_id=s.service_id where s.service_vendor_id=? and o.booking_status='Scheduled'",nativeQuery = true)
	public List<OrderBookingTbl> getScheduledorderByVendorId(int vendorId);
	
	//select * from order_booking_tbl o inner join services_tbl s on o.booking_service_id=s.service_id where s.service_vendor_id=1003 and booking_review is not null;
	@Query(value = "select * from order_booking_tbl o inner join services_tbl s on o.booking_service_id=s.service_id where s.service_vendor_id=? and booking_review is not null",
			nativeQuery = true)
	public List<OrderBookingTbl> getFeedbackByVendorID(int vendorId);
}
