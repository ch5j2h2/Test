package com.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.admin.goods.db.GoodsBean;

public class BasketDAO {

	
	// DB - itwill_basket 
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql ="";
	
//////////////////////////////////////////////////////////	
	// 디비연결
	private Connection getCon() throws Exception{
		Context initCTX = new InitialContext();
		DataSource ds 
		   = (DataSource)initCTX.lookup("java:comp/env/jdbc/model2DB");
		con = ds.getConnection();
		
		System.out.println("DAO : 디비연결 ::::::: "+con);
		
		return con;
	}
//////////////////////////////////////////////////////////	
	// 자원해제
	public void closeDB(){
		try {
			if(rs != null){ rs.close(); }
			if(pstmt != null){ pstmt.close(); }
			if(con != null){ con.close(); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
///////////////////////////////////////////////////////////
	//checkGoods(basket)
	public int checkGoods(BasketBean basket){
		// 아이디, 상품번호, 사이즈, 컬러
		// 동일상품 있는지 확인하기 위해서 모든 조건 중복 확인해야함
		// 아이디만 또는 상품번호만 또는 사이즈만 또는 컬러면 이런식으로
		// 한종목으로만은 중복 체크 어려움
		int check = -1;
		
		try {
			con = getCon();
			
			sql = "select * from itwill_basket "
					+ "where b_m_id=? and b_g_num=? and "
					+ "b_g_size=? and b_g_color=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, basket.getB_m_id());
			pstmt.setInt(2, basket.getB_g_num());
			pstmt.setString(3, basket.getB_g_size());
			pstmt.setString(4, basket.getB_g_color());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				// 장바구니에 동일한 옵션의 상품이 존재 => 수량만 증가 (update)
				check = 1;
				
				sql = "update itwill_basket set b_g_amount=b_g_amount+? "
						+ "where b_m_id=? and b_g_num=? and b_g_size=? "
						+ "and b_g_color=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, basket.getB_g_amount());
				pstmt.setString(2, basket.getB_m_id());
				pstmt.setInt(3, basket.getB_g_num());
				pstmt.setString(4, basket.getB_g_size());
				pstmt.setString(5, basket.getB_g_color());
								
				pstmt.executeUpdate();
				
				System.out.println("DAO : 동일상품 수량 수정완료");
			}
			
				System.out.println("DAO : 장바구니 중복 체크 완료");
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	
		return check;
	}
//checkGoods(basket)
///////////////////////////////////////////////////////////
	//basketAdd(basket)
	
	public void basketAdd(BasketBean basket){
		int b_num = 0;
		
		try {
			con = getCon();
			
			sql="select max(b_num) from itwill_basket";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				//rs.getInt("max(b_num)")+1;
				b_num = rs.getInt(1)+1;
			}
			System.out.println("DAO : 장바구니번호 :"+b_num);
			
			// 3. insert
			
			sql="insert into itwill_basket values(?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, b_num);
			pstmt.setString(2, basket.getB_m_id());
			pstmt.setInt(3, basket.getB_g_num());
			pstmt.setInt(4, basket.getB_g_amount());
			pstmt.setString(5, basket.getB_g_size());
			pstmt.setString(6, basket.getB_g_color());
			
			// 4.
			pstmt.executeUpdate();
			
			System.out.println("DAO : 장바구니 저장완료");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
	}
	 
	//basketAdd(basket)
///////////////////////////////////////////////////////////
	//getBasketList(id) 
	// 정보들의 List와 그 정보들의 객체들을 담을 객체의 List 필요
	// 3/11 사진찍어둠
	public Vector getBasketList(String id){
		// 상품정보 + 장바구니정보 저장
		/*
		ArrayList 써도 되지만 리스트 두개를 또 다른 리스트에 담을 예정
		그러면 헷갈리기때문에 제일 밖 리스트를 벡터로 만듦
		*/
		Vector totalList = new Vector();
		
		// 장바구니정보저장 ( 상품번호, 구매수량, 옵션 .... )
		List basketList = new ArrayList();
		// 상품정보 저장 (상품이미지, 가격, 이름.....)
		List goodsList = new ArrayList();
		
		try {
			con = getCon();
			
			sql = "select * from itwill_basket where b_m_id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()){
				//장바구니가 있다
				// 장바구니 정보를 저장 (bean - > List)
				BasketBean bb = new BasketBean();
				
				bb.setB_date(rs.getDate("b_date"));
				bb.setB_g_amount(rs.getInt("b_g_amount"));
				bb.setB_g_color(rs.getString("b_g_color"));
				bb.setB_g_num(rs.getInt("b_g_num"));
				bb.setB_g_size(rs.getString("b_g_size"));
				bb.setB_m_id(rs.getString("b_m_id"));
				bb.setB_num(rs.getInt("b_num"));
				
				basketList.add(bb);
				
				// 장바구니에 저장된 상품의 정보를 DB에서 가져오기
				
				sql="select * from itwill_goods where num=?";
				
				PreparedStatement pstmt2 = con.prepareStatement(sql);
				// 그냥 pstmt 사용시 정보찾을수없음
				// 위에 pstmt와 겹쳐서 
				pstmt2.setInt(1, bb.getB_g_num());
				// 따로 객체 선언안하고 bb에서 불러온거 사용하기
				
				ResultSet rs2 = pstmt2.executeQuery();
				
				if(rs2.next()){
					// 상품번호에 해당하는 정보가 있을때 정보저장(bean -> List)
					GoodsBean gb = new GoodsBean();
					
					gb.setName(rs2.getString("name"));
					gb.setPrice(rs2.getInt("price"));
					gb.setImage(rs2.getString("image"));
					//그외 나머지정보 필요한것만 가져오기
					
					goodsList.add(gb);
					
				}
				
				
				
			}//while
			
			System.out.println("DAO : 장바구니 + 상품정보 저장 완료(List)");
			
			totalList.add(basketList);
			totalList.add(goodsList);
			
			System.out.println("DAO : 벡터0-장바구니리스트, 벡터1-상품리스트");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		
		return totalList;
	}
	 
	//getBasketList(id)
///////////////////////////////////////////////////////////
	//deleteBasket(num)
	public void basketDelete(int b_num){
		
		try {
			con = getCon();
			
			sql="delete from itwill_basket where b_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, b_num);
			
			pstmt.executeUpdate();
			
			System.out.println("DAO : 장바구니 삭제완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
	 
	}
	//deleteBasket(num)
///////////////////////////////////////////////////////////
	//deleteBasket(id)
		public void basketDelete(String id){
			
			try {
				con = getCon();
				
				sql = "delete from itwill_basket where b_m_id=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				
				pstmt.executeUpdate();
				
				System.out.println("DAO : 주문 후 장바구니 삭제완료");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
		 
		}
		//deleteBasket(num)
///////////////////////////////////////////////////////////
			
	
	
}