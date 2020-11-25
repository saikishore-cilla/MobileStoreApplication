package com.mobile.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mobile.application.model.Cart;
import com.mobile.application.model.Item;
import com.mobile.application.repository.CartRepository;
import com.mobile.application.service.ItemService;

@Controller
	public class ApplicationController {
	 
	@RequestMapping("/home")
	    public String home() {
	   
	        return "home";
	    }
	    
	    
	    @RequestMapping("/login")
	    public String login() {
	   
	        return "login";
	    }
	    
	    @RequestMapping("/register")
	    public String register() {
	   
	        return "register";
	    }
	    
	    @RequestMapping("/welcome")
	    public String welcome() {
	   
	        return "welcome";
	    }
	    @RequestMapping("admin")
	    public String admin() {
	    	return "admin";
	    }
	    @RequestMapping("adminlogin")
	    public String adminlog() {
	    	return "adminlogin";
	    }
	    
	    @RequestMapping("/cart")
		public String cart()
		{
			return "/cart";
		}
	    
	    @RequestMapping("/Contact")
	    public String contact() {
	   
	        return "Contact";
	    }
	    
	    @RequestMapping("/thanku")
	    public String thanku() {
	   
	        return "thanku";
	    }
		
	    @Autowired 
	    private CartRepository cartRepository;    
	    
		@PostMapping("/remove/{cartid}")
		public String remove(@PathVariable("cartid") int cartid,Model model) {
			//cartService.removefromcart(cartid);
			Cart cartvalue = cartRepository.getOne(Integer.valueOf(cartid));
			System.out.println("start remove");
			int cid=cartvalue.getCartid();
			//cartService.removefromcart(cartid);
			cartRepository.deleteById(cid);
			System.out.println("Deleted");
			return "remove";
		}

    
		@RequestMapping("/mobile")
		public String mobile() {
			return "mobile";
		}
	    
		  @Autowired
			 private  ItemService itemService;
		//adding to cart 
		//  @PostMapping(value="/addcart/{modelType}" )
		  public String getData(@PathVariable ("modelType") int modelType ,Model models) {
				 Item itemdta = itemService.findById(Integer.valueOf(modelType));
		      models.addAttribute("itemdta", itemdta);
		    	  
		      return "addcart";

		  }

   }    
	    
	    
	