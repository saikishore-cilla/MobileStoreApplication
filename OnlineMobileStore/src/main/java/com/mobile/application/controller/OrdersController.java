package com.mobile.application.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mobile.application.model.Cart;
import com.mobile.application.model.Item;
import com.mobile.application.model.Orders;
import com.mobile.application.model.Payment;
import com.mobile.application.model.User;
import com.mobile.application.repository.CartRepository;
import com.mobile.application.repository.ItemRepository;
import com.mobile.application.repository.OrdersRepository;
import com.mobile.application.repository.PaymentRepository;


@Controller
@RequestMapping("/User") 

public class OrdersController {
	
	@Autowired
	private OrdersRepository orderRepository;
	@Autowired 
	  private CartRepository cartRepository; 
	
//Fine!!! when item move from cart to orders . we should r0emove that item from the cart
//Multiple Orders
	

	@Autowired
	private ItemRepository itemRepository ;
	
	@PostMapping(value="/save-orders" )
	  public ModelAndView getDatas(Model models,@SessionAttribute("User")User users) {
		
		List<Cart> cartvalue = (List<Cart>)cartRepository.findAll();
		List<Cart> cart = new ArrayList<Cart>();
		String email=users.getEmail();
		for(var i:cartvalue)
		{
			if(email!=null)
				cart.add(i);
		}
		int tot=0;
		System.out.println(cart);
		for(var j:cart)
		{
			Orders o=new Orders();
			o.setAddress("IN");
			o.setEmail(email);
			o.setItemname(j.getItemname());
			o.setModel(j.getModel());
			o.setQuantity(j.getQuantity());
			o.setTotal(j.getTotal());
			int t=j.getTotal();
			tot=tot+t;
			orderRepository.save(o);
			cartRepository.deleteById(j.getCartid());
			Item item= itemRepository.findById(j.getModel()).get();
			item.setQuantity_available(item.getQuantity_available()-j.getQuantity());
			itemRepository.save(item);
		}
		System.out.println("total "+tot);
	
		List<Orders>o=orderRepository.findAll();
		List<Orders>order=new ArrayList<Orders>();
		for(var i:o)
		{
			if(i.getEmail().equals(email))
			{
				order.add(i);
			}
		}
		ModelAndView mn=new ModelAndView("orders2");
		mn.addObject("multiple", order);
		mn.addObject("total", tot);
		
		return mn;
	}		
		
	//remove orders
	@PostMapping("/removeO/{orderid}")
	public ModelAndView remove(@PathVariable("orderid") int orderid,@SessionAttribute("User")User user) {
		//cartService.removefromcart(cartid);
		Orders value = orderRepository.getOne(Integer.valueOf(orderid));
		System.out.println("start remove");
		int id=value.getOrderid();
		//cartService.removefromcart(cartid);
		orderRepository.deleteById(id);
		System.out.println("Deleted");
		System.out.println(user.getEmail());
		
		System.out.println("The Email of session :"+user.getEmail());
		List<Orders> cart=orderRepository.findAll();
	      System.out.println("fine  getcart");
	      List<Orders> newCart=new ArrayList<>();
		  	for(var li : cart)	
			{
				if((li.getEmail().equals(user.getEmail())))
					newCart.add(li);
				
			}
			/*
			 * if(email==null) { return "login"; }
			 */
		  	
		  ModelAndView model=new ModelAndView("orders2");
	      System.out.println(newCart);
	      model.addObject("cart", newCart);
	      return model;

	}

    
    //working Fine
 // Show All Orders
 		@RequestMapping("/allorder")
 	    public String home(Model model,@SessionAttribute("User")User users) {
 			
 			System.out.println(this.getClass().getSimpleName() + ":=======>Showing Orders list.");
 			List<Orders> o=(List<Orders>) orderRepository.findAll();
 			model.addAttribute("list",o );
 	         return "allorder";
 	    }
 	    
 		
 		//user orders
 		@Autowired
 		private PaymentRepository paymentRepository;
 		  @PostMapping(value="/getorder")
 		  public String getOrderList(Model model, @SessionAttribute("User")User users) {
 		      List<Payment> cart=(List<Payment>) paymentRepository.findAll();
 		      System.out.println("fine  Orders");
 		      String email=users.getEmail();
 		      List<Payment> newOrders=new ArrayList<>();
 			  	for(var li : cart)	
 				{
 					if((li.getEmail().equals(email)))
 						newOrders.add(li);
 				}
 		      System.out.println(newOrders);
 		      model.addAttribute("list", newOrders);
 		      return "allorder";

 		  }
 		  //okay

    
	
}
