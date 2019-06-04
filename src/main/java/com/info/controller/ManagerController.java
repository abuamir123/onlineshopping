package com.info.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.info.model.Category;
import com.info.model.Product;
import com.info.service.CategoryService;
import com.info.service.FileUploadService;
import com.info.service.ProductService;

@Controller
@RequestMapping("manager")
public class ManagerController {

//	public static String uploadDirectory = System.getProperty("user.dir") + 
//			"/src/main/resources/statics/images";

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileUploadService fileUploadService;

	@GetMapping("index")
	public String index() {
		return "manager/index";
	}

//	For Category--------------------------------------------------
	@GetMapping("category-form")
	public ModelAndView listCategory() {
		ModelAndView mv = new ModelAndView("manager/category-form");
		mv.addObject("categoryList", categoryService.listCategory());
		return mv;
	}

	@PostMapping("add-category")
	public ModelAndView addCategory(Category category) {
		ModelAndView mv = new ModelAndView("manager/category-form");
		categoryService.addCategory(category);
		mv.addObject("categoryList", categoryService.listCategory());
		return mv;
	}

//	For Product--------------------------------------------------
	@GetMapping("product-form")
	public ModelAndView listProduct() {
		ModelAndView mv = new ModelAndView("manager/product-form");
		mv.addObject("categoryList", categoryService.listCategory());
		mv.addObject("productList", productService.listProduct());
		return mv;
	}
	
//	@PostMapping("add-product")
//	public String addProduct(@RequestParam("file") MultipartFile file, Model model,Product product) {
//		System.out.println("file: " + file.getOriginalFilename());
//		String filePath = fileUploadService.upload(file);
//		System.out.println(filePath);
//		product.setImage(filePath);
//		
//		System.out.println(product.getImage());
//		
//		productService.addProduct(product);
//		return "redirect:/manager/product-form";
//	}

	@PostMapping("add-product")
	public ModelAndView addProduct(Product product, @RequestParam("file") MultipartFile file) {
		ModelAndView mv = new ModelAndView("manager/product-form");
		System.out.println("file: " + file.getOriginalFilename());
		String filePath = fileUploadService.upload(file);
		product.setImage(filePath);
		
		System.out.println(product.getImage());
		
		productService.addProduct(product);
		mv.addObject("productList", productService.listProduct());
		return mv;
	}

}
