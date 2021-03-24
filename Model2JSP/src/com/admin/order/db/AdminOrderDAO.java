package com.admin.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.order.db.OrderBean;

public class AdminOrderDAO {

	
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
		//getAdminOrderList()
		public List getAdminOrderList(){
			List orderAllList = new ArrayList();

			try {
				//1.2. 디비연결
				con = getCon();
				//3. sql
				sql =  "select o_trade_num,o_g_name,o_g_amount,"
						+ "o_g_size,o_g_color,sum(o_sum_money) as o_sum_money,"
						+ "o_trans_num,o_date,o_status,o_trade_type, o_m_id from itwill_order "
						+ "group by o_trade_num order by o_trade_num desc";
				
				pstmt = con.prepareStatement(sql);
				 
				
				//4. sql 실행
				rs = pstmt.executeQuery();
				
				//5. 데이터 처리
				while(rs.next()){
					OrderBean ob = new OrderBean();
					
					ob.setO_date(rs.getDate("o_date"));
					ob.setO_g_amount(rs.getInt("o_g_amount"));
					ob.setO_g_color(rs.getString("o_g_color"));
					ob.setO_g_name(rs.getString("o_g_name"));
					ob.setO_g_size(rs.getString("o_g_size"));
					ob.setO_trade_num(rs.getString("o_trade_num"));
					ob.setO_trans_num(rs.getString("o_trans_num"));
					ob.setO_sum_money(rs.getInt("o_sum_money"));
					ob.setO_status(rs.getInt("o_status"));
					ob.setO_trade_type(rs.getString("o_trade_type"));
					ob.setO_m_id(rs.getString("o_m_id"));
					
					orderAllList.add(ob);
				}//while
				
				System.out.println("DAO : 주문 목록 저장완료");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return orderAllList;
		}
		
		//getAdminOrderList()
	///////////////////////////////////////////////////////////
		// getAdminOrderDetail(trade_num)
		public List	getAdminOrderDetail(String trade_num){
			List adminOrderDetail = new ArrayList();
			try {
				con = getCon();
				
				sql="select * from itwill_order where o_trade_num=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, trade_num);
				
				// sql 실행
				rs = pstmt.executeQuery();
				// 데이터 처리
				while(rs.next()){
					OrderBean ob = new OrderBean();
					
					ob.setO_date(rs.getDate("o_date"));
					ob.setO_g_amount(rs.getInt("o_g_amount"));
					ob.setO_g_color(rs.getString("o_g_color"));
					ob.setO_g_name(rs.getString("o_g_name"));
					ob.setO_g_size(rs.getString("o_g_size"));
					ob.setO_trade_num(rs.getString("o_trade_num"));
					ob.setO_trans_num(rs.getString("o_trans_num"));
					ob.setO_sum_money(rs.getInt("o_sum_money"));
					ob.setO_status(rs.getInt("o_status"));
					ob.setO_trade_type(rs.getString("o_trade_type"));
					ob.setO_m_id(rs.getString("o_m_id"));
					
					adminOrderDetail.add(ob);
					 
					
				}
				
				System.out.println("DAO : 관리자 주문 디테일 페이지 정보 저장완료");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			 
			return adminOrderDetail; 
		}
		
		
		// getAdminOrderDetail(trade_num)
	///////////////////////////////////////////////////////////
		//updateOrder(ob)
		public void updateOrder(OrderBean ob){
			
			try {
				con=getCon();
				// sql - 주문번호에 해당하는 주문에 주문상태와 운송장 번호를 수정
				sql="update itwill_order set o_status=?, o_trans_num=? "
						+ "where o_trade_num=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ob.getO_status());
				pstmt.setString(2, ob.getO_trans_num());
				pstmt.setString(3, ob.getO_trade_num());
				
				pstmt.executeUpdate();
				
				System.out.println("DAO : 주문번호에 해당하는 주문 상태 및 운송장 번호 수정 완료");
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		}
		
		
		
		
		
		
		
		//updateOrder(ob)
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////
}
