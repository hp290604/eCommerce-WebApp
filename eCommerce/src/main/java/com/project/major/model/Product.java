package com.sheryians.major.model;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	int categoryId;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public Product() {
		super();
	}
	public String category;
	String name;
	double weight;
	double price;
	String description;
	String imageName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public String getCategory() {
		// TODO Auto-generated method stub
		return this.category;
	}
	public void setCategory(String category) {
		this.category=category;
	}
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	 Long id;
//	 String name;
//	 double price;
//	 double weight;
//	 String description;
//	 String imageName;
//	public Product(Long id, String name, Category category, double price, double weight, String description,
//			String imageName) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.category = category;
//		this.price = price;
//		this.weight = weight;
//		this.description = description;
//		this.imageName = imageName;
//	}
//	public Product() {
//		super();
//	}
//	public Long getId() {
//		return id;
//	}
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getname() {
//		return name;
//	}
//	public void setname(String name) {
//		this.name = name;
//	}
//	public Category getCategory() {
//		return category;
//	}
//	public void setCategory(Optional<Category> optional) {
//		this.category = optional.get();
//	}
//	public double getPrice() {
//		return price;
//	}
//	public void setPrice(double price) {
//		this.price = price;
//	}
//	public double getWeight() {
//		return weight;
//	}
//	public void setWeight(double weight) {
//		this.weight = weight;
//	}
//	public String getDescription() {
//		return description;
//	}
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	public String getImageName() {
//		return imageName;
//	}
//	public void setImageName(String imageName) {
//		this.imageName = imageName;
//	}
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="category_id",referencedColumnName = "category_id")
//	 Category category;
//	 
//	
//	
	
}
