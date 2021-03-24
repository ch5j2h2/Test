package com.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.goods.db.GoodsBean;
import com.goods.db.GoodsDAO;

public class GoodsDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		System.out.println("M : GoodsDetailAction_execute 호출");
		
		// 전달된 데이터 저장(파라미터 값)
		int num = Integer.parseInt(request.getParameter("num"));
		
		
		// DAO 객체 생성 - getGoods(num)
		GoodsDAO gdao = new GoodsDAO();
		GoodsBean goods = gdao.getGoods(num);
		
			
		// request 영역에 저장
		request.setAttribute("goods", goods);

		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./goods/goods_detail.jsp");
		forward.setRedirect(false);
		 // request영역을 저장하서 이동할거기때문에 false
		
		
		
		return forward;
	}

}
