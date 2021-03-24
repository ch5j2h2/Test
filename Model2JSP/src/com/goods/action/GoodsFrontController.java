package com.goods.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class GoodsFrontController extends HttpServlet {


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Goods-doProcess 호출");
		
		
		System.out.println("\n 1. 가상주소 계산 ");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		
		System.out.println("  1. 가상주소 계산 ");
		
		
		
		System.out.println("\n 2. 가상주소 매핑 ");
		
		Action action = null;
		ActionForward forward = null;
		// 객체는 필요할때만 사용할꺼고 레퍼런스를 사용하여 필요할때만 사용
		
		if(command.equals("/GoodsList.go")){
			System.out.println("C : /GoodsList.go 호출 ");
			System.out.println("C : DB에서 정보를 가져와서 view 출력");
			
			// /GoodsListAction 객체 생성
			
			action = new GoodsListAction();
			// http://localhost:8088/Model2JSP/GoodsList.go
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		else if(command.equals("/GoodsDetail.go")){
			System.out.println("C : /GoodsDetail.go 호출");
			System.out.println("C : DB에서 정보를 가져와서 view 출력");
			
			action = new GoodsDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		
		
		
		
		
		
		
		
		System.out.println(" 2. 가상주소 매핑 ");

		
		System.out.println("\n 3. 페이지 이동 ");
		
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			} else{
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());

				dis.forward(request, response);
				// 여기까지하면 404 화면 뜸 ( admin_goods_write.jsp 만들기전)
			}
		}
		
		
		
		System.out.println(" 3. 페이지 이동 ");
		
		
	}

	
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Goods-doGet 호출"); 
		// 주소줄에 파라메터로 가져가느냐를 가지고 따지기 doPost이외 다 두겟 버튼행동
		
		doProcess(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Goods-doPost 호출");
		// ajax , form 
		
		doProcess(request, response);
	}

	
	
	
	
}
