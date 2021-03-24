package com.admin.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("*.ao")
public class AdminOrderFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdminOrderFrontController - doProcess 호출");
		
		
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
		if(command.equals("/AdminOrderList.ao")){
			System.out.println("C: /AdminOrderList.ao 호출");
			System.out.println("C : DB정보를 View 페이지에 출력");
			
			action = new AdminOrderListaction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		else if(command.equals("/AdminOrderDetail.ao")){
			System.out.println("C : /AdminOrderDetail.ao 호출");
			System.out.println("C : 전달받은 파라미터를 사용해서 DB정보롤 출력");
			
			// AdminOrderDetailAction 객체
			action = new AdminOrderDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		 	
		}
		else if(command.equals("/AdminOrderModify.ao")){
			System.out.println("C: /AdminOrderModify.ao");
			System.out.println("C : 운송장번호, 주문상태 변경(DB)->주문List 이동");
			
			// AdminOrderModifyAction
			
			action = new AdminOrderModifyAction();
			
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
		 System.out.println("AdminOrderFrontController - doGet 호출");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdminOrderFrontController - doProcess 호출");
		doProcess(request, response); 
	}
	

	
	
	
	
	
	
	
	
	
}
