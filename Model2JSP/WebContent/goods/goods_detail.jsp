<%@page import="com.admin.goods.db.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

	<script type="text/javascript">
		
		function isBasket(){
			
			alert("장바구니 처리");
			
			// 구매 수량, 크기, 색상 미 선택시 이동 x
			if(document.gfr.amount.value ==""){
				alert("구매수량을 입력하세요");
				document.gfr.amount.focus();
				return;
			}
			
			if(document.gfr.size.value ==""){
				alert("크기를 선택하세요");
				document.gfr.size.focus();
				return;
			}
			
			if(document.gfr.color.value ==""){
				alert("색상을 선택하세요");
				document.gfr.color.focus();
				return;
			}
	/* ---------------------------구매 정보를 모두 입력상태 ----------------------------------- */
			
			// 장바구니에 저장하시겠습니까?
				var check =	confirm("장바구니로 저장하시겠습니까?");
			
				if(check){
					document.gfr.action = "./BasketAdd.ba";
					document.gfr.submit();
					
				}else{
					retrun;
				}
					
					/*-----------요즘엔 Ajax로 담는동시에 이동할건지 물어봄   */
					
			// Y - 장바구니 저장 ( ./BasketAdd.ba)
					
					
					
					
					
			// N - 동작 x 
			
		}
	
	
	
	
	
	
	</script>




</head>
<body>
  <h1>WebContent/goods/goods_detail.jsp</h1>
 	
 	<%
 		//request.setAttribute("goods", goods);
 	
 		GoodsBean gb = (GoodsBean) request.getAttribute("goods");
 	
 	
 	
 	%>
 	
 	<h2> 상품 상세보기 </h2>
 	
 	
 	
  <form action="" method="post" name="gfr">	
  	 <!-- submit으로 안넘기고 자바로 넘길거라서 name 지정  -->
  	 <!--  장바구니에 담긴 상품들을 구분하기위해  num 사용 -->
  	 <input type="hidden" name="num" value="<%=gb.getNum()%>" >
 	<table border="1">
 		<tr>
 			<td>
 				<img src="./upload/<%=gb.getImage().split(",")[0] %>" width="300" height="300">
 			</td>
 			<td width="300" height="300">
 				상품이동 : <%=gb.getName() %><br>
 				
 				판매가격 : <%=gb.getPrice() %><br>
 				
 				구매수량 : <input type="number" name="amount"><br>
 					<!-- !Dotype html 뒤에 삭제 시키면 타입 넘버 나옴  -->
 				
 				
 				남은수량 :<%=gb.getAmount() %><br>
 				
 				
 				
 				크기 : 
 					<!--  동적으로 값 생성 -->
 					<!--  파라메타로 넘길거기에 name 반드시!! -->
 					<select name="size">
 						<option value="">크기를 선택하시오.</option>
 							<C:forTokens var="size" items="${goods.size }" delims=",">
 								<option value="${size }">${size }</option>
 							</C:forTokens>
 					 <!-- JSTL 이라 EL 표현식 가능 < % =gb.getSize() %> 이표현 안써두됨 -->
 					</select>
 			
 			
 				<br>
 			
 	<!-- -------------------------------------------------------------------------------------------------   -->
 				<!-- 코드의 복잡도가 올라가기에 위에 코드형식으로 사용하기  -->
 		<%-- 
 				<select name="size">
 					<option value=""> 크기를 선택하시오.</option>
 					
 					<%
 					String[] gbArr = gb.getColor().split(",");
 					for(String col : gbArr){
 						%>
 						
 						<option  value="<%=col %>"><%=col %></option>
 						
 						<%
 					}
 				%>
 				</select>
 		 --%>
	<!-- -------------------------------------------------------------------------------------------------   -->
 				
 				
 				색상 : 
 					<select name="color">
						<option value=""> 색상을 선택하시오. </option> 					
							<C:forTokens var="color"  items="${goods.color }" delims=",">
								<!-- item을 ,로 구분한 다음 var에 담을것 -->
								<option value="${color }">${color }</option>								
							</C:forTokens>
 					</select>
 				
 				
 		 <br>
 			<hr><hr>	
 			
 			<a href="javascript:isBasket()"> [ 장바구니 담기 ]</a>
 			<a href="javascript:isBuy()"> [ 구매하기 ]</a>
 			
 			
 			
 			
 				
 				
 			</td>
 		</tr>
 
 		<tr>
 		
 		
 		<!-- -------------------------------------------------------------------------------------------------   -->	
 		<%-- 
 			<td colspan="2">
 				<img  src="./upload/<%=gb.getImage().split(",")[1] %>" width="600"  >
 				<img  src="./upload/<%=gb.getImage().split(",")[2] %>" width="600"  >
 				<img  src="./upload/<%=gb.getImage().split(",")[3] %>" width="600"  >
 			</td>
 		 --%>
 		<!-- -------------------------------------------------------------------------------------------------   -->
 		
 		
 		
 			
 			<td colspan="2">
 		 		<C:forTokens var="imgName"  items="${goods.image }" delims=",">
					<img  src="./upload/${imgName }" width="600"  >
				</C:forTokens>
 			
										
						
 			</td>	 
 	
 		</tr>
 	
 	
 	</table>
  </form>	
 	
 	
 
</body>
</html>