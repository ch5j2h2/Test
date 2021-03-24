<%@page import="com.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/member/updateForm.jsp</h1>
	
	<h2> 회원정보 수정 페이지 (model2) </h2>
	
	<%
	 // 회원 아이디 (로그인 체크)
	 String id = (String) session.getAttribute("id");
	 
	 if(id == null){
		response.sendRedirect("loginForm.jsp");
	 }
	 
	 // request.setAttribute("mb", mdao.getMember(id));
	 // request 영역에 있는 데이터를 꺼내서 활용
	 MemberBean mb = (MemberBean) request.getAttribute("mb");	 
	 
	 // 성별 데이터 제어 -> 라디오버튼 체크(equals() 비교)
	 // 만약에 성별의 정보가 없을경우 기본값을 하나 설정
	 if(mb.getGender() == null){
		 mb.setGender("남자");		 
	 }
	 
	 // 폼태그에 채워넣기 (비밀번호 비우기)
	%>
	
	

	<fieldset>
	    <form action="./MemberUpdateAction.me" method="post">
		      아이디 : <input type="text" name="id" value="<%=mb.getId()%>" readonly ><br>
		      비밀번호 : <input type="password" name="pass" placeholder="비밀번호를 입력하시오."><br>
		      이름 : <input type="text" name="name" value="<%=mb.getName()%>"><br>
		      나이 : <input type="text" name="age" value="<%=mb.getAge()%>"><br>
		      성별 : <input type="radio" name="gender" value="남자" 
		      <%if(mb.getGender().equals("남자")){ %>
		      checked="checked"
		      <%} %>
		      >남자
		      <input type="radio" name="gender" value="여자"
		      <%if(mb.getGender().equals("여자")){ %>
		      checked="checked"
		      <%} %>
		      >여자        
		      <br>
		      이메일 : <input type="text" name="email" value="<%=mb.getEmail() %>"><br>
		      <hr>
		      
	    <input type="submit" value="수정하기">  
	    </form>  
    </fieldset>
  












</body>
</html>