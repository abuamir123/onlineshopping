package com.info.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.Product;
import com.info.model.User;
import com.info.service.ProductService;
import com.info.service.UserService;

@Controller
@RequestMapping("profile")
public class ProfileController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
//	SpringS
	
//	@GetMapping("index")
//	public String index() {
//		return "profile/index";
//	}
	
	@GetMapping("index")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("profile/index");
		mv.addObject("productList", productService.listProduct());
		return mv;
	}
	
	@GetMapping("cart-product")
	public ModelAndView cartProduct(Principal principal) {
		ModelAndView mv = new ModelAndView("profile/cart-product");
		User user = userService.findByEmail(principal.getName());
//		List<Product> productArray = new ArrayList<Product>();
//		for (Product string1 : user.getProductList()) {
//			productArray.add(string1);
//		}
		mv.addObject("userProduct", user.getProductList());
		return mv;
	}
	
	@GetMapping("addToCart/{productId}")
	public ModelAndView addToCart(@PathVariable("productId")String productId,Principal principal) {
		ModelAndView mv = new ModelAndView("profile/cart-product");
		User user = userService.findByEmail(principal.getName());
		long productLongId = Long.parseLong(productId);
		Product product = productService.getProductById(productLongId).get();
		
		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		user.setProductList(productList);
		
		List<User> userList = new ArrayList<>();
		userList.add(user);
		product.setUserList(userList);
		
		userService.update(user);
		productService.addProduct(product);
//		mv.addObject("userProduct", user.getProductList());
		
		return mv;
	}

}
