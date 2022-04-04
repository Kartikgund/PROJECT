package com.homemate.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homemate.entities.OrderBookingTbl;
import com.homemate.entities.ServicesTbl;
import com.homemate.entities.VendorTbl;
import com.homemate.repository.MyService;
import com.homemate.repository.MyVendor;

@Service
public class ServiceDAO {

	@Autowired
	MyService repo;
	@Autowired
	MyVendor vrepo;
	

	public void insertService( int serviceCost, String serviceDiscount, String serviceTimeHrs,
			String serviceType, VendorTbl vendor)
	{

		ServicesTbl services = new ServicesTbl(serviceCost, serviceDiscount,
				serviceTimeHrs, serviceType, vendor); 
		repo.save(services);

	}

	public List<ServicesTbl> getAllServices(int vendorID) {
		List<ServicesTbl> services = repo.vendorServices(vendorID);
		return services;
	}

	public void findtheService(int id) {
		repo.deleteById(id);
		
	}

	public ServicesTbl editTheService(int id) {
		ServicesTbl service = repo.getById(id);
		return service;
	}
	
	public ServicesTbl editTheService(int id,int serviceCost, String serviceDiscount, String serviceTimeHrs,
			String serviceType, VendorTbl vendor) {
		
		ServicesTbl service = new ServicesTbl(id,serviceCost, serviceDiscount, serviceTimeHrs, serviceType,vendor);
		repo.save(service);
		return service;
	}

	
}
