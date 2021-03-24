 
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
	<%
	// admingoods/admin_goods_modify.jsp
	//request.setAttribute("adminOrderDetail", adminOrderDetail);
	List adminOrderDetail=(List)request.getAttribute("adminOrderDetail");
	OrderBean ob=(OrderBean)adminOrderDetail.get(0);
	int total=0;
	%>
	<h1>주문상세보기/수정</h1>
	<h3>주문번호: <%=ob.getO_trade_num() %></h3>
	<h3>상품정보</h3>
	<table border="1">
	<tr><td>상품이름</td><td>수량</td><td>금액</td><td>사이즈</td><td>색깔</td></tr>
	<%
	for(int i=0;i<adminOrderDetail.size();i++){
		OrderBean orderbean=(OrderBean)adminOrderDetail.get(i);
		total=total+orderbean.getO_sum_money();
		%>
	<tr><td><%=orderbean.getO_g_name() %></td>
	<td><%=orderbean.getO_g_amount() %></td>
	<td><%=orderbean.getO_sum_money() %></td>
	<td><%=orderbean.getO_g_size() %></td>
	<td><%=orderbean.getO_g_color() %></td></tr>	
		<%
	}
	%>
	</table>
	<h3>배송지정보</h3>
	받는사람:<%=ob.getO_receive_name() %><br>
	집전화:<%=ob.getO_receive_phone() %><br>
	배송지주소:<%=ob.getO_receive_addr1() %><br>
	배송지나머지주소:<%=ob.getO_receive_addr2() %><br>
	기타요구사항:<%=ob.getO_memo() %><br>
	<h3>결제정보</h3>
	
	
	<form action="./AdminOrderModify.ao" method="post">
	<input type="hidden" name="trade_num" value="<%=ob.getO_trade_num()%>">
	주문합계금액:<%=total %><br>
	결제방법:<%=ob.getO_trade_type() %><br>
	입금자명:<%=ob.getO_trade_payer() %><br>
	주문상태:<%=ob.getO_status() %>
	<select name="status">
	<option value="0" <% if(ob.getO_status()==0){%>selected<%} %>>대기중</option>
	<option value="1" <% if(ob.getO_status()==1){%>selected<%} %>>발송준비</option>
	<option value="2" <% if(ob.getO_status()==2){%>selected<%} %>>발송완료</option>
	<option value="3" <% if(ob.getO_status()==3){%>selected<%} %>>배송중</option>
	<option value="4" <% if(ob.getO_status()==4){%>selected<%} %>>배송완료</option>
	<option value="5" <% if(ob.getO_status()==5){%>selected<%} %>>주문취소</option>
	</select>
	<br>
	운송장번호:<input type="text" name="trans_num" value="<%=ob.getO_trans_num()%>"><br>
	<input type="submit" value="수정">
	</form>
</body>
</html>





