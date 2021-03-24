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
	// DB - itwill_member ���̺��� ��� ó�� 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="";
		
//////////////////////////////////////////////////////////		
		// ��񿬰� - Ŀ�ؼ�Ǯ 
		private Connection getCon() throws Exception{
			Context initCTX = new InitialContext();
			DataSource ds 
			   = (DataSource)initCTX.lookup("java:comp/env/jdbc/model2DB");
			con = ds.getConnection();
			
			System.out.println("DAO : ��� ���� ����! -> "+con);
			
			return con;
		}
//////////////////////////////////////////////////////////		
		// �ڿ����� 
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
				//1.2. ��񿬰�
				con = getCon();
				
				//3. sql �ۼ� & pstmt ��ü
				// ȸ�����Ա��� - insert����
				
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
				
				//4. sql ����
				
				pstmt.executeUpdate();
				
				System.out.println("DAO : ȸ������ �Ϸ�!");
				
			} catch (Exception e) {
				System.out.println("DAO : ȸ������ ����!!!!");
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
				// 1.2. ��񿬰�
				con = getCon();
				// 3. sql ���� & pstmt ��ü
				sql = "select pass from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				
				// 4. sql ����
				rs = pstmt.executeQuery();
				// 5. ������ ó�� 
				if(rs.next()){
					// ȸ��
					if(mb.getPass().equals(rs.getString("pass"))){
						// ����
						result = 1;
					}else{
						// ���������� ��й�ȣ ����
						result = 0;
					}
				}else{
					// ��ȸ��
					result = -1;
				}
				
				System.out.println("DAO : �α��� üũ >> "+result);
				
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
				//1.2. ��񿬰�
				con = getCon();
				//3. sql �ۼ� & pstmt����
				// id�� �ش��ϴ� ȸ������ ���θ� �������� ����
				sql ="select * from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				//4. sql ����
				rs = pstmt.executeQuery();
				//5. ������ ó��
				if(rs.next()){
					mb = new MemberBean();
					
					// DB -> MemberBean ����
					mb.setAge(rs.getInt("age"));
					mb.setEmail(rs.getString("email"));
					mb.setGender(rs.getString("gender"));
					mb.setId(rs.getString("id"));
					mb.setName(rs.getString("name"));
					mb.setPass(rs.getString("pass"));
					mb.setReg_date(rs.getTimestamp("reg_date"));
				}
				
				System.out.println("DAO : ȸ������ ���� �Ϸ�  "+mb);
				
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
				//1.2. ��񿬰�
				con = getCon();
				
				//3. sql �ۼ� & pstmt ��ü ����
				sql = "select pass from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				
				//4. sql ����
				rs = pstmt.executeQuery();
				//5. ������ ó��
				if(rs.next()){
					if(mb.getPass().equals(rs.getString("pass"))){
						// ����
						//3. sql �ۼ� & pstmt ����
						sql ="update itwill_member set "
								+ "name=?,age=?,gender=?,email=? where id=?";
						pstmt = con.prepareStatement(sql);
						
						pstmt.setString(1, mb.getName());
						pstmt.setInt(2, mb.getAge());
						pstmt.setString(3, mb.getGender());
						pstmt.setString(4, mb.getEmail());
						pstmt.setString(5, mb.getId());
						
						//4. sql ����
						check = pstmt.executeUpdate();
						// => executeUpdate() : sql ���� ������ ���������� row�� ����
						//check = 1; 
					}else{
					    check = 0;
					}
				}else{
					check = -1;
				}
				
				System.out.println("DAO : ȸ������ �����Ϸ� >> "+check);
				
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
				// 1.2. ��񿬰�
				con = getCon();
				// 3.sql �ۼ� & pstmt ��ü ����
				sql = "select pass from itwill_member where id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, mb.getId());
				
				// 4.sql ����
				rs = pstmt.executeQuery();
				// 5.������ ó��
				if(rs.next()){
					if(mb.getPass().equals(rs.getString("pass"))){
						// 3. sql �ۼ�
						sql = "delete from itwill_member where id=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, mb.getId());
						// 4. sql ����
						pstmt.executeUpdate();
						
						check = 1;
					}else{
						check = 0;
					}
				}else{
					check = -1;
				}

				System.out.println("DAO : ȸ�� Ż�� �Ϸ� >> "+check);
				
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
					
					// ����Ʈ ��ĭ�� ȸ������ 1���� ������ ����
					memberList.add(mb);
				}
				System.out.println("DAO : ȸ����� ����Ϸ�");
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
