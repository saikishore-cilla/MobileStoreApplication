package com.mobile.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.model.Image;
import com.mobile.application.model.Item;
import com.mobile.application.model.User;
import com.mobile.application.repository.ItemRepository;
import com.mobile.application.service.ItemServicesAdmin;
import com.mobile.application.service.OrderServicesAdmin;
import com.mobile.application.service.UserServicesAdmin;

@Controller
@RequestMapping("/User")
public class AdminController {
	@Autowired
	OrderServicesAdmin orderService;

	@Autowired
	ItemServicesAdmin itemService;
	
	@Autowired
	UserServicesAdmin userService;
	
	@Autowired
	ItemRepository itemRepo;
	
	Image image = new Image();

	
	// Show All Users
		@RequestMapping(value="/users")
		public String userList(ModelMap mod,@SessionAttribute("User")User users ) {
			System.out.println(this.getClass().getSimpleName() + ":=======>Showing Users list.");
			mod.put("users",userService.getAllUsers());
			return "users";
		}
	

	// Show All Orders
	@RequestMapping(value="/ordersadmin")
	public String orderList(ModelMap mod, @SessionAttribute("User")User users ) {
		System.out.println(this.getClass().getSimpleName() + ":=======>Showing Orders list.");
		mod.put("orders",orderService.getAllOrders());
		mod.put("path", "Images/");
		return "ordersadmin";
	}
	
	// Show All Products
	@RequestMapping(value="/products")
	public String productList(ModelMap mod, @SessionAttribute("User")User users ) {
		System.out.println(this.getClass().getSimpleName() + ":=======>Showing Products list.");
		mod.put("products",itemService.getAllItems());
		mod.put("path", "Images/");
		return "products";
	}
	
	//to access Add Product Page
	@RequestMapping("/addproduct")
	public String addP(@SessionAttribute("User")User users) {
		return "addproduct";
	}
	
	//Delete a product brand 
	@RequestMapping("/delete/{model}")
	public ModelAndView deleteItem(@PathVariable("model") int model, @SessionAttribute("User")User users ) {
		System.out.println(this.getClass().getSimpleName() + "Product deleted");
		itemService.deleteItem(model);
		ModelAndView mav = new ModelAndView("products");
		mav.addObject("products", itemService.getAllItems());
		mav.addObject("path", "../Images/");
		return mav;
	}
	
	//To add a new product
	@RequestMapping(value = "/saveitem")
	public ModelAndView addNewProduct(Item  item, @SessionAttribute("User")User users ) {
		ModelAndView mav = new ModelAndView("products");
		List<String> img = image.imageList();
		for(var i : img)
		{
			System.out.println(item.getItemname());
			if(i.equalsIgnoreCase(item.getItemname())) {
				String str = item.getItemname()+".jpg";
				item.setImage(str);;
				break;
			}	
		}
		itemService.saveItem(item);
		System.out.println(this.getClass().getSimpleName() + ":=======>Item saved");
		mav.addObject(item);
		mav.addObject("products", itemService.getAllItems());
		mav.addObject("path", "Images/");
		return mav;
	}
	
	//to access update page
	@RequestMapping(value="/update/{model}", method=RequestMethod.GET)
	 public ModelAndView updateItem(@PathVariable int model, @SessionAttribute("User")User users ) {
	  ModelAndView mod = new ModelAndView();
	  Item items=itemService.getItemByModel(model);
	  mod.addObject("item", items);
	  mod.setViewName("update");
	  return mod;
	 }	 
	
	//to save the updated data
	@RequestMapping(value = "/savep", method={RequestMethod.GET,RequestMethod.POST})
	public String saveUpdate(@ModelAttribute("item") Item item, Model mod, @SessionAttribute("User")User users ) {
		System.out.println(this.getClass().getSimpleName() + ":=======>Updated data saved.");
		itemService.saveItem(item);
		mod.addAttribute("products", itemService.getAllItems());
		return "products";
	}
}
