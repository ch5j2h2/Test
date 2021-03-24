package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : MemberDeleteAction_execute() 호출 ");

		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		// 전달된 비밀번호 저장
		MemberBean mb = new MemberBean();
		mb.setPass(request.getParameter("pass"));
		mb.setId(id);
		
		System.out.println("M : 삭제회원 정보 "+mb);

		// DAO 객체 생성 - deleteMember(mb);
		MemberDAO mdao = new MemberDAO();
		int check = mdao.deleteMember(mb);

		// 데이터의 처리 결과에 따라서 페이지 이동
		// update동작과 동일하게 처리 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(check == 0){
			out.print("<script>");
			out.print(" alert('비밀번호 오류!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			return null;
			
		}else if(check == -1){
			out.print("<script>");
			out.print(" alert('회원 정보 없음!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			
			out.close();
			return null;
		}else{ // check == 1
			// 세션초기화
			session.invalidate();
			
			out.print("<script>");
			out.print(" alert('회원 탈퇴 완료!'); ");
			out.print(" location.href='./Main.me'; ");
			out.print("</script>");
			
			out.close();
			return null;
		}
		

	}

}
