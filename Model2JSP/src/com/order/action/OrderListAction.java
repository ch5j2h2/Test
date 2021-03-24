package com.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order.db.OrderDAO;

public class OrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : OrderListActkon execute 호출");
		
		//세션 정보처리
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null ){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		
		// OrderDAO 객체 생성 => 회원의 주문정보를 가녀오기
		// id값을 사용
		OrderDAO ordao = new OrderDAO();
		//ordao.getOrderList(id);
		//request 영역에 저장
		
		request.setAttribute("orderList", ordao.getOrderList(id));
		
		// 페이지 이동("./goods_order/order_list.jsp")
		forward.setPath("./goods_order/order_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
