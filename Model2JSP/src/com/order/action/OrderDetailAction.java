package com.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order.db.OrderDAO;

public class OrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : OrderDetailAction_execute 호출");
		
		
		// 세션정보
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null ){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달된 정보(파라메터 trade_num) 저장
		String trade_num = request.getParameter("trade_num");
		
		// OrderDAO 객체 생성 -> orderDetail(trade_num)
		OrderDAO ordao = new OrderDAO();
		//ordao.orderDetail(trade_num);
		
		
		// request 영역에 저장
		request.setAttribute("orderDetailList", ordao.orderDetail(trade_num));
		
		
		// 페이지 이동 ("./goods_order/order_detail.jsp")
		forward.setPath("./goods_order/order_detail.jsp");
		forward.setRedirect(false);
		
		return forward;
		 
	}

}
