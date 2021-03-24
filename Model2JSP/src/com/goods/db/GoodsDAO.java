package com.goods.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.admin.goods.db.GoodsBean;
import com.basket.db.BasketBean;

public class GoodsDAO {

	// DB 접근은 관리자접근과 일반유저를 분리, Bean은 그냥 사용가능
	
	
	// DB - itwill_goods 
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
				//getGoodsList(item)
				public List getGoodsList(String item){
					// all, best, category 로 item 경우를 나눌것
					// 매서드도 3개 필요하지만 한번에 해볼것 
					// >> 쿼리문에 조건달기 , String에 저장하지않고 
					// String 버퍼에 담을예정
					List goodsList = new ArrayList();
					
					StringBuffer SQL = new StringBuffer();
					// StringBuffer 업무상에서도 많이 사용 , 상황이나 개인취향에따라
					// String과 동일 사용
					// String : 매서드 실행하면 결과만 사용가능 , 메서드를 통해서 
					// StringBuffer : 자체메모리 존재, 메서드 사용하면 자체의 값이 바뀜
					
					
					try {
						con =getCon();
						
						SQL.append("select * from itwill_goods");
						
						if(item.equals("all")){
						}else if(item.equals("best")){
							SQL.append(" where best=?");
						}else {
							// category
							SQL.append(" where category=?");
						}
						
						pstmt = con.prepareStatement(SQL.toString());
						// pstmt = con.prepareStatement(SQL+""); 이것도 가능
						 // toString 지우고 오류확인해보기
						
						if(item.equals("all")){}
						else if(item.equals("best")){
							pstmt.setInt(1, 1);
							// best라 작성시 오류
							// 인기상품 1이라고 담기로 했으니깐 저렇게 표현
							// 인기상품  : 1 , 일반상품 : 0
						}else{
							pstmt.setString(1, item);
						}
						
						rs = pstmt.executeQuery();
						
						while(rs.next()){
							// 상품정보 저장(goodsBean)
							GoodsBean gb = new GoodsBean();
							
							gb.setAmount(rs.getInt("amount"));
							gb.setBest(rs.getInt("best"));
							gb.setCategory(rs.getString("category"));
							gb.setColor(rs.getString("color"));
							gb.setContent(rs.getString("content"));
							gb.setDate(rs.getString("date"));
							gb.setImage(rs.getString("image"));
							gb.setName(rs.getString("name"));
							gb.setNum(rs.getInt("num"));
							gb.setPrice(rs.getInt("price"));
							gb.setSize(rs.getString("size"));
							
							// 리스트 한칸에 저장
							
							goodsList.add(gb);
						}
						
						System.out.println("DAO : 사용자 상품정보 저장완료");
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						closeDB();
					}
					
					return goodsList;
				}
				
				
				
				//getGoodsList(item)
///////////////////////////////////////////////////////////
				//getGoods(num)
				public GoodsBean getGoods(int num){
					GoodsBean gb = null;
					// 여기 객체 만들어도 되지만 필요할때 만들어쓰자
					// 자원해재하면 날라갈테니깐
					// 쓰레기남는다?
					try {
						con = getCon();
						
						sql ="select * from itwill_goods where num=?";
						
						pstmt = con.prepareStatement(sql);
						
						pstmt.setInt(1, num);
						
						rs = pstmt.executeQuery();
						
						if(rs.next()){
							
							
							gb =new GoodsBean();
							
							gb.setAmount(rs.getInt("amount"));
							gb.setBest(rs.getInt("best"));
							gb.setCategory(rs.getString("category"));
							gb.setColor(rs.getString("color"));
							gb.setContent(rs.getString("content"));
							gb.setDate(rs.getString("date"));
							gb.setImage(rs.getString("image"));
							gb.setName(rs.getString("name"));
							gb.setNum(rs.getInt("num"));
							gb.setPrice(rs.getInt("price"));
							gb.setSize(rs.getString("size"));
							
							
						}
						
						System.out.println("DAO : 상품정보 저장 완료");
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						closeDB();
					}
					
					
					return gb;
				}
				//getGoods(num)
///////////////////////////////////////////////////////////
				//updateAmount(basketList)-구매후 작동
				public void updateAmount(List basketList){
					
					try {
						con =getCon();
						// 장바구니에 있는 정보중에서 각 상품의 개수만큼 감소
						// => 반복(장바구니 물품 개수만큼
					for(int i=0; i<basketList.size(); i++){
						BasketBean bk =(BasketBean) basketList.get(i);
						
						sql="update itwill_goods set amount=amount-? where num=?";
												
						pstmt = con.prepareStatement(sql);
						
						pstmt.setInt(1, bk.getB_g_amount());
						pstmt.setInt(2, bk.getB_g_num());
						
						
						// 4. 실행
						pstmt.executeUpdate();
						 
					}
					
					System.out.println("DAO : 구매후 판매수량 수정완료");
					
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						closeDB();
					} 
					
					
				}
				
				
				//updateAmount(basketList)-구매후 작동
///////////////////////////////////////////////////////////
				////deleteBasket(num)
				public void basketDelete(String id){
					
					try {
						con = getCon();
						
						sql="delete from itwill_basket where b_m_id=?";
						
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, id);
						
						pstmt.executeUpdate();
						
						System.out.println("DAO : 주문후 장바구니 삭제완료");
						
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						closeDB();
					}
					
					 
					
				}
				//deleteBasket(num)

}
