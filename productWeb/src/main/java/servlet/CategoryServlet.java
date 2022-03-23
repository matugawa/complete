package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryBeans;
import model.CategoryLogic;

/**
 * Servlet implementation class CategoryServlet
 */
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		CategoryLogic categoryLogic = new CategoryLogic();
		List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
		request.setAttribute("categoryList", categoryList);
		
		
		ArrayList<String> delCategoryList=categoryLogic.getDeleteCategoryList();
		request.setAttribute("delCategoryList", delCategoryList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		if (action.equals("register")) {

			String categoryId = request.getParameter("categoryId");
			String categoryName = request.getParameter("categoryName");
	

			CategoryBeans category = new CategoryBeans(categoryId, categoryName);
			CategoryLogic categoryLogic = new CategoryLogic();
			String msg = categoryLogic.inputtedStringCheck(category);

			if (msg.length() > 0) {
				List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
				request.setAttribute("categoryList", categoryList);
				
				ArrayList<String> delCategoryList=categoryLogic.getDeleteCategoryList();
				request.setAttribute("delCategoryList", delCategoryList);
				
				
				request.setAttribute("msg", msg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
				dispatcher.forward(request, response);
			} else {//エラーじゃない
				
				if (!categoryLogic.registerCategory(category)) {
					List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
					request.setAttribute("categoryList", categoryList);
					
					ArrayList<String> delCategoryList=categoryLogic.getDeleteCategoryList();
					request.setAttribute("delCategoryList", delCategoryList);
					
					msg = "登録に失敗しました";
					request.setAttribute("msg", msg);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
					dispatcher.forward(request, response);
				} else {
					msg="登録に成功しました<br>";
			
					request.setAttribute("msg", msg);

					List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
					request.setAttribute("categoryList", categoryList);
					
					ArrayList<String> delCategoryList=categoryLogic.getDeleteCategoryList();
					request.setAttribute("delCategoryList", delCategoryList);

					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
					dispatcher.forward(request, response);

				}

			}
		}else if(action.equals("delete")) {
			String categoryId = request.getParameter("categoryId");
			CategoryBeans category = new CategoryBeans(categoryId, "dummy");
			CategoryLogic categoryLogic = new CategoryLogic();
			String msg=categoryLogic.delCategory(category);
			request.setAttribute("msg", msg);

			List<CategoryBeans> categoryList = categoryLogic.getCategoryList();

			request.setAttribute("categoryList", categoryList);
			
			ArrayList<String> delCategoryList=categoryLogic.getDeleteCategoryList();
			request.setAttribute("delCategoryList", delCategoryList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
			dispatcher.forward(request, response);
			
		}else if(action.equals("edit")) {
			String categoryId = request.getParameter("categoryId");
			String categoryName = request.getParameter("categoryName");

			CategoryBeans category = new CategoryBeans(categoryId, categoryName);

			CategoryLogic categoryLogic = new CategoryLogic();
			String msg=categoryLogic.editCategory(category);
			
			request.setAttribute("msg", msg);

			
			List<CategoryBeans> categoryList = categoryLogic.getCategoryList();

			request.setAttribute("categoryList", categoryList);
			
			ArrayList<String> delCategoryList=categoryLogic.getDeleteCategoryList();
			request.setAttribute("delCategoryList", delCategoryList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/categoryMain.jsp");
			dispatcher.forward(request, response);
			
			
		}

	}

}
