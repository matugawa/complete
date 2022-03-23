package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ProductBeans;

public class ProductDAO {
	
	private final String JDBC_URL=
			"jdbc:mysql://localhost:3306/product_web_db";
	private final String DB_USER="mysql";
	private final String DB_PASS="mysql";
	
	
	public boolean makeProduct(ProductBeans product) {
		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			
			String sql="insert into product( product_id,"
					+ "category_id,"
					+ "product_name,"
					+ "price) values(?, ?, ?, ?)";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setString(1, product.getProductId());
			pStmt.setString(2, product.getCategoryId());
			pStmt.setString(3, product.getProductName());
			pStmt.setInt(4, product.getPrice());
			
			int result=pStmt.executeUpdate();
			if(result!=1) {
				return false;
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
		
	}
	
	
	public String isExistProduct(ProductBeans product) {
		String msg="";
		
		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			String sql="select product_id from product where category_id=? and product_id=?";
			
			PreparedStatement pStmt=conn.prepareStatement(sql);
			pStmt.setString(1, product.getCategoryId());
			pStmt.setString(2, product.getProductId());
			
			ResultSet rs=pStmt.executeQuery();
			if(rs.next()) {
				//msg=rs.getString("category_id");
				msg="プロダクトID既存<br>";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}

		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			String sql="select product_name from product where category_id=? and product_name=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			pStmt.setString(1, product.getCategoryId());
			pStmt.setString(2, product.getProductName());
			
			ResultSet rs=pStmt.executeQuery();
			
			if(rs.next()) {
				msg+="プロダクト名既存<br>";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return  null;
		}
		
		return msg;
		
	}
	
	
	public List<ProductBeans> collectProduct(){
		List<ProductBeans> productList =new ArrayList<>();
		
		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			
			String sql="select product_id, category_id, product_name, price from product order by category_id asc";
			PreparedStatement pStmt =conn.prepareStatement(sql);
			
			ResultSet rs=pStmt.executeQuery();
			
			while(rs.next()) {
				String productId=rs.getString("product_id");
				String categoryId=rs.getString("category_id");
				String productName=rs.getString("product_name");
				int price=rs.getInt("price");
				ProductBeans pB=new ProductBeans(productId, categoryId, productName, price);
				productList.add(pB);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return productList;
	}
	
	
	public boolean deleteProduct(ProductBeans product) {
		
		String sql="delete from product where product_id=?";
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			stmt =conn.prepareStatement(sql);
			stmt.setString(1, product.getProductId());
			
			int result=stmt.executeUpdate();
			if(result!=1) {
				return false;
			}
		}catch(SQLException e) {
			e.getStackTrace();
			return false;
		}
		return true;
	}
		
	
	public String updateProduct(ProductBeans product) {
		String msg="";
		
		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			String sql="select * from product where product_name=?";
			
			PreparedStatement pStmt=conn.prepareStatement(sql);
			pStmt.setString(1, product.getProductName());
			
			ResultSet rs=pStmt.executeQuery();
			if(rs.next()) {
				//msg=rs.getString("category_id");
				msg="すでに同じ商品名があります。";
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return msg="編集に失敗しました<br>";
		}
		
		if(msg.length()!=0) {
			return msg;
		}
		
		
		String sql="update product set category_id=?, product_name=? ,price=? where product_id=?";
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			stmt =conn.prepareStatement(sql);
			stmt.setString(1, product.getCategoryId());
			stmt.setString(2, product.getProductName());
			stmt.setInt(3, product.getPrice());
			stmt.setString(4, product.getProductId());
			
			int result=stmt.executeUpdate();
			if(result!=1) {
				msg="編集に失敗しました<br>";
			}
		}catch(SQLException e) {
			e.getStackTrace();
			return msg="null";
		}
		return msg;
	}

	public ArrayList<String> collectProductId() {
		ArrayList<String> li=new ArrayList<>();
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			String sql="select category_id from product";
			
			PreparedStatement pStmt =conn.prepareStatement(sql);

			ResultSet rs=pStmt.executeQuery();
			
			while(rs.next()) {
				String cId=rs.getString("category_id");
				li.add(cId);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return li;
		
	
		
	}
	
	
	
	
}
