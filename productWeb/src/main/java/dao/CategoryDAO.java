package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CategoryBeans;



public class CategoryDAO {
	
	private final String JDBC_URL=
			"jdbc:mysql://localhost:3306/product_web_db";
	private final String DB_USER="mysql";
	private final String DB_PASS="mysql";
	
	
	public boolean isExistIdorName(CategoryBeans category) {
		
		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			String sql="select category_id from category where category_id=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			pStmt.setString(1, category.getCategoryBeansId());
			ResultSet rs=pStmt.executeQuery();
			if(rs.next()) {
				//msg=rs.getString("category_id");
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return (Boolean) null;
		}

		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			String sql="select category_name from category where category_name=?";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			pStmt.setString(1, category.getCategoryBeansName());
			
			ResultSet rs=pStmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return (Boolean) null;
		}
		
		return false;
		
	}
	
	public boolean makeCategory(CategoryBeans category) {
		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			
			String sql="insert into category(category_id, category_name) values(?, ?)";
			PreparedStatement pStmt=conn.prepareStatement(sql);
			
			pStmt.setString(1, category.getCategoryBeansId());
			pStmt.setString(2, category.getCategoryBeansName());

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
	
	public List<CategoryBeans> collectCategory(){
		List<CategoryBeans> categoryList =new ArrayList<>();
		
		try(Connection conn=DriverManager.getConnection(
				JDBC_URL, DB_USER, DB_PASS)){
			
			String sql="select category_id, category_name from category order by category_id asc";
			PreparedStatement pStmt =conn.prepareStatement(sql);
			
			ResultSet rs=pStmt.executeQuery();
			
			while(rs.next()) {

				String categoryId=rs.getString("category_id");
				String categoryName=rs.getString("category_name");
				CategoryBeans cat=new CategoryBeans(categoryId, categoryName);
				categoryList.add(cat);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return categoryList;
	}
	
	public boolean deleteCategory(CategoryBeans category) {
		String sql="delete from category where category_id=?";
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			stmt =conn.prepareStatement(sql);
			stmt.setString(1, category.getCategoryBeansId());
			
			
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
	
	
	public boolean updateCategory(CategoryBeans category) {
		String sql="update category set category_name=? where category_id=?";
		Connection conn=null;
		PreparedStatement stmt=null;
		try {
			conn =DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			stmt =conn.prepareStatement(sql);
			stmt.setString(1, category.getCategoryBeansName());
			stmt.setString(2, category.getCategoryBeansId());
		
			
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
	
	public ArrayList<String> collectCategoryId() {
		ArrayList<String> li=new ArrayList<>();
		
		try(Connection conn=DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
			
			String sql="select category_id from category";
			
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


