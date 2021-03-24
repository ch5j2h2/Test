package com.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	// DB - itwill_member 테이블의 모든 처리 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="";
		
//////////////////////////////////////////////////////////		
		// 디비연결 - 커넥션풀 
		private Connection getCon() throws Exception{
			Context initCTX = new InitialContext();
			DataSource ds 
			   = (DataSource)initCTX.lookup("java:comp/env/jdbc/model2DB");
			con = ds.getConnection();
			
			System.out.println("DAO : 디비 연결 성공! -> "+con);
			
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
//////////////////////////////////////////////////////////		
		// insertMember(mb)
		public void insertMember(MemberBean mb){
			
			try {
				//1.2. 디비연결
				con = getCon();
				
				//3. sql 작성 & pstmt 객체
				// 회원가입구문 - insert구문
				
				sql ="insert into itwill_member(id,pass,name,age,gender,email,reg_date) "
						+ " values(?,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, mb.getId());
				pstmt.setString(2, mb.getPass());
				pstmt.setString(3, mb.getName());
				pstmt.setInt(4, mb.getAge());
				pstmt.setString(5, mb.getGender());
				pstmt.setString(6, mb.getEmail());
				pstmt.setTimestamp(7, mb.getReg_date());
				
				//4. sql 실행
				
				pstmt.executeUpdate();
				
				System.out.println("DAO : 회원가입 완료!");
				
			} catch (Exception e) {
				System.out.println("DAO : 회원가입 실패!!!!");
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
		}
		// insertMember(mb)
//////////////////////////////////////////////////////////		
		// loginCheck(mb)
		public int loginCheck(MemberBean mb){
			int result = -1;
			
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3. sql 생성 & pstmt 객체
				sql = "select pass from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				
				// 4. sql 실행
				rs = pstmt.executeQuery();
				// 5. 데이터 처리 
				if(rs.next()){
					// 회원
					if(mb.getPass().equals(rs.getString("pass"))){
						// 본인
						result = 1;
					}else{
						// 본인이지만 비밀번호 오류
						result = 0;
					}
				}else{
					// 비회원
					result = -1;
				}
				
				System.out.println("DAO : 로그인 체크 >> "+result);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return result;
		}
		// loginCheck(mb)
//////////////////////////////////////////////////////////		
		// getMember(id)
		public MemberBean getMember(String id){
			MemberBean mb = null;
			try {
				//1.2. 디비연결
				con = getCon();
				//3. sql 작성 & pstmt생성
				// id에 해당하는 회원정보 전부를 가져오는 구문
				sql ="select * from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4. sql 실행
				rs = pstmt.executeQuery();
				//5. 데이터 처리
				if(rs.next()){
					mb = new MemberBean();
					
					// DB -> MemberBean 저장
					mb.setAge(rs.getInt("age"));
					mb.setEmail(rs.getString("email"));
					mb.setGender(rs.getString("gender"));
					mb.setId(rs.getString("id"));
					mb.setName(rs.getString("name"));
					mb.setPass(rs.getString("pass"));
					mb.setReg_date(rs.getTimestamp("reg_date"));
				}
				
				System.out.println("DAO : 회원정보 저장 완료  "+mb);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return mb;
		}
		// getMember(id)
//////////////////////////////////////////////////////////		
		// updateMember(mb)
		public int updateMember(MemberBean mb){
			int check = -1;
			
			try {
				//1.2. 디비연결
				con = getCon();
				
				//3. sql 작성 & pstmt 객체 실행
				sql = "select pass from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				
				//4. sql 실행
				rs = pstmt.executeQuery();
				//5. 데이터 처리
				if(rs.next()){
					if(mb.getPass().equals(rs.getString("pass"))){
						// 수정
						//3. sql 작성 & pstmt 생성
						sql ="update itwill_member set "
								+ "name=?,age=?,gender=?,email=? where id=?";
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, mb.getName());
						pstmt.setInt(2, mb.getAge());
						pstmt.setString(3, mb.getGender());
						pstmt.setString(4, mb.getEmail());
						pstmt.setString(5, mb.getId());
						
						//4. sql 실행
						check = pstmt.executeUpdate();
						// => executeUpdate() : sql 쿼리 실행후 영향을받은 row수 리턴
						//check = 1; 
					}else{
					    check = 0;
					}
				}else{
					check = -1;
				}
				
				System.out.println("DAO : 회원정보 수정완료 >> "+check);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return check;
		}
		// updateMember(mb)
//////////////////////////////////////////////////////////		
		// deleteMember(mb)
		public int deleteMember(MemberBean mb){
			int check =-1;
			
			try {
				// 1.2. 디비연결
				con = getCon();
				// 3.sql 작성 & pstmt 객체 생성
				sql = "select pass from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				
				// 4.sql 실행
				rs = pstmt.executeQuery();
				// 5.데이터 처리
				if(rs.next()){
					if(mb.getPass().equals(rs.getString("pass"))){
						// 3. sql 작성
						sql = "delete from itwill_member where id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, mb.getId());
						// 4. sql 실행
						pstmt.executeUpdate();
						
						check = 1;
					}else{
						check = 0;
					}
				}else{
					check = -1;
				}

				System.out.println("DAO : 회원 탈퇴 완료 >> "+check);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return check;
		}
		// deleteMember(mb)
/////////////////////////////////////////////////////////////////////////
		//getMemberList()
		public List<MemberBean> getMemberList(){
			List<MemberBean> memberList = new ArrayList<MemberBean>();
			
			try {
				con = getCon();
				sql="select * from itwill_member";
				
				pstmt=con.prepareStatement(sql);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					MemberBean mb = new MemberBean();
					
					mb.setAge(rs.getInt("age"));
					mb.setEmail(rs.getString("email"));
					mb.setGender(rs.getString("gender"));
					mb.setId(rs.getString("id"));
					mb.setName(rs.getString("name"));
					mb.setPass(rs.getString("pass"));
					mb.setReg_date(rs.getTimestamp("reg_date"));
					
					// 리스트 한칸에 회원정보 1명의 정보를 저장
					memberList.add(mb);
				}
				System.out.println("DAO : 회원목록 저장완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			
			
			return memberList;
		}
		
		//getMemberList()
///////////////////////////////////////////////////////////////////////////		
		
		
		

	}
