package com.admin.goods.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdiminGoodsFrontController extends HttpServlet {

	
	//http://localhost:8080/Model2JSP/servlet/test.ag
	 
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//웹페이지를 get/Post방식으로 호출시 실행되는 메서드
			System.out.println("doProcess() 호출");

			
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
			
			if(command.equals("/GoodsAdd.ag")){
				// 상품등록역활
				System.out.println("C: /GoodsAdd.ag 호출");
				// 바로 정보를 입력할수있는 페이지로 연결
				System.out.println("C : view페이지로 이동");
				// actionForward  필요 디비안쓰니깐 객체 생성필요없음
				
				forward = new ActionForward();
				forward.setPath("./admingoods/admin_goods_write.jsp");
				forward.setRedirect(false);
				// T - sendRedirect 밥식으로  F - forward방식으로
				// 여기 까지는 정보만 저장했지 이동한 상태는 아니기에 화면에 표시 안됨
			}
			
			else if(command.equals("/GoodsAddAction.ag")){
				System.out.println("C : ./GoodsAddAction.ag 호출");
				// 주소가 action으로 끝나면 객체 만들어 쓰라
				System.out.println("C : DB에 정보를 저장 후 페이지 이동");
				
				// GoodsAddAction 객체 생성
				action = new GoodsAddAction();
				
				try {
				   forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
					
				}
			
			else if(command.equals("/GoodsList.ag")){
				System.out.println("C : /GoodsList.ag 호출");
				System.out.println("C : DB에서 정보를 가져와서 vies에서 출력");
				
				//GoodsListAction
				action = new GoodsListAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				}
			
			else if(command.equals("/GoodsModify.ag")){
				System.out.println("C : /GoodsModify.ag 호출");
				System.out.println("C : DB정보를 가져와서 수정 - > 저장");
				
				//  /GoodsModifyAction 객체 생성
				action = new GoodsModifyAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
			else if(command.equals("/GoodsModifyProAction.ag")){
				 	System.out.println("C : /GoodsMoidfyProAction.ag호출");
				 	System.out.println("C : 전달정보를 받아서 DB에 저장");
				 	
				 	// GoodsModifyAction 객체 생성
				 	
				 	action = new GoodsModifyProAction();
				 	try {
						forward = action.execute(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}

			else if(command.equals("/GoodsDelete.ag")){
					System.out.println("C : /GoodsDelete.ag호출");
					System.out.println("C : DB이동후 정보 삭제");
					
					// GoodsDeleteAction 객체 생성
					
					action = new GoodsDeleteAction();
					
					try {
				
						forward = action.execute(request, response);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
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
			//웹페이지를 get방식으로 호출시 실행되는 메서드
			System.out.println("doGet() 호출");
		
			doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//웹페이지를 Post방식으로 호출시 실행되는 메서드
			System.out.println("doPost() 호출");
		
			doProcess(request, response);
	}
	
	
}
