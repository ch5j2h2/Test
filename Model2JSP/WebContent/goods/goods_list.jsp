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

  <h1>WebContent/goods/goods_list.jsp</h1>

	<%
	 // request 영역에 저장 -attribute
	 //request.setAttribute("goodsList", gdao.getGoodsList(item));
	 // Action 페이지에서 이렇게 저장했으니깐
	 
	 List goodsList = (List) request.getAttribute("goodsList");
		// 다운캐스팅
	
	%>


	<table border="1">
	 <tr>
	   <td>
	    <a href="./GoodsList.go">전체</a></td>	 
	   <td>
	   	<a href="./GoodsList.go?item=best">베스트</a>
	   	</td>	 
	   <td>
	    <a href="./GoodsList.go?item=outwear">아웃웨어 </a>
		</td>	 
	   <td>
	    <a href="./GoodsList.go?item=fulldress">정장/신사복 </a>
	   </td>	 
	   <td>
	    <a href="./GoodsList.go?item=Tshirts">티셔츠 </a>
	   </td>	 
	   <td>
	    <a href="./GoodsList.go?item=shirts">와이셔츠 </a>
	   </td>	 
	   <td>
	    <a href="./GoodsList.go?item=pants">바지 </a>
	   </td>	 
	   <td>
	    <a href="./GoodsList.go?item=shoes">신발 </a>
	   </td>	 
	 </tr>
	 
	 <tr>
	 <% 
	 	
	 	// 상품의 총 개수
	 	 int size = goodsList.size();
	 	// 상품의 열의 개수 8개 
	 	int col = 8;
	 	// 상품의 행의 개수 
	 	int row = (size / col)+(size%col>0 ? 1 : 0);
	 	// 출력카운트
	 	int num = 0;
	 	
	 	for(int a=0; a<row; a++){
	 		%>
	 		<tr>
	 		<% 
	 		for(int b=0; b<col; b++){
		 		GoodsBean gb = (GoodsBean) goodsList.get(num);
	 			%>
	 		<td>
	 			<img src="./upload/<%=gb.getImage().split(",")[0] %>" width="150" height="150"><br>
	 			
	 			
	 			<a href="./GoodsDetail.go?num=<%=gb.getNum() %>">
	 					<%=gb.getName() %> 
	 			</a>
	 			<br>
			 	
			 	
			 	<%=gb.getPrice() %> 원 <br>
			</td>
				<% 	 			
	 			
	 			num++;
	 			if(size<=num) break;
	 			
	 		}
	 		%>
	 		</tr>
	 	<% 
	 	}
	%>
	 	 
	 
	</table>




</body>
</html>