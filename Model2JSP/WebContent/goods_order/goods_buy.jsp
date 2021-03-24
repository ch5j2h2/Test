<%@page import="com.admin.goods.db.GoodsBean"%>
<%@page import="com.basket.db.BasketBean"%>
<%@page import="com.member.db.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/goods_order/goods_buy.jsp</h1>
	
	<%
	
		  /* 	
			// 정보 저장 (request)
			request.setAttribute("basketList", basketList);
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("memberInfo", memberInfo);
		  */
	
		ArrayList basketList = (ArrayList) request.getAttribute("basketList");
		ArrayList goodsList = (ArrayList) request.getAttribute("goodsList");
		MemberBean memberInfo =  (MemberBean) request.getAttribute("memberInfo");
		 
	%>
	 
	
	<h2>주문 상세 내역</h2>
	
	 
	<table border="1">
	    <tr>
			<td>이미지</td>
			<td>상품명</td>
			<td>구매수량</td>
			<td>색상</td>
			<td>사이즈</td>
			<td>가격</td>
		</tr>
	 
	<% 
		int totalSum = 0;
		for(int i=0; i<basketList.size(); i++){
		BasketBean bk = (BasketBean) basketList.get(i);
		GoodsBean gb = (GoodsBean)goodsList.get(i);
		System.out.println(totalSum);
		
		%>
	
		<tr>
			<td>
				<img  src="./upload/<%=gb.getImage().split(",")[0] %>"
						width="50" height="50"> 
			</td>
			<td><%=gb.getName() %></td>
			<td><%=bk.getB_g_amount() %></td>
			<td><%=bk.getB_g_color() %></td>
			<td><%=bk.getB_g_size() %></td>
			<td><%=gb.getPrice() %></td>
		</tr>
	<%
		
		totalSum += (gb.getPrice() * bk.getB_g_amount());
		
		} %>	
	
	</table> 
		<h3>총 가격 : <%=totalSum %> 원 </h3>
	<hr>
	
  <form action="./OrderAdd.or" method="post">	 
	<table border="1">
	  
	  <tr>
	  	<td colspan="2"><h2>주문자 정보</h2></td>
	  	 
	  </tr>
	  
	  <tr>
	  	<td>이름</td>
	  	<td><%=memberInfo.getName() %></td>
	  </tr>
	  
	   <tr>
	  	<td>전화번호</td>
	  	<td>전화번호</td>
	  </tr>
	  
	   <tr>
	  	<td>이메일</td>
	  	<td><%=memberInfo.getEmail() %></td>
	  </tr>
	  
	   <tr>
	  	<td colspan="2"><h2>배송지 정보</h2></td>
	  </tr>
	  
	   <tr>
	  	<td>받는사람</td>
	  	<td><input type="text" name="o_receive_name" 
	  					value="<%=memberInfo.getName()%>"></td>
	  </tr>
	  
	  <tr>
	  	<td>전화번호</td>
	  	<td><input type="text" name="o_receive_phone" ></td>
	  </tr>
	  
	   <tr>
	  	<td>배송지 주소</td>
	  	<td><input type="text" name="o_receive_addr1"> </td>
	  </tr>
	  
	   <tr>
	  	<td>배송지 나머지 주소</td>
	  	<td><input type="text" name="o_receive_addr2"></td>
	  </tr>
	  
	   <tr>
	  	<td>배송 메세지 작성</td>
	  	<td><input type="text" name="o_memo" ></td>
	  </tr>
	  
	   <tr>
	  	<td colspan="2"><h2>결제정보</h2></td>
	   
	  </tr>
	  
	  <tr>
	  	<td colspan="2">
	  		<input type="radio" checked> 온라인입금
	  		<input type="radio"> 신용카트
	  		<input type="radio"> 체크카드
	  		<input type="radio"> o o 페이
	  	 
	  	</td>
	  </tr>	
	  
	  <tr>
	  	<td>입금자명(온라인입금)</td>
	  	<td><input type="text" name="o_trade_payer" 
	  					value="<%=memberInfo.getName() %>" ></td>
	  </tr>
	  
	  	 
	  <tr>
	   <td colspan="2">
	   	<input type="submit" value="주문하기">
	   	<input type="reset" value="취소하기">
	   </td>	  
	  </tr>
	  
	  
	</table>
   </form>		
	 
</body>
</html>