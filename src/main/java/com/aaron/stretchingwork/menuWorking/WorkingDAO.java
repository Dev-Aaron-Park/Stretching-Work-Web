package com.aaron.stretchingwork.menuWorking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.aaron.db.manager.AaronDBManager;
import com.aaron.stretchingwork.account.Account;

public class WorkingDAO {
	private int allDataCount;

	private final static WorkingDAO WORKINGDAO = new WorkingDAO();
	
	private WorkingDAO() {
		// TODO Auto-generated constructor stub
	}

	public static WorkingDAO getWorkingdao() {
		return WORKINGDAO;
	}
	
	public void createWorkingData(HttpServletRequest req) throws NullPointerException {
		// 새로고침 : 방금 전에 했던 요청을 동일하게 다시 요청
		// 전과 같은 요청이 또 들어오면 insert 하지 말아야 함
		// 요청을 할 때 마다 새로운 토큰 발급
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			
			req.setCharacterEncoding("euc-kr");
			String token = req.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("successWorkingToken");
			
			if (lastToken != null && token.equals(lastToken)) {
				return;
			}
			
			Account a = (Account) req.getSession().getAttribute("userAccount");
			String id = a.getId();
			String year = req.getParameter("year");
			int month = Integer.parseInt(req.getParameter("month"));
			int day = Integer.parseInt(req.getParameter("day"));
			int hour = Integer.parseInt(req.getParameter("hour"));
			int minute = Integer.parseInt(req.getParameter("minute"));
			
			String date = String.format("%s&%02d&%02d&%02d&%02d",
										year,
										month,
										day,
										hour,
										minute);
			String todo = req.getParameter("todo");
			String memo = req.getParameter("memo");
			String imp = req.getParameter("imp");
			
			
			String sql = "insert into stretchingwork_work "
					+ "values(stretchingwork_work_seq.nextval, ?, to_date(?, 'YYYY&MM&DD&HH24&MI'), ?, ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, date);
			pstmt.setString(3, todo);
			pstmt.setString(4, memo);
			pstmt.setString(5, imp);
			
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "Add Success");
				req.getSession().setAttribute("successWorkingToken", token);
				allDataCount++;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "Add Failed");
		} finally {
			AaronDBManager.disconnect(con, pstmt, null);
		}
	}

	public void readWorkingData(int page, HttpServletRequest req){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = AaronDBManager.connect("taeHwanPool");
			// 실행하기 귀찮//////
			setAllDataCount();
			///////////////////
			int dataPerPage = 10;
			int pageCount = (int) Math.ceil(allDataCount / (double) dataPerPage);
			int start = (page - 1) * dataPerPage + 1;
			int end = page * dataPerPage;
			
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("page", page);
			
			req.setCharacterEncoding("euc-kr");
			
			Account a = (Account) req.getSession().getAttribute("userAccount");
			if (a == null) {
				return;
			}
			String id = a.getId();
			
			String sql = "select * "
					+ "from ( "
					+ "	select rownum as rn, work_no, work_id, work_date, work_todo, work_memo, work_imp "
					+ "	from ( "
					+ "		select work_no, work_id, work_date, work_todo, work_memo, work_imp "
					+ "		from stretchingwork_work where work_id= ? "
					+ "		order by work_date, work_imp, work_todo "
					+ "	) "
					+ ") where rn >= ? and rn <= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			
//			Date now = new Date();
//			String today = sdf.format(now).substring(0, 10);
//			String date;
//			String color;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			ArrayList<Working> workList = new ArrayList<Working>();
			while (rs.next()) {
//				date = sdf.format(rs.getDate("work_date"));
//				color = date.substring(0, 10).equals(today.substring(0, 10)) ? "today" : "other";
				
				workList.add(new Working(rs.getInt("work_no")+"",
										rs.getString("work_id"),
										sdf.format(rs.getDate("work_date")),
										rs.getString("work_todo"),
										rs.getString("work_memo"),
										rs.getString("work_imp")));
			}
			
			if (page != 1 && workList.size() == 0) {
				readWorkingData(1, req);
				return;
			}
			
			req.setAttribute("workList", workList);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			AaronDBManager.disconnect(con, pstmt, rs);
		}
	}

	public void updateWorkingData(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try { 
			con = AaronDBManager.connect("taeHwanPool");
			
			req.setCharacterEncoding("euc-kr");
			String no = req.getParameter("no");
			String id = req.getParameter("id");
			Account a = (Account) req.getSession().getAttribute("userAccount");
			String userId = a.getId();
			String userPW = a.getPw();
			String todo = req.getParameter("todo");
			String memo = req.getParameter("memo");
			String imp = req.getParameter("imp");
			
			if (permissionCheck(no, id, userId, userPW)) {
				String sql = "update stretchingwork_work set work_todo = ?, work_memo = ?, work_imp = ? where work_no = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, todo);
				pstmt.setString(2, memo);
				pstmt.setString(3, imp);
				pstmt.setString(4, no);
				
				if (pstmt.executeUpdate() == 1) {
					req.setAttribute("result", "Update Success");
				} else {
					req.setAttribute("result", "Update Failed");
				}
			} else {
				req.setAttribute("result", "Update Failed");
			}
			
		} catch (Exception e) {
			req.setAttribute("result", "Update Failed");
			e.printStackTrace();
		}
		AaronDBManager.disconnect(con, pstmt, null);
	}

	public void deleteWorkingData(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			String no = (String) req.getAttribute("no");
			String id = (String) req.getAttribute("id");
			Account a = (Account) req.getSession().getAttribute("userAccount");
			String userId = a.getId();
			String userPw = a.getPw();
			
			System.out.println(no);
			System.out.println(id);
			System.out.println(userId);
			System.out.println(userPw);
			
			if (permissionCheck(no, id, userId, userPw)) {
				String sql = "delete from stretchingwork_work where work_no=?";
				System.out.println("permissionCheck Test");
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, no);
				
				if (pstmt.executeUpdate() == 1) {
					req.setAttribute("result", "Delete Success");
				} else {
					req.setAttribute("result", "Delete Failed");
				}
			} else {
				req.setAttribute("result", "Delete Failed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "Delete Failed");
		} finally {
			AaronDBManager.disconnect(con, pstmt, null);
		}
	}
	
	public boolean permissionCheck(String workingNo, String workingID, String userID, String userPW) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			String sql = "select user_pw from stretchingwork_work, stretchingwork_user "
					+ "where work_id = user_id and work_id = ? and work_no = ?";
			
			System.out.println("Per--------------------------");
			System.out.println(workingNo);
			System.out.println(workingID);
			System.out.println(userID);
			System.out.println(userPW);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, workingID);
			pstmt.setInt(2, Integer.parseInt(workingNo));
			rs = pstmt.executeQuery();
			
			String WorkingPW = null;
			if (rs.next()) {
				WorkingPW = rs.getString("user_pw");
				if (workingID.equals(userID) && WorkingPW.equals(userPW)) {
					return true;
				} else {
					return false;
				}
				
			} else {
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			AaronDBManager.disconnect(con, pstmt, rs);
		}
	}
	
	public void setAllDataCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			String sql = "select count(*) from stretchingwork_work";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			allDataCount = rs.getInt("count(*)");
			
			
		} catch (Exception e) {
		}
		AaronDBManager.disconnect(con, pstmt, rs);
	}
	
	public int getAllDataCount() {
		return allDataCount;
	}
}
