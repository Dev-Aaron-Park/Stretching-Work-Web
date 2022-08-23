package com.aaron.stretchingwork.menuGuestBook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.aaron.db.manager.AaronDBManager;
import com.aaron.stretchingwork.account.Account;

public class GuestBookDAO {
	private int allDataCount;
	private final static GuestBookDAO GUESTBOOKDAO = new GuestBookDAO();
	
	private GuestBookDAO() {
		// TODO Auto-generated constructor stub
	}

	public static GuestBookDAO getGuestbookdao() {
		return GUESTBOOKDAO;
	}

	public int getAllDataCount() {
		return allDataCount;
	}

	public void setAllDataCount() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			String sql = "select count(*) from stretchingwork_guestbook";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			allDataCount = rs.getInt("count(*)");
			
			
		} catch (Exception e) {
		}
		AaronDBManager.disconnect(con, pstmt, rs);
	}

	public void createGuestbook(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			
			req.setCharacterEncoding("euc-kr");
			String token = req.getParameter("token");
			String lastToken = (String) req.getSession().getAttribute("successGuestBookToken");
			
			if (lastToken != null && token.equals(lastToken)) {
				return;
			}
			
			Account a = (Account) req.getSession().getAttribute("userAccount");
			String id = a.getId();
			String title = req.getParameter("guestbookTitle");
			String contents = req.getParameter("guestbookContents").replace("\r\n", "<br>");
			
			String sql = "insert into stretchingwork_guestbook "
					+ "values(stretchingwork_guestbook_seq.nextval, ?, ?, ?, sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, title);
			pstmt.setString(3, contents);
			
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "Add Success");
				req.getSession().setAttribute("successGuestBookToken", token);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "Add Failed");
		} finally {
			AaronDBManager.disconnect(con, pstmt, null);
		}
	}

	public void readGuestbook(int page, HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			//////////////////
			setAllDataCount();
			//////////////////
			int dataPerPage = 5;
			int pageCount = (int) Math.ceil(allDataCount / (double) dataPerPage);
			int start = (page - 1) * dataPerPage + 1;
			int end = page * dataPerPage;
			
			req.setAttribute("pageCount", pageCount);
			req.setAttribute("page", page);
			
			req.setCharacterEncoding("euc-kr");
			
			String sql = "select *  "
					+ "from ( "
					+ "	select rownum as rn, user_id, user_photo, gb_no, gb_title, gb_contents, gb_date  "
					+ "	from ( "
					+ "		select user_id, user_photo, gb_no, gb_title, gb_contents, gb_date  "
					+ "		from stretchingwork_user, stretchingwork_guestbook  "
					+ "		where user_id = gb_id  "
					+ "		order by gb_date desc "
					+ "	) "
					+ ") "
					+ "where rn >= ? and rn <= ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			ArrayList<GuestBook> gbList = new ArrayList<GuestBook>();
			while (rs.next()) {
				gbList.add(new GuestBook(rs.getString("gb_no"),
										rs.getString("user_id"),
										rs.getString("user_photo"),
										rs.getString("gb_title"),
										rs.getString("gb_contents"),
										sdf.format(rs.getDate("gb_date"))));
			}
			
			if (page != 1 && gbList.size() == 0) {
				readGuestbook(1, req);
				return;
			}
			
			req.setAttribute("gbList", gbList);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			AaronDBManager.disconnect(con, pstmt, rs);
		}
	}

	public void deleteGuestbook(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = AaronDBManager.connect("taeHwanPool");
			String gbNo = req.getParameter("guestbookNo");
			String gbId = req.getParameter("guestbookId");
			Account a = (Account) req.getSession().getAttribute("userAccount");
			String userId = a.getId();
			String userPW = a.getPw();
			if (permissionCheck(gbNo, gbId, userId, userPW)) {
				String sql = "delete from stretchingwork_guestbook where gb_no=?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, gbNo);
				
				if (pstmt.executeUpdate() == 1) {
					req.setAttribute("result", "Delete Success");
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
	
	public void updateGuestBook(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try { 
			con = AaronDBManager.connect("taeHwanPool");
			
			req.setCharacterEncoding("euc-kr");
			String gbTitle = req.getParameter("guestbookEditTitle");
			String gbContents = req.getParameter("guestbookEditContents");
			int gbNo = Integer.parseInt(req.getParameter("guestbookNo"));

			String sql = "update stretchingwork_guestbook set gb_title = ?, gb_contents = ? where gb_no = ?";
				
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gbTitle);
			pstmt.setString(2, gbContents);
			pstmt.setInt(3, gbNo);
			
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "Update Success");
			} else {
				req.setAttribute("result", "Update Failed");
			}
			
		} catch (Exception e) {
			req.setAttribute("result", "Update Failed");
			e.printStackTrace();
		}
		AaronDBManager.disconnect(con, pstmt, null);
	}
	
	public boolean permissionCheck(String guestBookNo, String guestbookID, String userID, String userPW) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			String sql = "select user_pw from stretchingwork_guestbook, stretchingwork_user "
					+ "where gb_id = user_id and gb_id = ? and gb_no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, guestbookID);
			pstmt.setInt(2, Integer.parseInt(guestBookNo));
			rs = pstmt.executeQuery();
			
			String guestbookPW = null;
			if (rs.next()) {
				guestbookPW = rs.getString("user_pw");
				if (guestbookID.equals(userID) && guestbookPW.equals(userPW)) {
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
}
