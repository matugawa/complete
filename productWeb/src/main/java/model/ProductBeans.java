package model;

import java.io.Serializable;

public class ProductBeans implements Serializable{
	private String productId;
	private String categoryId;
	

	private String productName;
	private int price;
	
	public ProductBeans() {
	}
	
	public ProductBeans(String productid, String categoryid,
			String productname, int price) {
		
		this.categoryId=categoryid;
		this.productId=productid;
		this.productName=productname;
		this.price=price;
	}
	
	
	public void viewProductBeans() {
		System.out.println(this.productId+" "+this.categoryId+" "
				+this.productName+" "+this.price);
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
