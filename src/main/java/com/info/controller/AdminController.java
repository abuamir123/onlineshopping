package com.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.Product;
import com.info.service.FileUploadService;
import com.info.service.ProductService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileUploadService fileUploadService;

	@GetMapping("index")
	public String index() {
		return "admin/index";
	}


	@GetMapping("product-form")
	public ModelAndView listProduct() {
		ModelAndView mv = new ModelAndView("/product-form");
		
		mv.addObject("productList", productService.listProduct());
		return mv;
	}
	@GetMapping("/addProduct")
	public ModelAndView getAddProductPage() {
		ModelAndView modelAndView=new ModelAndView("add-product");
		return modelAndView;
	}

	@PostMapping("add-product")
	public ModelAndView addProduct(Product product,  @RequestParam("file") MultipartFile file) {
		ModelAndView mv = new ModelAndView("/product-form");
		System.out.println("file: " + file.getOriginalFilename());
		String filePath = fileUploadService.upload(file);
		product.setImage(filePath);
		
		System.out.println(product.getImage());
		
		productService.addProduct(product);
		mv.addObject("productList", productService.listProduct());
		return mv;
	}
	
	@GetMapping("delete-Product/{productId}")
	public ModelAndView deleteProduct(@PathVariable("productId")String productId) {
		ModelAndView mv = new ModelAndView("/product-form");
		productService.deleteProduct(Long.parseLong(productId));
		mv.addObject("productList", productService.listProduct());
		return mv;
	}
	
	@GetMapping("updateProduct/{productId}")
	public ModelAndView updateProduct(@PathVariable("productId")String productId) {
		ModelAndView mv = new ModelAndView("/updateProduct");
		
		mv.addObject("Product", productService.getProductById(Long.parseLong(productId)).get());
		return mv;
	}

}
