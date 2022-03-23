package model;

import java.util.ArrayList;
import java.util.List;

import dao.ProductDAO;

public class ProductLogic {
	
	public String preProductCheck(ProductBeans product) {
		String msg="";
		
		String productId=product.getProductId();
		String productName=product.getProductName();
		
		if(productId.length()==0 ||productId.length()>10) {
			msg="IDは１文字以上１０文字以内です。<br>";
		}
		if(productName.length()==0 ||productName.length()>50) {
			msg+="名前は１文字以上５０文字以内です。<br>";
		}
		
		ProductDAO dao=new ProductDAO();
		msg+=dao.isExistProduct(product);
		
		return msg;
		

	}
	
	public boolean registerProduct(ProductBeans product) {
		ProductDAO dao=new ProductDAO();
		return dao.makeProduct(product);
		
	}
	
	public List<ProductBeans> getProductList(){
		List<ProductBeans> li=new ArrayList<>();
		
		ProductDAO dao=new ProductDAO();
		li=dao.collectProduct();
		return li;
		
		
		
	}
	
	
	public String delProduct(ProductBeans product) {
		String msg="";
		ProductDAO dao=new ProductDAO();
		if(!dao.deleteProduct(product)) {
			msg="削除に失敗しました<br>";
		}
		return msg;
	}
	
	public String editProduct(ProductBeans product) {
		String msg="";
		
		String productName=product.getProductName();
		if(productName.length()==0 ||productName.length()>50) {
			msg+="名前は１文字以上５０文字以内です。<br>";
			return msg;
		}
		
		ProductDAO dao=new ProductDAO();
		
		msg=dao.updateProduct(product);
		

		return msg;
	}
	
	

}
