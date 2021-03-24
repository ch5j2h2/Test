package com.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8088/Model2JSP/member

public class MemberFrontController extends HttpServlet {
	// 컨트롤러 : 모델과 뷰의 연결지점 => 서블릿

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController - doProcess() 호출! ");
		

		/* 1. 가상주소 계산 */
		System.out.println("\n\n\n      1. 가상주소 계산              ");
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI : "+requestURI);
		//System.out.println("URL : "+request.getRequestURL());
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath : "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		System.out.println("      1. 가상주소 계산              ");
		/* 1. 가상주소 계산 */
		
		/* 2. 가상주소 매핑 (연결)*/
		System.out.println("\n\n\n      2. 가상주소 매핑 (연결)        ");
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MemberJoin.me")){
			System.out.println("C : /MemberJoin.me 호출");
			System.out.println("C : DB 사용없이 view 페이지로 이동");
			
			//페이지 이동객체 생성
			forward = new ActionForward();
			forward.setPath("./member/insertForm.jsp");
			forward.setRedirect(false);
		}
		else if(command.equals("/MemberJoinAction.me")){
			System.out.println("C : /MemberJoinAction.me 호출");
			System.out.println("C : 정보를 가지고 DB에 처리후 페이지 이동");
			
			//DB사용 객체는 ->Action 객체로 구현 
			// MemberJoinAction 객체를 생성
			action = new MemberJoinAction();
			
			// DB처리하는 동작을 호출
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberLogin.me")){
			System.out.println("C : /MemberLogin.me 호출 ");
			System.out.println("C : DB사용없이 view페이지로 이동");
			
			// 페이지 이동객체 생성
			forward = new ActionForward();
			forward.setPath("./member/loginForm.jsp");
			forward.setRedirect(false);			
		 }
		else if(command.equals("/MemberLoginAction.me")){
			System.out.println("C : /MemberLoginAction.me 호출");
			System.out.println("C : DB에 정보 처리 후 페이지 이동");
			System.out.println("C : MemberLoginAction 객체 생성");
			// MemberLoginAction 객체 생성
			action = new MemberLoginAction();
			
			try {
				System.out.println("C : execute() 호출! ");
				forward = action.execute(request, response);
				System.out.println("C : 호출결과 forward 저장후 이동 ");
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		else if(command.equals("/Main.me")){
			System.out.println("C : /Main.me 호출 ");
			System.out.println("C : DB 사용없이 view 페이지 이동 ");
			
			forward = new ActionForward();
			forward.setPath("./member/main.jsp");
			forward.setRedirect(false);			
		}
		else if(command.equals("/MemberLogout.me")){
			System.out.println("C : /MemberLogout.me 호출");
			System.out.println("C : 데이터처리 로직 사용후 이동");
			
			// MemberLogoutAction 객체 생성
			action = new MemberLogoutAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/MemberInfo.me")){
			System.out.println("C : /MemberInfo.me 호출");
			System.out.println("C : DB결과를 가지고 view페이지 출력");
			
			// MemberInfoAction 객체 생성
			action = new MemberInfoAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberUpdate.me")){
			System.out.println("C : /MemberUpdate.me 호출");
			System.out.println("C : DB 정보를 가지고 view 페이지 이동");
			
			// MemberUpdate 객체 생성
			action = new MemberUpdate();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		else if(command.equals("/MemberUpdateAction.me")){
			System.out.println("C : /MemberUpdateAction.me 호출");
			System.out.println("C : 전달정보를 DB에 저장 후 이동");
			
			// MemberUpdateAction 객체 생성
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberDelete.me")){
			System.out.println("C : /MemberDelete.me 호출");
			System.out.println("C : view 페이지로 이동");
			
			forward = new ActionForward();
			forward.setPath("./member/deleteForm.jsp");
			forward.setRedirect(false);
			
		}
		else if(command.equals("/MemberDeleteAction.me")){
			System.out.println("C : /MemberDeleteAction.me 호출");
			System.out.println("C : 전달받은 정보를 사용해서 DB에서 삭제 ");
			
			// MemberDeleteAction 객체 생성
			action = new MemberDeleteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/MemberList.me")){
			System.out.println("C : /MemberList.me 호출");
			System.out.println("C : DB정보를 받아서 view 출력");
			
			// MemberListAction 객체 생성
			action = new MemberListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		System.out.println("      2. 가상주소 매핑 (연결)        ");
		/* 2. 가상주소 매핑 (연결)*/
		
		/* 3. 가상주소 페이지 이동 */
		System.out.println("\n\n\n      3. 가상주소 페이지 이동        ");
		
		if(forward != null){ // 이동정보가 있을경우 페이지 이동
			if(forward.isRedirect()){
				System.out.println("C : sendRedirect 이동 ->"+forward.getPath());
				//true
				response.sendRedirect(forward.getPath());
			}else{
				//false
				System.out.println("C : forward 이동 -> "+forward.getPath());
				RequestDispatcher dis
				      = request.getRequestDispatcher(forward.getPath());
				
				dis.forward(request, response);				
			}
		}
		
		System.out.println("      3. 가상주소 페이지 이동        ");
		/* 3. 가상주소 페이지 이동 */
		
		
		
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 호출! ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 호출! ");
		doProcess(request, response);
	}
	
	
	

}
