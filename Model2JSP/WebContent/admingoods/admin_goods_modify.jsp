<%@page import="com.admin.goods.db.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!--  index.jsp 페이지 실행시키기 -->
	
	<h1>WebContent/admingoods/admin_goods_modify.jsp</h1>

	<h1>관리자 상품 수정 페이지</h1>
	
	<%
		GoodsBean gb = (GoodsBean) request.getAttribute("goodsbean");
		// el 표현식 사용가능
	
	
	%>
	
	
		
	<form action="./GoodsModifyProAction.ag" method="post" >
		
		<input type="hidden" name="num" value="<%=gb.getNum() %>">
	<%-- <input type="hidden" name="num" value="<%=request.getParameter(num) %>"> --%>
		<!-- 파라미터로 넘길거면 주석처리된것도 가능 근데 num부분 오류났음 확인할것!  -->
	
		<table border="1">
			<tr>
				<td>카테고리</td>
				<td>
					<select name="category">
					  <option value="outwear" 
					 	 <%if(gb.getCategory().equals("outwear")){ %>
					 		 selected
						  <% } %>
					  >아웃웨어</option>
					  
					  <option value="fulldress"
						  <%if(gb.getCategory().equals("fulldress")){ %>
							  selected
						  <% } %>
					  >정장/신사복</option>
					  
					  <option value="Tshirts"
					 		<%if(gb.getCategory().equals("Tshirts")){ %>
							  selected
					 	 <% } %>
					  >티셔츠</option>
					  <option value="shirts"
					  <%if(gb.getCategory().equals("shirts")){ %>
					  selected
					  <% } %>
					  >와이셔츠</option>
					  <option value="pants"
					  <%if(gb.getCategory().equals("pants")){ %>
					  selected
					  <% } %>
					  >바지</option>
					  <option value="shoes"
					  <%if(gb.getCategory().equals("shoes")){ %>
					  selected
					  <% } %>
					  >신발</option>
					</select>
				</td>
			</tr>		
		
			<tr>
				<td>상품명</td>
				<td>
					<input type="text" name="name" value="<%=gb.getName() %>">
				</td>
			</tr>	
			
			<tr>
				<td>판매가</td>
				<td>
					<input type="text" name="price" value="<%=gb.getPrice() %>">
				</td>
			</tr>	
			
			<tr>
				<td>색상</td>
				<td>
					<input type="text" name="color" value="<%=gb.getColor() %>">
				</td>
			</tr>	
			
			
			<tr>
				<td>수량</td>
				<td>
					<input type="text" name="amount" value="<%=gb.getAmount() %>">
				</td>
			</tr>	
			
			<tr>
				<td>크기</td>
				<td>
					<input type="text" name="size" value="<%=gb.getSize() %>">
				</td>
			</tr>	
			
			 
			<tr>
				<td>제품정보</td>
				<td>
					<input type="text" name="content" value="<%=gb.getContent() %>">
				</td>
			</tr>	
		
			<tr>
				<td>인기상품</td>
				<td>
					<input type="radio" name="best" value="1"
					  <%if(gb.getBest() ==1){ %>
					  	checked
					  <% } %>
					>예
					
					<input type="radio" name="best" value="0"
					  <%if(gb.getBest() ==0){ %>
					  	checked
					  <% } %>
					>아니요
				</td>
			
			</tr> 
			
			<tr>
				<td colspan="2">
					<input type="submit" value="정보 수정">
					<input type="reset" value="초기화">
				</td>
			</tr>	
		
		</table>	
	</form>



</body>
</html>