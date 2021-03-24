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
	<h1>WebContent/goods_order/order_list.jsp</h1>
	
	<%
	
	//request.setAttribute("orderList", ordao.getOrderList(id));
		List orderList = (List) request.getAttribute("orderList");
		// 받을떄 업케였기에 다운캐
	
	
	
	
	
	%>
	
	
	
	
	<h2> 주문 목록</h2>
	
	<table border="1">
	
	<tr>
		<td>주문범호</td>
		<td>상품명</td>
		<td>결재방법</td>
		<td>주문금액</td>
		<td>주문상태</td>
		<td>주문일자</td>
		<td>운송장번호</td>
	</tr>
	<%for(int i=0; i<orderList.size(); i++){
		OrderBean ob = (OrderBean)orderList.get(i);
		 %>
	
	<tr>
		<td>
			<a href="./OrderDetail.or?trade_num=<%=ob.getO_trade_num() %>">
			<%=ob.getO_trade_num() %>
		</a> 
		</td>
		<td><%=ob.getO_g_name() %></td>
		<td><%=ob.getO_trade_type() %></td>
		<td><%=ob.getO_sum_money() %></td>
		
		<%
			// 주문상태
			// 0 - "대기중" 1-"발송준비", 2-"발송완료" 3-"배송중"
			// 4-"배송완료", 5-"주문취소"
			String status = "";
			if(ob.getO_status() ==0){
				status = "대기중";
				
			}else if(ob.getO_status() ==1){
				status= "발송준비";
			}else if(ob.getO_status() ==2){
				status= "발송완료";
			}else if(ob.getO_status() ==3){
				status= "배송중";
			}else if(ob.getO_status() ==4){
				status= "배송완료";
			}else if(ob.getO_status() ==5){
				status= "주문취소";
			}
			else{
				status="에러";
			}
		
		
		%>
		
		
		<td><%=status %></td>
		<td><%=ob.getO_date() %></td>
		<td><%=ob.getO_trans_num() %></td>
		 
	</tr>
	<%} %>
	</table>
	
	<a href="./Main.me"> 메인페이지로 </a>
	
</body>
</html>