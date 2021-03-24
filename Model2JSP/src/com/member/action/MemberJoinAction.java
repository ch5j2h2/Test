package com.member.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberJoinAction implements Action{
	//pro 페이지 동작을 수행 - Model

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberJoinAction_execute() 호출");
		// pro 페이지처리 
		// 회원가입 처리 
		
		// 한글데이터 처리 
		request.setCharacterEncoding("UTF-8");
		
		// DB 테이블 생성
		// 전달된 파라미터값을 저장 -> 자바빈객체 
		MemberBean mb = new MemberBean();
		mb.setId(request.getParameter("id"));
		mb.setPass(request.getParameter("pass"));
		mb.setName(request.getParameter("name"));
		mb.setAge(Integer.parseInt(request.getParameter("age")));
		mb.setGender(request.getParameter("gender"));
		mb.setEmail(request.getParameter("email"));
		mb.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		System.out.println("M : 전달된 회원정보 "+mb);
		
		
		// DB에 저장하기위한 객체 DAO 생성
		MemberDAO mdao = new MemberDAO();
		
		// 회원가입 메서드 생성
		mdao.insertMember(mb);
		
		System.out.println("M : 회원가입 성공! ");
		
		// 페이지 이동 (로그인 페이지로 이동)
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberLogin.me");
		forward.setRedirect(true);		
		
		return forward;
	}
	
	
	
	

}
