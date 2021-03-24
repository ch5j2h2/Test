<%@page import="com.admin.goods.db.GoodsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>WebContent/admingoods/admin_goods_list.jsp</h1>
	
	<h2> 관리자 - 상품목록 리스트</h2>
	
	<%
	 	List<GoodsBean> goodsList 
	 	 	=(List<GoodsBean>)request.getAttribute("goodsList");
	
	
	
	%>
	
	
	<h2><a href="./GoodsAdd.ag"> 관리자 상품등록</a></h2>	
	
	<table border="1">
		<tr>
		 <td>번호</td>
		 <td>카테고리</td>
		 <td>이미지</td>
		 <td>상품명</td>
		 <td>가격</td>
		 <td>수량</td>
		 <td>등록일자</td>
		 <td>수정/삭제</td>
		</tr>
		
	<%for(int i=0; i<goodsList.size(); i++){
			GoodsBean gb = goodsList.get(i);
		%>
	
		<tr>
		 <td><%=gb.getNum() %></td>
		 <td><%=gb.getCategory() %></td>
		 <td>
		 	<img src="./upload/<%=gb.getImage().split(",")[0] %>" width="50" height="50">
		 </td>
		 <td><%=gb.getName() %></td>
		 <td><%=gb.getPrice() %></td>
		 <td><%=gb.getAmount() %></td>
		 <td><%=gb.getDate() %></td>
		 <td>
			<a href="./GoodsModify.ag?num=<%=gb.getNum()%>"> 수정</a>
		 	/
		 	<a href="./GoodsDelete.ag?num=<%=gb.getNum()%>">삭제</a>
		 </td>
		</tr>
		
	<% } %>	
	
	</table>

	



</body>
</html>