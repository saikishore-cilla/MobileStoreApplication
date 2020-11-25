package com.mobile.application.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.repository.ItemRepository;
import com.mobile.application.model.Item;
import com.mobile.application.model.User;

@Controller
@RequestMapping("/User") 
public class SearchController {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@RequestMapping("/searchItem")
	public ModelAndView searchDish(@SessionAttribute("User")User user) {
		ModelAndView mv = new ModelAndView("Search");
	
		  if(user.getEmail()==null) mv.addObject("msg",null);
		
		return mv;
	}
	//1
	
	
	
	@RequestMapping("/searchOprn")
	public ModelAndView searchDishOpr(String search_Item,@SessionAttribute("User")User user) {
		
		List<Item> item = (List<Item>) itemRepo.findAll();
		List<Item> Res = new ArrayList<Item>();
		
		for(var li : item)	
		{
			if((li.getItemname().contains(search_Item.toUpperCase())))
				Res.add(li);
		}
		
		System.out.println(Res);	
		String email=user.getEmail();
		ModelAndView mv = new ModelAndView("Search");
		mv.addObject("email",email);
		mv.addObject("SearchRes",Res);
		mv.addObject("search_key", search_Item);
		  if(user.getEmail()==null) mv.addObject("msg",null);
			
		if(Res.isEmpty())
		{
			mv.addObject("msg", "No Result Found for Search ");
		}
		return mv;
	}

}