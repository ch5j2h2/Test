package com.admin.goods.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.goods.db.AdminGoodsDAO;
import com.admin.goods.db.GoodsBean;

public class GoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : GoodsListAction_execute 호출");
		
		// DAO 객체 생성 - 상품정보 모두를 가져오는 메서드
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		
		List<GoodsBean> goodsList = agdao.getGoodsList();
		
		// request 영역에 저장
		request.setAttribute("goodsList", goodsList);
		
		// 페이지 이동(view  ./admingopds/admin_goods_list.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_list.jsp");
		forward.setRedirect(false); //forward
		return forward;
	}

}
