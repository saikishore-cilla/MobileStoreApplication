<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!--<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Display Page</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
.d input[type="submit"] {
  border: black;
  outline: black;
  color: white;
  background-color: #1a1a1a;
  padding: 1.22rem 2.0rem;
  cursor: pointer;
  border-radius: 0.312rem;
  font-size: 1.7rem;
}

</style>
</head>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Online Shop</a>
			</div>
			 <ul class="nav navbar-nav">
      <li class="active"><a href="#">Search </a> </li>
      <li><a href="/mobile">Mobile</a></li>
      <li><a href="/access">Accessories</a></li>
<!--       <li><a href="/login">Login</a></li>
      <li><a href="/register">Register</a></li>
      
       <form action="/User/getcart/ ${l.email}" method="post">
       <input  type="submit" value="Cart"></form> -->
       
      <li><div class="d">
       <form action="/User/getcart/" method="post">
      <%-- <a href="../User/getcart/ ${l.email}"> --%>
       <input  type="submit" value="Cart"></form></div>
        </li>
        
         <li><div class="d">
       <form action="/User/getorder/" method="post">
      <%-- <a href="../User/getcart/ ${l.email}"> --%>
       <input  type="submit" value="MY Orders"></form></div>
        </li>
       
       
      <!--   <li><a href="/allorder">AllOrders</a></li> -->
    
      <li ><a href="/User/logout">logout</a></li>
    </ul>
  </div></nav>

<div class ="x">
   <c:if test="${SearchRes.isEmpty()}">
 	 <c:if test="${msg!=null}"><center>
 	${msg} <b>${search_key}</b></center></c:if>
 	</c:if>
 	<c:if test="${!SearchRes.isEmpty()}">
 	<center> Search Results for <b>${search_key}</center></b><br>
 	 <center>
 <div class="container">
		<div class="starter-template">
			<h1>Search List</h1>	${email}<br>
			<table
				class="table table-striped table-hover table-condensed table-bordered" border="1">
		
				<tr>
		
	    <th>Image</th> 
	 		<th>Model</th>  	
			<th>Name</th>
			<th>Color</th>
			<th>price</th>
			<th>Features</th> 
			<th>Add to Cart</th>
			
		</tr>
		<c:forEach var="Item" items="${SearchRes}">
			<tr>
			
		 	<td><img src="/Images/${Item.itemname}.jpg" alt="NoImg" border=3 height=100 width=100/></td>
			 <td>${Item.model }</td>
				<td>${Item.itemname }</td>
				<td>${Item.color }</td>
				<td>${Item.price }</td>
				<td>${Item.features }</td> 
				<td>
   				<Form action="../../User/addcart/${Item.model}" method="post">
			   	<button>Add to Cart</button>
   				</Form>
   				</td>	
			</tr>
		</c:forEach>
	</table></center></c:if>
	
 	</div>



</body>
</html>