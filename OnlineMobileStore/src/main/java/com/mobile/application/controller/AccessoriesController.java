package com.mobile.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//import com.mobile.application.model.HeadSet;
import com.mobile.application.model.Image;
import com.mobile.application.model.Item;
//import com.mobile.application.model.PowerBank;
//import com.mobile.application.repository.HeadSetRepository;
import com.mobile.application.repository.ItemRepository;


@Controller
//@RequestMapping("/accessories")

public class AccessoriesController {
	
	@Autowired
	private ItemRepository itemRepository ;

	 //to add the products
	@PostMapping("/saveaccess")
	public String saveaccess(Item i ) {
		
		itemRepository.save(i);
		return "accessories";
	}
	Image image;
	 @RequestMapping("/access")
		public String access()
		{
			return "/access";
		}
	    

	   
	    @RequestMapping("/addaccess")
		public String addaccess()
		{
			return "/addaccess";
		}
	
	@PostMapping("/view-power")
	public ModelAndView power()
	{
		
		List<Item> item = (List <Item>) itemRepository.findAll();

		List<Item> l=new ArrayList<>();
		int p = 17; 
		//String a ="AMBRANE";	
		for (var i: item)
		{ 
			if (i.getProductid()==(p))
			{ 
				l.add(i);
			
			}
		}
		ModelAndView m= new ModelAndView("accessview");
		m.addObject("list", l);
		return m;
	}
	
	

	@PostMapping("/view-headset")
    public ModelAndView headset() {
		
		List<Item> item = (List <Item>) itemRepository.findAll();

		List<Item> l=new ArrayList<>();
		int p = 18; 
		
		for (var i: item)
		{
			if (i.getProductid()==(p))
			{
				l.add(i);
			}
		}
		ModelAndView m= new ModelAndView("accessview");
		m.addObject("list", l);
		return m;
    }

		@PostMapping("/view-charger")
		public ModelAndView charger() {
			List<Item> item = (List <Item>) itemRepository.findAll();

			List<Item> l=new ArrayList<>();
			int p = 19; 
			
			for (var i: item)
			{
				if (i.getProductid()==(p))
				{
					l.add(i);
				}
			}
			ModelAndView m= new ModelAndView("accessview");
			m.addObject("list", l);
			return m;
		}

			@PostMapping("/view-cover")
			public ModelAndView cover() {
				List<Item> item = (List <Item>) itemRepository.findAll();

				List<Item> l=new ArrayList<>();
				int p = 20; 
				
				for (var i: item)
				{
					if (i.getProductid()==(p))
					{
						l.add(i);
					}
				}
				ModelAndView m= new ModelAndView("accessview");
				m.addObject("list", l);
				return m;
			}
			
			@PostMapping("/view-screen")
			public ModelAndView screen() {
				List<Item> item = (List <Item>) itemRepository.findAll();

				List<Item> l=new ArrayList<>();
				int p = 21; 
				
				for (var i: item)
				{
					if (i.getProductid()==(p))
					{
						l.add(i);
					}
				}
				ModelAndView m= new ModelAndView("accessview");
				m.addObject("list", l);
				return m;
			}
		
			@PostMapping("/view-usb")
			public ModelAndView usb() {
				List<Item> item = (List <Item>) itemRepository.findAll();

				List<Item> l=new ArrayList<>();
				int p = 22; 
				
				for (var i: item)
				{
					if (i.getProductid()==(p))
					{
						l.add(i);
					}
				}
				ModelAndView m= new ModelAndView("accessview");
				m.addObject("list", l);
				return m;
			}
//end of accessory
			
			
			@PostMapping("/apple")
		    public ModelAndView apple() {
				
				List<Item> item = (List <Item>) itemRepository.findAll();

				List<Item> l=new ArrayList<>();
				int p = 11; 
				
				for (var i: item)
				{
					if (i.getProductid()==(p))
					{
						l.add(i);
					}
				}
				ModelAndView m= new ModelAndView("accessview");
				m.addObject("list", l);
				return m;
		    }


			
			@PostMapping("/vivo")
			public ModelAndView vivo() {
				
				List<Item> item = (List <Item>) itemRepository.findAll();

				List<Item> l=new ArrayList<>();
				int p = 15; 
				
				for (var i: item)
				{
					if (i.getProductid()==(p))
					{
						l.add(i);
					}
				}
				ModelAndView m= new ModelAndView("accessview");
				m.addObject("list", l);
				return m;
		    }
			
			
			@PostMapping("/realme")
			
			public ModelAndView realme() {
				
				List<Item> item = (List <Item>) itemRepository.findAll();

				List<Item> l=new ArrayList<>();
				int p = 16; 
				
				for (var i: item)
				{
					if (i.getProductid()==(p))
					{
						l.add(i);
					}
				}
				ModelAndView m= new ModelAndView("accessview");
				m.addObject("list", l);
				return m;
		    }
			//4
			@PostMapping("/oneplus")
			public ModelAndView oneplus() {
				
				List<Item> item = (List <Item>) itemRepository.findAll();

				List<Item> l=new ArrayList<>();
				int p = 14; 
				
				for (var i: item)
				{
					if (i.getProductid()==(p))
					{
						l.add(i);
					}
				}
				ModelAndView m= new ModelAndView("accessview");
				m.addObject("list", l);
				return m;
		    }
			//5
			@PostMapping("/samsung")
			public ModelAndView samsung() {
				
				List<Item> item = (List <Item>) itemRepository.findAll();

				List<Item> l=new ArrayList<>();
				int p = 12; 
				
				for (var i: item)
				{
					if (i.getProductid()==(p))
					{
						l.add(i);
					}
				}
				ModelAndView m= new ModelAndView("accessview");
				m.addObject("list", l);
				return m;
		    }
			
			//6
			@PostMapping("/mi")
			public ModelAndView mi() {
				
				List<Item> item = (List <Item>) itemRepository.findAll();

				List<Item> l=new ArrayList<>();
				int p = 13; 
				
				for (var i: item)
				{
					if (i.getProductid()==(p))
					{
						l.add(i);
					}
				}
				ModelAndView m= new ModelAndView("accessview");
				m.addObject("list", l);
				return m;
		    }
			
			
			
			
			
			
			

}
