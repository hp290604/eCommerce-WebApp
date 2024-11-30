package com.sheryians.major.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Product;
import com.sheryians.major.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepository;
	public List<Product> getAllProducts(){
		return this.productRepository.findAll();
	}
	public Product getProductById(long id) {
		Optional<Product> op=this.productRepository.findById(id);
		return op.get();
	}
	public void addProduct(Product product) {
		this.productRepository.save(product);
	}
	public void deleteProduct(long id) {
		this.productRepository.deleteById(id);
	}
	public List<Product> getProductByCategoryId(int id){
		List<Product> ans=this.productRepository.findAll();
		List<Product> list=new ArrayList<>();
		for(Product p:ans) {
			if(p.getCategoryId()==id) {
				list.add(p);
			}
		}
//		for(Product pro:ans) {
//			if(pro.getCategoryId()!=id)ans.remove(pro);
//		}
//		ans.clear();
		return list;
//		return this.productRepository.findByCategoryId(id);
	}
}
