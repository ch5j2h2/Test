package com.basket.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basket.db.BasketDAO;

public class BasketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		System.out.println("M : BasketListAction_execut()실행");
		
		
		// 로그인 체크
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id==null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			
			return forward;
		}
		// DAO 객체 생성 - 장바구니 정보를 가져오는 메소드
					//-장바구니에 없는 ㅇ정보들(이미지, 상품명, 가격)
		BasketDAO  bkdao = new BasketDAO();
		
		Vector totalList = bkdao.getBasketList(id);
		
		System.out.println("M : "+totalList);
					
		List  basketList = (List) totalList.get(0);
		List  goodsList = (List) totalList.get(1);
		/*			
		System.out.println("M : 장바구니 리스트");
		System.out.println("M : "+basketList);
		System.out.println("M : 상품 리스트");
		System.out.println("M :  "+goodsList);
		*/
		
		
		// request 영역에 저장
		request.setAttribute("basketList", basketList);
		request.setAttribute("goodsList", goodsList);
		
		// 페이지이동("./goods_order/goods_basket.jsp")
		
		forward.setPath("./goods_order/goods_basket.jsp");
		forward.setRedirect(false);
		
		
		 
		return forward;
	}

}
