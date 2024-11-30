package com.sheryians.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sheryians.major.global.GlobalData;
import com.sheryians.major.model.Category;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/", "/home"})
	public String home(Model model) {
		return "index";
	}
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("products",productService.getAllProducts());
		model.addAttribute("cartCount",GlobalData.cart.size());
		return "shop";
	}
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model,@PathVariable("id") int id) {
		model.addAttribute("categories",categoryService.getAllCategory());
		System.out.println(productService.getProductByCategoryId(id));
		model.addAttribute("products",productService.getProductByCategoryId(id));
		return "shop";
	}
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable("id") int id,Model model) {
		model.addAttribute("product",this.productService.getProductById(id));
		return "viewProduct";
	}
}
