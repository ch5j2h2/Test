<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/member/main.jsp</h1>
   <%
     System.out.println("main.jsp 실행");
     //메인페이지를 로그인 상태에서만 사용가능하게 처리
     String id = (String)session.getAttribute("id");
     
     if(id == null){ //로그인 X
    	 response.sendRedirect("./MemberLogin.me");
     }
   %>
   
   <h2> 로그인 사용자명 : <%=id %>님 </h2>
   
   <!-- 로그아웃 버튼 -->
   <input type="button" value="로그아웃" onclick=" location.href='./MemberLogout.me' ">
   
   <hr>
   
   
   <h2><a href="./MeberInfo.me"> 회원 정보 조회 </a></h2>
   
   <hr><hr>
   
   <h2><a href="./MemberUpdate.me"> 회원 정보 수정 </a></h2>
   
   <hr><hr>
   
   <h2><a href="./MemberDelete.me"> 회원 정보 삭제 </a></h2>
   
   <hr><hr>
   <h2><a href="./GoodsList.go"> 사용자 상품보기(쇼핑몰) </a></h2>
   
   <hr><hr>
   <h2><a href="./BasketList.ba"> 장바구니(쇼핑몰) </a></h2>
   
   <hr><hr>
	   
	<h2><a href="./OrderList.or"> 주문목록(쇼핑몰)</a></h2>
	   
   
   <%
   //if(id != null){ // 로그인을 한경우
   //	if(id.equals("admin")){
   		//if( id.equals("admin") &&  id != null ){ (x)
   		if(  id != null  && id.equals("admin") ){
   			// 데이터 값을 비교시 항상 데이터가 있는지 부터 체크
   		%>
   
	   <hr><hr>
	   <h2> 관리자 기능 </h2>
	   
	   <hr><hr>
	   
	   <h2><a href="./MemberList.me"> 회원 정보 목록보기 </a></h2>
	   
	   
	   
	   <hr><hr>
	   
	   <h2><a href="./GoodsAdd.ag"> 관리자 상품등록 </a></h2>
	   
	   <hr><hr>
	   
	   <h2><a href="./GoodsList.ag"> 관리자 상품리스트</a></h2>
	   
	   <hr><hr>
	   
	   <h2><a href="./AdminOrderList.ao"> 관리자 주문목록 리스트</a></h2>
	   
	   
	   
   <%
      }
   
   	%>
   
   
   
   
   
   
   
   
   
   
   
   
</body>
</html>