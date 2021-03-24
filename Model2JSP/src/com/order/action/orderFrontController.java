package com.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 


@WebServlet("*.or")
public class orderFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("orderFrontController - doProcess 호출");
		
		
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
		
		if(command.equals("/OrderStart.or")){
			System.out.println("C: /OrderStart.or 호출" );
			System.out.println("C: 장바구니 정보를 저장 ");
		
		// OrderStartActon 객체
			action = new OrderStartAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/OrderAdd.or")){
		System.out.println("c: /OrderAdd.or 호출");
		System.out.println("C : 전달받은 정보를 DB에 저장");
		
		// OderAddAction 객체 생성
			action = new OderAddAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
		}
		else if(command.equals("/OrderList.or")){
			System.out.println("C :/OrderList.or 호출");
			System.out.println("C : DB정보 가져와서 view페이지 출력");
			
			// OrderListAction 객체 생성
			action = new OrderListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/OrderDetail.or")){
			System.out.println("C : /OrderDetail.or 호출");
			System.out.println("C : DB정보를 view페이지 출력");
			
			// OrderDetailAction
			
			action = new OrderDetailAction();
			
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
		 System.out.println("orderFrontController - doGet 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("orderFrontController - doProcess 호출");
		doProcess(request, response); 
	}
	

	
	
	
	
	
	
	
	
}
