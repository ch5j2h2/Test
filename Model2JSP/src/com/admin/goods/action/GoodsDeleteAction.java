package com.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.goods.db.AdminGoodsDAO;

public class GoodsDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : GoodsDeleteAction_execute 호출");
		
		// 전달된 파라미터정보 저장(num)
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DAO 객체 생성 - deleteGoods(num)
		// 상품번호를 사용하여 해당 상품 삭제
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.deleteGoods(num);
		
		// 페이지 이동(GoodsList.ag)
		ActionForward forward = new ActionForward();
		
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		
		return forward;
	}

}
