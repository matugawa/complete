<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="model.ProductBeans, model.CategoryBeans, java.util.List" %>
<%

String msg=(String) request.getAttribute("msg");
List<ProductBeans> productList=(List<ProductBeans>) request.getAttribute("prductList");
List<CategoryBeans> categoryList=(List<CategoryBeans>) request.getAttribute("categoryList");
%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロダクト登録</title>
</head>
<body>

<h1>プロダクト登録</h1>


<% if(msg!=null){ %>
<p><%=msg %></p>
<% } %>



<form action="/productWeb/ProductServlet?action=register" method="post">

<select name="categoryId" size="10">
<% for( CategoryBeans category:categoryList){%>
<option value="<%= category.getCategoryBeansId()%>">カテゴリID<%= category.getCategoryBeansId()%> </option>
<% } %>
</select>
<p>商品ID：<input type="text" name="productId"></p>
<p>商品名:<input type="text" name="productName"></p>
<p>単価:<input type="text" name="price"></p>
<input type="submit" value="登録">
</form>


<br>
<br>

<form action="/productWeb/ProductServlet?action=delete" method="post">
<select name="productId" size="10">
<% for(ProductBeans product:productList){%>
<option value="<%= product.getProductId()%>">カテゴリID=<%= product.getCategoryId()%> 商品ID=<%=product.getProductId()%>商品名=<%=product.getProductName() %>単価<%=product.getPrice() %></option>
<% } %>
</select>
<input type="submit" value="削除">
</form>

<br>
<br>



<form action="/productWeb/ProductServlet?action=edit" method="post">

<select name="productId" size="10">
<% for(ProductBeans product:productList){%>
<option value="<%= product.getProductId()%>">商品ID=<%=product.getProductId()%></option>
<% } %>
</select>

<br>
変更後のカテゴリIDを選択
<br>

<select name="categoryId" size="10">
<% for( CategoryBeans category:categoryList){%>
<option value="<%= category.getCategoryBeansId()%>">カテゴリID<%= category.getCategoryBeansId()%> </option>
<% } %>
</select>

<p>変更後商品名:<input type="text" name="productName"></p>
<p>変更後単価:<input type="text" name="price"></p>

<input type="submit" value="編集">
</form>






</body>
</html>