package com.sheryians.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sheryians.major.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
//	@Query("select * from Product where category_id=:id")
	public List<Product>  findByCategoryId(int categoryId);
	
}
