package com.mobile.application.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="multipleorders")

public class MultipleCart {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Column(name="multipleid")
	private int multipleid;
	
	@Column(name="cartid")
	private int cartid;
	@Column(name="email")
	private String email;
	@Column(name="model")
	private int model;
	@Column(name="quantity")
	private int quantity;
	@Column(name="price")
	private int price;
	@Column(name="total")
	private int total;
	@Column(name="itemname")
	private String itemname;
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public MultipleCart(int cartid, String email, int model, int quantity, int price, int total, String itemname) {
		super();
		this.cartid = cartid;
		this.email = email;
		this.model = model;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.itemname = itemname;
	}
	public MultipleCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MultipleCart [multipleid=" + multipleid + ", cartid=" + cartid + ", email=" + email + ", model=" + model
				+ ", quantity=" + quantity + ", price=" + price + ", total=" + total + ", itemname=" + itemname + "]";
	}
	
	
	
}
