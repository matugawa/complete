package model;

import java.util.ArrayList;
import java.util.List;

import dao.CategoryDAO;
import dao.ProductDAO;

public class CategoryLogic {
	
	public String inputtedStringCheck( CategoryBeans category) {
		String msg="";
		
		String catId=category.getCategoryBeansId();
		String catName=category.getCategoryBeansName();
		
		if(catId.length()==0 ||catId.length()>10) {
			msg="IDは１文字以上１０文字以内です。<br>";
		}
		if(catName.length()==0 ||catName.length()>50) {
			msg+="カテゴリー名は１文字以上５０字以内です。<br>";
		}
		
		if(msg.length()!=0) {
			return msg;
		}
		
		CategoryDAO dao=new CategoryDAO();
		if( dao.isExistIdorName( category)) {
			msg="既存のID、名前があります<br>";
		}
		
		return msg;
		
		
	}
	
	public boolean registerCategory(CategoryBeans category) {
		CategoryDAO dao=new CategoryDAO();
		return dao.makeCategory(category);
	}
	
	public List<CategoryBeans> getCategoryList(){
		List<CategoryBeans> li=new ArrayList<>();
		
		CategoryDAO dao=new CategoryDAO();
		li=dao.collectCategory();
		return li;
		
		
		
	}
	
	public String editCategory(CategoryBeans category) {
		String msg="";
		CategoryDAO dao=new CategoryDAO();
		if(!dao.updateCategory(category)) {
			msg="編集に失敗しました<br>";
		}
		return msg;
	}
	
	public String delCategory(CategoryBeans category) {
		String msg="";
		CategoryDAO dao=new CategoryDAO();
		if(!dao.deleteCategory(category)) {
			msg="削除に失敗しました<br>";
		}
		return msg;
	}
	
	public ArrayList<String> getDeleteCategoryList(){
		ArrayList<String> cLi=new ArrayList<>();
		ArrayList<String> pLi=new ArrayList<>();


		
		CategoryDAO cDAO=new CategoryDAO();
		cLi=cDAO.collectCategoryId();

		
		ProductDAO pDAO=new ProductDAO();
		pLi=pDAO.collectProductId();
		

		cLi.removeAll(pLi);
		
		return cLi;


	}
	
	

}
