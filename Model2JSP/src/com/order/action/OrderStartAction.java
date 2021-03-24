package com.order.action;

import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basket.db.BasketDAO;
import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class OrderStartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : OrderStartActon_execute 호출 ");
		
		// 세션정보
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null ){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
				
				
		// 장바구니 정보 -> 주문테이블에 저장 
		// 장바구니 정보를 가져오기 (장바구니 + 상품정보 )
		BasketDAO bkdao = new BasketDAO();

		Vector totalList = bkdao.getBasketList(id);
		
		// 장바구니 정보(구매수량, 옵션)
		ArrayList basketList = (ArrayList)totalList.get(0);
		// 상품정보 (이름, 가격, 이미지)
		ArrayList goodsList = (ArrayList) totalList.get(1);
		
		
		// 사용자 정보
		MemberDAO mdao = new MemberDAO();
		MemberBean memberInfo = mdao.getMember(id);
		 
		// 정보 저장 (request)
		request.setAttribute("basketList", basketList);
		request.setAttribute("goodsList", goodsList);
		request.setAttribute("memberInfo", memberInfo);
		
		// view 페이지로 이동 ( "./goods_order/goods_buy.jsp")
		forward.setPath("./goods_order/goods_buy.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
