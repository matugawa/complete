package model;

import java.io.Serializable;

public class CategoryBeans implements Serializable{
	
	private String categoryId;
	private String categoryName;
	
	public CategoryBeans() {
	}
	
	public CategoryBeans(String id, String name) {
		this.categoryId=id;
		this.categoryName=name;
	}
	
	public void viewCategoryBeans() {
		System.out.println(this.categoryId+" "+this.categoryName);
	}
	
	
	public String getCategoryBeansId() {
		return categoryId;
	}

	public void setCategoryBeansId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryBeansName() {
		return categoryName;
	}

	public void setCategoryBeansName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
