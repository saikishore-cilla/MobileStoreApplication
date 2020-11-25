package com.mobile.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.model.Orders;
import com.mobile.application.model.Payment;
import com.mobile.application.model.User;
import com.mobile.application.repository.OrdersRepository;
import com.mobile.application.repository.PaymentRepository;

@Controller
@RequestMapping("/User") 
public class PaymentController {
	
	@Autowired
	private OrdersRepository orderRepository;
	
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	
	//Multiple order
		
	 @PostMapping(value="/pay")
	  public ModelAndView getDatas(@SessionAttribute("User")User users, Model models) {
		  System.out.println("hmmmmmmmmmmmmmmmmmmmmmuuullllllllllllll");
		 
			
			List<Orders> ordervalue = (List<Orders>)orderRepository.findAll();
			List<Orders> order = new ArrayList<Orders>();
			String email=users.getEmail();
			for(var i:ordervalue)
			{
				if(i.getEmail().equals(email))
					order.add(i);
			}
			System.out.println(order);
			/*
			 * for(var j:order) { orderRepository.deleteById(j.getOrderid()); }
			 */
			ModelAndView mn=new ModelAndView("payinfo");
			mn.addObject("multiple", order);
			return mn;
		}
			
	 
		 ////saving payment details
				
		
@PostMapping(path="/saveTopay")
public ModelAndView savePay1(Payment p,
		@RequestParam("fullname") String fullname,
		@RequestParam("address") String address,
  		@RequestParam("city") String city,
  		@RequestParam("modeofpayment") String modeofpayment,
		@SessionAttribute("User")User users ) {


 	List<Orders> orderValue = (List<Orders>)orderRepository.findAll();
	List<Orders> order = new ArrayList<Orders>();
	String email=users.getEmail();
	for(var i:orderValue)
	{
		if(i.getEmail().equals(email))
			order.add(i);
	}
	System.out.print(order);
	
	System.out.println(order);

	for(var j:order)
	{
		System.out.println("hiii");
		Payment p1=new Payment();
		p1.setAddress(address);
		p1.setCity(city);
		p1.setFullname(fullname);
		p1.setModeofpayment(modeofpayment);
		p1.setEmail(email);
		p1.setItemname(j.getItemname());			
		p1.setTotal(j.getTotal());
		//p.setModeofpayment("cash");
		System.out.println(p1);
		paymentRepository.save(p1);
		System.out.println("Done");
		//orderRepository.deleteById(j.getOrderid());
	}

	List<Payment>pays=(List<Payment>) paymentRepository.findAll();
	List<Payment>payment1=new ArrayList<Payment>();
	int k=0;
	
	int n=pays.size();
	for(int j=n-1;j>0;j--)
	{

		payment1.add(pays.get(j));
		
		if(++k>10)break;
	}
	

	  ModelAndView mn=new  ModelAndView("successpage")	;
		for(var j:order)
		{
			orderRepository.deleteById(j.getOrderid());
		}
		 
	mn.addObject("multiple", payment1);
	return mn;
}

}

