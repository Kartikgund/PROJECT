package com.homemate.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.homemate.dao.OrderBookingDao;
import com.homemate.dao.ServiceDAO;
import com.homemate.entities.OrderBookingTbl;
import com.homemate.entities.ServicesTbl;
import com.homemate.entities.VendorTbl;
import com.homemate.repository.OrederBookingRepository;


@RequestMapping
@Controller
public class ServiceController {

	@Autowired
	ServiceDAO serviceDao;

	@Autowired
	OrderBookingDao orderBookingDao;

	//--------------------insert and get service of login vendor-------------------------------
	@RequestMapping("/getServices")
	public ModelAndView getAllVendors(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		HttpSession session = request.getSession();
		VendorTbl vendor = (VendorTbl) session.getAttribute("vendor");
		System.out.println(session);
		int id = vendor.getVendorId();

		List<ServicesTbl> list = serviceDao.getAllServices(id);
		mv.addObject("services", list); // request.setAttribute
		mv.setViewName("VendorManageServices"); // requestdispatcher forward
		return mv;
	}

	//---------------insert new service--------------------------------------
	@PostMapping("/insertService")
	public String insertService(@RequestParam("serviceType") String service,@RequestParam("cost") int cost,@RequestParam("discount") String discount,@RequestParam("time") String time,HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		VendorTbl vendor = (VendorTbl) session.getAttribute("vendor");
		System.out.println(session);
		serviceDao.insertService(cost, discount, time, service,vendor);
		return "redirect:/getServices";

	}

	//--------------------Delete Service----------------------------------
	@GetMapping("/deleteService/{id}")
	public String deleteService(@PathVariable int id)
	{
		serviceDao.findtheService(id);
		return "redirect:/getServices";

	}

	//--------------------Update Service----------------------------------------------
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView editService(@RequestParam int id)
	{
		ModelAndView mv = new ModelAndView();
		ServicesTbl service = serviceDao.editTheService(id);
		System.out.println("I'm here");
		System.out.println(service.getServiceType());
		mv.addObject("service", service);
		mv.setViewName("VendorEditService");

		return mv;
	}


	//----------------------Update Service--------------------------------------
	@PostMapping("/updateService")
	public String updateService(@RequestParam("id") int id,@RequestParam("serviceType") String service,@RequestParam("cost") int cost,@RequestParam("discount") String discount,@RequestParam("time") String time,HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		VendorTbl vendor = (VendorTbl) session.getAttribute("vendor");
		serviceDao.editTheService(id, cost, discount, time, service,vendor);

		return "redirect:/getServices";
	}

	//--------------------Update Service----------------------------------------------
		@RequestMapping(value="/acceptService", method = RequestMethod.GET)
		public String acceptService(@RequestParam int id)
		{
			System.out.println("I'm here "+id);
			orderBookingDao.acceptTheService(id);
			System.out.println("I'm here "+id);
			/* System.out.println(service.getServiceType()); */
			//mv.addObject("service", order);
			return "redirect:/orderBooking";
		}

	
	//--------------------Log out------------------------------
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView Logout(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		session.invalidate();
		mv.setViewName("VendorLogin");
		return mv;
	}
	//---------------------Order Booking------------------
	@RequestMapping(value="/orderBooking", method = RequestMethod.GET)
	public ModelAndView OrderBooking(HttpServletRequest request)
	{
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		VendorTbl vendor =  (VendorTbl)session.getAttribute("vendor");
		int id = vendor.getVendorId();
		List<OrderBookingTbl> list = orderBookingDao.getAllOrders(id);
		List<OrderBookingTbl> list1 = orderBookingDao.getAllSchedule(id);
		mv.addObject("orders", list);
		mv.addObject("schedule", list1);
		mv.setViewName("VendorMyAppointment");
		return mv;
	}

	//--------------------Delete Service----------------------------------
	@GetMapping("/deleteOrder/{id}")
	public String deleteOrderBooking(@PathVariable int id)
	{
		orderBookingDao.findAndDeletetheService(id);
		return "redirect:/orderBooking";

	}

	//---------------------Service Feedback-------------------
	@GetMapping("/vendorFeedback")
	public ModelAndView feedback(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		VendorTbl vendor =  (VendorTbl)session.getAttribute("vendor");
		int id = vendor.getVendorId();
		System.out.println(id);
		
		List<OrderBookingTbl> list = orderBookingDao.getFeedbackReview(id);
		if(Objects.nonNull(list))
		{
			mv.addObject("feedback", list);
			mv.setViewName("VendorViewFeedback");
			return mv;
		}
		else
		{
			mv.setViewName("VendorViewFeedback");
			return mv;
		}
	}

}
