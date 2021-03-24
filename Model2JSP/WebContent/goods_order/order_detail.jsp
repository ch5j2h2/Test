<%@page import="com.order.db.OrderBean"%>
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
	<h1>WebContetn/goods_order/order_detail.jsp</h1>
	
	<%
	  //request.setAttribute("trade_num", trade_num);
		List orderDetailList =(List) request.getAttribute("orderDetailList");	
		
		// 총합계산을 위해 변수
		int total=0;
	
	%>
	<h2> 주문내역 상세보기 </h2>
	
	<table border="1">
		 
		<tr>
			<td>상품명</td>
			<td>상품 사이즈</td>
			<td>상품 컬러</td>
			<td>주문 수량</td>
			<td>주문 금액</td>
		</tr>
		
		<%for(int i=0; i<orderDetailList.size(); i++){
			OrderBean ob = (OrderBean) orderDetailList.get(i);
			total += ob.getO_sum_money();
			%>
		<tr>
			<td><%=ob.getO_g_name() %></td>
			<td><%=ob.getO_g_size() %></td>
			<td><%=ob.getO_g_color() %></td>
			<td><%=ob.getO_g_amount() %></td>
			<td><%=ob.getO_sum_money() %></td>
			 
		</tr>
		<% } %>
	</table>
	
	<h3>총 주문 금액 : <%=total %>원</h3>
	
	<a href="./Main.me"> 메인페이지로 </a>
	
</body>
</html>