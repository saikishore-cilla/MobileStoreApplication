
  package com.mobile.application.controller;
  
  
  
  import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileSystemUtils;
import
  org.springframework.web.bind.annotation.PathVariable; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import  org.springframework.web.servlet.ModelAndView;
  
import com.mobile.application.model.Cart; 
import com.mobile.application.model.Item;
import com.mobile.application.model.Orders;
import com.mobile.application.model.Payment;
import com.mobile.application.model.User;
import com.mobile.application.repository.CartRepository;
import com.mobile.application.repository.OrdersRepository;
import com.mobile.application.repository.PaymentRepository;
import com.mobile.application.service.CartService;
import com.mobile.application.service.ItemService;

  
  
  @Controller
  @RequestMapping("/User") 
  public class MultipleCartController {
	  @Autowired
	 private  ItemService itemService;
  
  @Autowired 
  private CartRepository cartRepository; 
  private CartService cartService;

	
	@Autowired
	private PaymentRepository paymentRepository;

//adding to cart 
  @PostMapping(value="/addcart/{modelType}" )
  public String getData(@PathVariable ("modelType") int modelType ,Model models,@SessionAttribute("User")User users) {
		 Item itemdta = itemService.findById(Integer.valueOf(modelType));
      
      String email=users.getEmail();
      System.out.println(email);
      System.out.println("Session");
    	System.out.println("ADDCART NA");
    	models.addAttribute("itemdta", itemdta);
        models.addAttribute("email", email);
      return "addcart";

  }
  
//saving the cart details to the cart table  
	@PostMapping(value="/saveToCart" )
    public String saveData(Cart cart,@RequestParam("model") int model,
  		@RequestParam("price") int price,
  		@RequestParam("qty") int qty,@RequestParam("total") int total,Model mode,@SessionAttribute("User")User users, Model model1) {
		 Item itemdta = itemService.findById(Integer.valueOf(model));
		 if(itemdta.getQuantity_available() < qty) {
			 return "page";
		 }
		 else {
		String email=users.getEmail();
		cart.setEmail(email);
		cart.setModel(model);
		cart.setPrice(price);
		cart.setQuantity(qty);
		cart.setTotal(total);
		System.out.println(cart);
		System.out.println(cartRepository.save(cart));
		
		//cartService.addToCart(cart);
		mode.addAttribute("email", cart);
		
		
		
		List<Cart> cart1=cartRepository.findAll();
	      System.out.println("fine  getcart");
	      List<Cart> newCart=new ArrayList<>();
	      System.out.println(email);
	      
		  	for(var li : cart1)	
			{
				if((li.getEmail().equals(email)))
					newCart.add(li);
				
			}
			/*
			 * if(email==null) { return "login"; }
			 */
		  	
		  	
	      System.out.println(newCart);
	      model1.addAttribute("cart", newCart);
	       return "mycart2";
		 }
//////change to mycart2

    }
  //fine
	
  @PostMapping(value="/getcart")
  public String getCartList(Model model,@SessionAttribute("User")User users) {
      List<Cart> cart=cartRepository.findAll();
      System.out.println("fine  getcart");
      List<Cart> newCart=new ArrayList<>();
      String email=users.getEmail();
      System.out.println(email);
      
	  	for(var li : cart)	
		{
			if((li.getEmail().equals(email)))
				newCart.add(li);
			
		}
		/*
		 * if(email==null) { return "login"; }
		 */
	  	
	  	
      System.out.println(newCart);
      model.addAttribute("cart", newCart);
      return "mycart";

  }
  //okay

	@PostMapping("/remove/{cartid}")
	public ModelAndView remove(@PathVariable("cartid") int cartid,@SessionAttribute("User")User user) {
		//cartService.removefromcart(cartid);
		Cart cartvalue = cartRepository.getOne(Integer.valueOf(cartid));
		System.out.println("start remove");
		int cid=cartvalue.getCartid();
		//cartService.removefromcart(cartid);
		cartRepository.deleteById(cid);
		System.out.println("Deleted");
		System.out.println(user.getEmail());
		System.out.println("The Email of session :"+user.getEmail());
		List<Cart> cart=cartRepository.findAll();
	      System.out.println("fine  getcart");
	      List<Cart> newCart=new ArrayList<>();
		  	for(var li : cart)	
			{
				if((li.getEmail().equals(user.getEmail())))
					newCart.add(li);
				
			}
		  	
		  	ModelAndView model=new ModelAndView("mycart");
	      System.out.println(newCart);
	      model.addObject("cart", newCart);
	      return model;

	}
  
  
  
// Show All Orders
  @RequestMapping("/allcart")
  public ModelAndView home(Model model,@SessionAttribute("User")User user) {
  	
  	System.out.println(this.getClass().getSimpleName() + ":=======>Showing cart list.");
  	List<Cart> c=(List<Cart>) cartRepository.findAll();
  	
  	List<Cart> Res = new ArrayList<Cart>();
  	for(var li : c)	
  	{
  		if((li.getEmail().equals(user.getEmail())))
  			Res.add(li);
  	}
  	System.out.println(user.getEmail());
  	System.out.println(Res);	
  	ModelAndView mv = new ModelAndView("allcart");
  	mv.addObject("list",Res);
  	mv.addObject("search_key", user.getEmail());
  	/*
  	 * if(user.getId()!=0) mv.addObject("msg1","notNull"); else mv.addObject("msg1",
  	 * null);
  	 */
  	if(Res.isEmpty())
  	{
  		mv.addObject("msg", "No Result Found for Search ");
  	}
  	return mv;
  }


	//user payment orders
	//  @PostMapping(value="/getorder")
	  public String getOrderList(Model model, @SessionAttribute("User")User users) {
	      List<Payment> cart=(List<Payment>) paymentRepository.findAll();
	      System.out.println("fine Payment  Orders");
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
	  //ok
  
  
 
  }


