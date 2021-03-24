package com.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.goods.db.AdminGoodsDAO;
import com.admin.goods.db.GoodsBean;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GoodsAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" G : GoodsAddAction_execute 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		// 파일 업로드 먼저 하고 파라미터 저장
		// cos.jar 추가하기
		// upload 가상폴더 생성
		
		
		// 파일이 저장되는 위치 - 실무에서 권장하는 형태는 이거에 가깝 (펀웹에서의 명령어보다)
		ServletContext ctx = request.getServletContext();
		String realpath = ctx.getRealPath("/upload");
		System.out.println(" M : realpath :::: "+realpath);
		// C:\eclipse_JSP5\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\Model2JSP\ upload
		
		// 파일용량
		int maxSize = 10*1024*1024; //10MB
		
		MultipartRequest multi =
				new MultipartRequest(
						request,
						realpath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()//파일이름 중복시
						);
		System.out.println(" M : 파일업로드 완료");
		
		// 파라미터 저장
		
		GoodsBean goods = new GoodsBean();
		
		goods.setAmount(Integer.parseInt(multi.getParameter("amount")));
		goods.setBest(0);// 기본값 0  인기상품있을시 1로 변경
		goods.setCategory(multi.getParameter("category"));
		goods.setColor(multi.getParameter("color"));
		goods.setContent(multi.getParameter("content"));
		//goods.setDate(date); 
		
		// 업로드는 getParameter로 가져올수 없음,  "," 중요
		//String img = multi.getParameter("file1")+","+
		String img = multi.getFilesystemName("file1")+","+
				     multi.getFilesystemName("file2")+","+
				     multi.getFilesystemName("file3")+","+
				     multi.getFilesystemName("file4");
		System.out.println("M : img"+ img);
		goods.setImage(img);
		goods.setName(multi.getParameter("name"));
		
		// goods.setNum(Integer.parseInt(multi.getParameter("num")));
		// DB에서 계산해서 가져갈거라 주석처리
		
		goods.setPrice(Integer.parseInt(multi.getParameter("price")));
		goods.setSize(multi.getParameter("size"));
		
		System.out.println(" M : 전달된 파라미터값 저장완료");
		// DAO 객체 생성후 메서드 호출
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		
		agdao.insertGoods(goods);
		
		// 페이지 이동
		
		ActionForward forward = new ActionForward();
		forward.setPath("./GoodsList.ag");
		forward.setRedirect(true); // sendRedirect 방식으로 이동
		return forward;
		
	}

}
