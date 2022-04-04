package com.homemate.dao;

import java.io.IOException;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.homemate.entities.ServicesTbl;
import com.homemate.entities.VendorTbl;
import com.homemate.repository.MyVendor;

@Service
public class VendorDAO {

	@Autowired
	MyVendor repo;

	public void insertVendorDetails(String vendorAddress, String vendorCity, String vendorEmail, String vendorFirstname,
			Blob image, String vendorLastname, String vendorMobile, String vendorPassword, int vendorPincode,
			String vendorUsername)
	{
		VendorTbl vendor = new VendorTbl(vendorAddress, vendorCity, vendorEmail, vendorFirstname, image, vendorLastname, vendorMobile, vendorPassword, vendorPincode, vendorUsername);

		repo.save(vendor);
	}

	public void verify(String uname,String pass) {

		if(uname=="Kartik" && pass=="123") System.out.println("ok");

		/*
		 * VendorTbl vendor= repo.findByVendorUsernameAndVendorPassword(uname, pass);
		 * if(vendor==null) return null; else return vendor;
		 */
	}


	public VendorTbl authenticationVendor(String username, String password) {
		// TODO Auto-generated method stub
		return repo.loginVendor(username, password);
	}

	public List<VendorTbl> getAllVendors() {
		List<VendorTbl> list = repo.findAll();
		return list;
	}

	public VendorTbl getvendorDetails(int id) {
		VendorTbl vendor1 = repo.getById(id);
		return vendor1;
	}

	public void updateVendorDetails(int id, String address, String city, String email, String fname, String lname,
			String mobile, int pincode) {
		
		VendorTbl v = repo.getById(id);
		v.setVendorAddress(address);
		v.setVendorCity(city);
		v.setVendorEmail(email);
		v.setVendorFirstname(fname);
		v.setVendorLastname(lname);
		v.setVendorMobile(mobile);
		v.setVendorPincode(pincode);
		repo.save(v);
		// TODO Auto-generated method stub
		
	}



}
