<%@page import="com.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <h1>WebContent/member/info.jsp</h1>
    
    <h2> 회원 정보 출력 페이지 (model2) </h2>
    
    <%
      // 로그인 여부 체크 -> 로그인 페이지로 이동
      String id = (String)session.getAttribute("id");
      
      if(id == null){
    	  response.sendRedirect("./MemberLogin.me");
      }

      // request.setAttribute("mb", mb);
      // => 영역에 저장된 정보를 꺼내서 사용
      MemberBean mb = (MemberBean) request.getAttribute("mb");
         
    %>
    <hr>
    
    <h2> 회원 정보 </h2>
    ${mb.id }
    <h3>아이디 : <%=mb.getId() %></h3>
    <h3>비밀번호 : <%=mb.getPass() %></h3>
    <h3>이름 : <%=mb.getName() %> </h3>
    <h3>나이 : <%=mb.getAge() %></h3>
    <h3>성별 : <%=mb.getGender() %></h3>
    <h3>이메일 : <%=mb.getEmail() %></h3>
    <h3>가입일 : <%=mb.getReg_date() %></h3>    
    
    <!-- ctrl + alt + 방향키 아래 : 코드라인 복사 -->
    
    <hr>
    
    <h2><a href="./Main.me">메인페이지로...</a></h2>
    
    

</body>
</html>