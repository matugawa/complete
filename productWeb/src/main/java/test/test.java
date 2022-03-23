package test;

import java.util.ArrayList;

import dao.CategoryDAO;
import dao.ProductDAO;

public class test {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		

		
		ArrayList<String> li=new ArrayList<>();
		ArrayList<String> lii=new ArrayList<>();


		
		CategoryDAO dao=new CategoryDAO();
		li=dao.collectCategoryId();
		for(String q: li) {
			System.out.println(q);
		}
		
		ProductDAO daoo=new ProductDAO();
		lii=daoo.collectProductId();
		
		for(String q: lii) {
			System.out.println(q);
		}
		
		li.removeAll(lii);
		System.out.println();

		for(String q: li) {
			System.out.println(q);
		}
	
	}

}
