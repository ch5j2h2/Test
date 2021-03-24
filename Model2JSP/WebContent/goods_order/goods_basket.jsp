<%@page import="com.admin.goods.db.GoodsBean"%>
<%@page import="com.basket.db.BasketBean"%>
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
 <h1>WebContent/goods_order/goods_basket.jsp</h1>
 
 <%
 	// request 영역에 저장
 	//request.setAttribute("basketList", basketList);
	//request.setAttribute("goodsList", goodsList);
	
 	List basketList = (List) request.getAttribute("basketList");
 	List goodsList = (List) request.getAttribute("goodsList");
 
 	
 
 
 
 
 %>
 
 
 
 
 <h2>장바구니</h2>
 
 <table border="1">
 
 	<tr>
	 <td> 번호</td>	 	
	 <td> 사진 </td>	 	
	 <td> 제품명</td>	 	
	 <td> 사이즈</td>	 	
	 <td> 색상</td>	 	
	 <td> 수량</td>	 	
	 <td> 가격</td>	 	
	 <td> 취소</td>	 	
 	</tr>
  
 	<% for(int i=0; i<basketList.size(); i++){
 		BasketBean bb = (BasketBean) basketList.get(i);
 		GoodsBean gb = (GoodsBean)goodsList.get(i);
 		 
 		%>
 	
 	<tr>
	 <%-- <td><%=bb.getB_num() %> </td> --%>	 	
	 <td><%=i+1 %> </td>
	 <!--  장바구니 순처적으로 하는법 -->	 	
	 <td>
	 	<img width="50" height="50" 
	 	src="./upload/<%=gb.getImage().split(",")[0] %>">
	 </td>	 	
	 <td><%=gb.getName() %> </td>	 	
	 <td><%=bb.getB_g_size() %> </td>	 	
	 <td><%=bb.getB_g_color() %> </td>	 	
	 <td><%=bb.getB_g_amount() %> </td>	 	
	 <td><%=gb.getPrice() %> </td>	 	
	 <td> 
		<a href="./BasketDelete.ba?b_num=<%=bb.getB_num() %>">취소</a>
	 </td>	 	
 	 
 	</tr>
 	
 	<% } %>
 </table>
 
 <a href="./OrderStart.or">[구매하기]</a>
 <a href="./GoodsList.go">[계속쇼핑]</a>
 
 
 
 
</body>
</html>