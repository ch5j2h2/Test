package com.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.order.db.AdminOrderDAO;
import com.order.db.OrderBean;

public class AdminOrderModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("C : /AdminOrderModifyAction_execute 호출");
		
		// 세션제어
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null || !id.equals("admin")){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
			 
		}
		
		// 파라미터 값 저장 ( trade_num, status, trans_num)
		// =>OrderBean 저장 가져올거 2개이상이다 하면 Bean생성
		OrderBean ob = new OrderBean();
		ob.setO_trade_num(request.getParameter("trade_num")); // form태그에 이름 trade_num
		ob.setO_status(Integer.parseInt(request.getParameter("status")));
		ob.setO_trans_num(request.getParameter("trans_num"));
		
		
		
		
		
		//AdminOrderDAO 객체 생성 - updateOrder(ob)
		AdminOrderDAO aodao = new AdminOrderDAO();
		aodao.updateOrder(ob);
		
		
		
		
		// 페이지 이동 ( AdminOrderList.ao)
		forward.setPath("AdminOrderList.ao");
		forward.setRedirect(true);
		
		return forward;
	}

}
