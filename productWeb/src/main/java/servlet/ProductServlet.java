package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategoryBeans;
import model.CategoryLogic;
import model.ProductBeans;
import model.ProductLogic;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		

		ProductLogic productLogic = new ProductLogic();
		List<ProductBeans> productList = productLogic.getProductList();
		request.setAttribute("prductList", productList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
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

			String temp = request.getParameter("price");
			String msg = "";
			try {
				Integer.parseInt(temp);
			} catch (NumberFormatException e) {
				CategoryLogic categoryLogic = new CategoryLogic();
				List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
				request.setAttribute("categoryList", categoryList);

				msg = "単価は数値で入力してください<br>";
				request.setAttribute("msg", msg);
				ProductLogic productLogic = new ProductLogic();
				List<ProductBeans> productList = productLogic.getProductList();
				request.setAttribute("prductList", productList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
				dispatcher.forward(request, response);
			}
			String categoryId = request.getParameter("categoryId");
			String productId = request.getParameter("productId");
			String productName = request.getParameter("productName");
			int price = Integer.parseInt(temp);

			ProductBeans pB = new ProductBeans(productId, categoryId, productName, price);
			ProductLogic pL = new ProductLogic();

			msg = pL.preProductCheck(pB);
			if (msg.length() != 0) {
				CategoryLogic categoryLogic = new CategoryLogic();
				List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
				request.setAttribute("categoryList", categoryList);

				request.setAttribute("msg", msg);
				ProductLogic productLogic = new ProductLogic();
				List<ProductBeans> productList = productLogic.getProductList();
				request.setAttribute("prductList", productList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
				dispatcher.forward(request, response);
			} else {
				if (!pL.registerProduct(pB)) {
					CategoryLogic categoryLogic = new CategoryLogic();
					List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
					request.setAttribute("categoryList", categoryList);

					msg = "登録に失敗しました";
					request.setAttribute("msg", msg);
					ProductLogic productLogic = new ProductLogic();
					List<ProductBeans> productList = productLogic.getProductList();
					request.setAttribute("prductList", productList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
					dispatcher.forward(request, response);
				} else {
					CategoryLogic categoryLogic = new CategoryLogic();
					List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
					request.setAttribute("categoryList", categoryList);

					msg = "登録に成功しました<br>";
					request.setAttribute("msg", msg);
					ProductLogic productLogic = new ProductLogic();
					List<ProductBeans> productList = productLogic.getProductList();
					request.setAttribute("prductList", productList);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
					dispatcher.forward(request, response);
				}
			}

		} else if (action.equals("delete")) {
			String productId = request.getParameter("productId");
			ProductBeans product = new ProductBeans(productId, "dummy", "dummy", 0);

			ProductLogic productLogic = new ProductLogic();
			String msg = productLogic.delProduct(product);

			if (msg.length() == 0) {
				msg = "削除しました<br>";
			}

			CategoryLogic categoryLogic = new CategoryLogic();
			List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
			request.setAttribute("categoryList", categoryList);

			request.setAttribute("msg", msg);
			List<ProductBeans> productList = productLogic.getProductList();
			request.setAttribute("prductList", productList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
			dispatcher.forward(request, response);

		} else if (action.equals("edit")) {
			String temp = request.getParameter("price");
			String msg = "";
			try {
				Integer.parseInt(temp);
			} catch (NumberFormatException e) {

				CategoryLogic categoryLogic = new CategoryLogic();
				List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
				request.setAttribute("categoryList", categoryList);

				msg = "単価は数値で入力してください<br>";
				request.setAttribute("msg", msg);
				ProductLogic productLogic = new ProductLogic();
				List<ProductBeans> productList = productLogic.getProductList();
				request.setAttribute("prductList", productList);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
				dispatcher.forward(request, response);
			}

			String productName = request.getParameter("productName");
			String categoryId = request.getParameter("categoryId");
			String productId = request.getParameter("productId");
			int price = Integer.parseInt(temp);

			ProductBeans product = new ProductBeans(productId, categoryId, productName, price);
			ProductLogic productLogic = new ProductLogic();
			msg = productLogic.editProduct(product);

			if (msg.length() == 0) {
				msg = "編集に成功しました。<br>";
			}
			
			CategoryLogic categoryLogic = new CategoryLogic();
			List<CategoryBeans> categoryList = categoryLogic.getCategoryList();
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("msg", msg);
			List<ProductBeans> productList = productLogic.getProductList();
			request.setAttribute("prductList", productList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/productMain.jsp");
			dispatcher.forward(request, response);

		}

	}

}
