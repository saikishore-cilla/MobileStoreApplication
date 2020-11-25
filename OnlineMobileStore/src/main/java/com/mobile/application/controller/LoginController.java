package com.mobile.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.model.User;
import com.mobile.application.repository.UserRepository;
import com.mobile.application.service.UserService;


	@Controller
	//@RequestMapping(path="/home")
	@SessionAttributes("User")
	public class LoginController {
		
		@ModelAttribute("User")
		public User setUp()
		{
			return new User();		
		}
		
		
		
		@Autowired
		private UserRepository userRepository; 
		private UserService userService;
		
		//Registration	
		@PostMapping(path="/save-user")
		public ModelAndView registerUser(User user) {
		User u=	userRepository.save(user);
	    ModelAndView m=new  ModelAndView("home")	;
	    m.addObject("l", u);
			return m;

	}
	
		@PostMapping("/validateuser")
		public ModelAndView ValidateUser(@ModelAttribute("User") User user, Model model) {
			 List<User> l = (List<User>) userRepository.findAll();
			 String ret = "login";
			
			 for(var l1:l)
			 {	 
				 if(l1.getEmail().equals(user.getEmail()))
				 { 
					 System.out.println(l1);
					 
					if((l1.getPassword().equals(user.getPassword())))
					{
						ret = "welcome";
					}
					else ret = "login";
				 } 
			 }	
			ModelAndView mode = new ModelAndView(ret);
			mode.addObject("l", user);
		    mode.addObject("message","Enter Valid Credentials");
		    mode.addObject("msg", "USER");
			return mode;
		}

		
		//Login	working  ,BindingResult bindingResult


  	 
	    
	    @PostMapping(path="/validateadmin") 
	    public ModelAndView  adminLogin(@ModelAttribute("User") User user,
		  BindingResult bindingResult) 
	    {
			 List<User> l = (List<User>) userRepository.findAll();
			 String ret = "login";
			
			 for(var l1:l)
			 {	 
				 if(l1.getEmail().equals(user.getEmail()))
				 { 
					 System.out.println(l1);
					 
					if((l1.getPassword().equals(user.getPassword()) && l1.getRolename().equals("Admin")))
					{
						ret = "adminlogin";
					}
					else ret = "login";
				 } 
			 }	
			ModelAndView mode = new ModelAndView(ret);
			mode.addObject("l", user);
		    mode.addObject("message","Enter Valid Credentials");
		    mode.addObject("msg", "USER");
			return mode;
		}
	    
  
	
	}