package com.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basket.db.BasketBean;
import com.basket.db.BasketDAO;

public class BasketAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		System.out.println("M : BasketAddAction_execute 호출");
		
		// 로그인 여부 체크 - 아이디별 장바구니 다를거니깐
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		
		
		// BasketBean 생성후 저장
		BasketBean basket = new BasketBean();
		// 상세페이지 폼에서 넘어오는 정보들
		basket.setB_g_num(Integer.parseInt(request.getParameter("num")));
		basket.setB_g_amount(Integer.parseInt(request.getParameter("amount")));
		basket.setB_g_size(request.getParameter("size"));
		basket.setB_g_color(request.getParameter("color"));
		
		// session에 있는 아이디 , 아이디없으면 이미 로긴으로 빠졌을것!
		basket.setB_m_id(id);
		
		System.out.println("M : 장바구니 정보 "+basket);
		
		
		// BasketDAO 객체 생성
		BasketDAO bkdao = new BasketDAO();
		
		// 바로 저장 x , 같은상품 여러개 구입할 경우 존재 
		// 장바구니에 이미 있는걸 다시 담는다면 수량을 업뎃으로 하기
		
		// 장바구니 상품 중복 체크 + 동일 상품이 있을경우 수량 증가
		int check = bkdao.checkGoods(basket);
		
		if(check != 1){
			//중복상품이 없음
			// 장바구니에 저장
			bkdao.basketAdd(basket);
		}
		
		System.out.println("M : 장바구니 저장완료");
		
		// 페이지 이동
		
		forward.setPath("./BasketList.ba");
		forward.setRedirect(true);
		
		return forward;
		
	}

}
