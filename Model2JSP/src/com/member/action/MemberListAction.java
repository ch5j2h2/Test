package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : MemberListAction_execute() 호출");
		
		//로그인 세션(관리자만 사용)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin") ){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;			
		}
		
		// DAO 객체 생성
		// 처리 메서드-getMemberList()
		MemberDAO mdao = new MemberDAO();
		List<MemberBean> memberList = mdao.getMemberList();

		// 리스트 정보를 저장(request) 
		request.setAttribute("memberList", memberList);
		
		// 페이지 이동 (view 페이지 이동)
		forward.setPath("./member/memberList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
