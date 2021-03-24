package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberInfoAction_execute() 호출");
		
		// id값을 저장(session) -> 아이디 정보가 없을경우 로그인페이지로 이동
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");

		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 생성 -> id에 해당하는 회원정보 전부를 가져오는 메서드
		// getMember(id);
		MemberDAO mdao = new MemberDAO();
		MemberBean mb = mdao.getMember(id);
		
		// 가져온 정보를 저장
		request.setAttribute("mb", mb);		
		
		// 페이지 이동 (./member/info.jsp)
		forward.setPath("./member/info.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
