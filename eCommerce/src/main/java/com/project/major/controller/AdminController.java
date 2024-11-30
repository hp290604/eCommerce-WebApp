package com.sheryians.major.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sheryians.major.dto.ProductDTO;
import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;

@Controller
public class AdminController {
	public static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	private Category category;
//	private Category category;
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
//	@GetMapping("/admin/categories")
//	public String getCategories() {
////		model.addAttribute("categories",categoryService.getAllCategory());
//		return "buildNewCategory";
//	}
	@GetMapping("/admin/categories")
	public String getCateory(Model model) {
		model.addAttribute("categories",categoryService.getAllCategory());
		return "categories";
	}
	@GetMapping("/admin/categories/add")
	public String addCategory(Model model) {
		model.addAttribute("category",new Category());
		return "buildNewCategory";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String delCategory(@PathVariable("id") int id) {
		this.categoryService.removeCategoryById(id);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable("id")int id,Model model) {
		Optional<Category> category = categoryService.getCategoryById(id);
		if(category.isPresent()) {
			model.addAttribute("category",category.get());
			return "buildNewCategory";
		}
		return "404";
	}
	@PostMapping("/admin/categories/add")
	public String postCategory(@ModelAttribute("category") Category category) {
		this.category = category;
		System.out.println(this.category.getId());
		System.out.println(this.category.getName());
		try {
			this.categoryService.addCategory(category);
			System.out.println(this.categoryService.getAllCategory());
		}finally {
			return "redirect:/admin/categories";
		}
//		categoryService.addCategory(this.category);
		
	}
	
	//Product Section
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("Product",productService.getAllProducts());
//		productService.getAllProducts();
//		List<Product> list=productService.getAllProducts();
//		for(Product product:list) {
//			System.out.println(product.getName()+" "+product.getCategory()+" "+product.getPrice());
//		}
		return "products";
	}
	@GetMapping("/admin/products/add")
	public String addProduct(Model model) {
		model.addAttribute("productDTO",new ProductDTO());
		model.addAttribute("categories",categoryService.getAllCategory());
		return "productsAdd";
	}
	@PostMapping("/admin/products/add")
	public String addNewProduct(@ModelAttribute("ProductDTO")ProductDTO productDTO,@RequestParam("imgName")String imgName,@RequestParam("productImage") MultipartFile file) throws IOException {
		
		Product product=new Product();
		product.setName(productDTO.getname());
		product.setId(productDTO.getId());
		product.setCategoryId(productDTO.getcategoryId());
		Optional<Category> list=categoryService.getCategoryById(productDTO.getcategoryId());
		List<Category> ans=new ArrayList<>();
		list.ifPresent(ans::add);
		Category cat=ans.get(0);
		product.setCategory(cat.getName());
		product.setDescription(productDTO.getDescription());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		String imageUUId;
		if(!file.isEmpty()) {
			imageUUId=file.getOriginalFilename();
			Path filepath=Paths.get(uploadDir,imageUUId);
			Files.write(filepath,file.getBytes());
		}
		else {
			imageUUId=imgName;
		}
		product.setImageName(imageUUId);
		productService.addProduct(product);
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id) {
		this.productService.deleteProduct(id);
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(Model model,@PathVariable("id") long id) {
		Product tempProduct=this.productService.getProductById(id);
		model.addAttribute("categories",categoryService.getAllCategory());
		ProductDTO productDTO=new ProductDTO();
		productDTO.setDescription(tempProduct.getDescription());
		productDTO.setId(tempProduct.getId());
		productDTO.setcategoryId(tempProduct.getCategoryId());
		productDTO.setImageName(tempProduct.getImageName());
		productDTO.setPrice(tempProduct.getPrice());
		productDTO.setWeight(tempProduct.getWeight());
		model.addAttribute(productDTO);
		productDTO.setname(tempProduct.getName());
		return "productsAdd";
	}
	
	
//	@GetMapping("/admin/categories/add")
//	public String getCat( Model model) {
//		model.addAttribute("category",new Category());
//		return "categoriesAdd";
//	}
//	@PostMapping("/admin/categories/add")
//	public String postCatAdd( @ModelAttribute("category") Category category) {
//		
////		this.category = category;
//		categoryService.addCategory(category);
//		return "redirect:/admin/categories";
//	}
	
}
