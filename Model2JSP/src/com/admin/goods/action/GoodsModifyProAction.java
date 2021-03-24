package com.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.goods.db.AdminGoodsDAO;
import com.admin.goods.db.GoodsBean;

public class GoodsModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : GoodsModifyProAction_execute ");

		//한글처리
		request.setCharacterEncoding("UTF-8");
		//파라미터 값 저장
		// -> GoodsBean 객체에 저장
		GoodsBean gb = new GoodsBean();
		
		gb.setCategory(request.getParameter("category"));
		gb.setName(request.getParameter("name"));
		gb.setPrice(Integer.parseInt(request.getParameter("price")));
		gb.setColor(request.getParameter("color"));
		gb.setAmount(Integer.parseInt(request.getParameter("amount")));
		gb.setSize(request.getParameter("size"));
		gb.setContent(request.getParameter("content"));
		gb.setBest(Integer.parseInt(request.getParameter("best")));
		gb.setNum(Integer.parseInt(request.getParameter("num")));
		
		// 파일 업데이트는 빠져있는데 스스로 해보기 
		
		// DAO 객체 생성 - modifyGoods(상품정보)
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.modifyGoods(gb);
		
		
		// 페이지 이동(GoodsList)
		ActionForward forward =new ActionForward();
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true);
		// 가상에서 가상으로가는 - T
		
		return forward;
	}

}
