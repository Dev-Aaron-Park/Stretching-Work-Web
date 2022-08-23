package com.aaron.stretchingwork.account;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.aaron.db.manager.AaronDBManager;
import com.aaron.stretchingwork.home.DateManager;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AccountDAO {
	private final static AccountDAO ACCOUNTDAO = new AccountDAO();
	
	public static AccountDAO getAccountdao() {
		return ACCOUNTDAO;
	}
	
	private AccountDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void delete(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			
			Account a = (Account) req.getSession().getAttribute("userAccount");
			
			String sql = "delete from stretchingwork_user where user_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getId());
			
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "Delete Success");
				String path = req.getSession().getServletContext().getRealPath("profilePhoto");
				String photo = URLDecoder.decode(a.getPhoto(), "euc-kr");
				new File(path + "/" + photo).delete();
				
				// 해당 유저가 사이트에 쓴 글들 모두 삭제해야 함
				
			} else {
				req.setAttribute("result", "Delete Failed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "Delete Failed");
		}
		AaronDBManager.disconnect(con, pstmt, null);
	}
	
	public void getInfo(HttpServletRequest req) {
		Account a = (Account) req.getSession().getAttribute("userAccount");
		DateManager.getDate(a.getBirthday(), req);
		
		String[] addr = a.getAddr().split("&");
		req.setAttribute("addr1", addr[0]);
		req.setAttribute("addr2", addr[1]);
		req.setAttribute("addr3", addr[2]);
	}
	
	public void login(HttpServletRequest req) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = AaronDBManager.connect("taeHwanPool");
			
			req.setCharacterEncoding("euc-kr");
			String id = req.getParameter("loginID");
			String pw = req.getParameter("loginPW");
			
			// 보안 상 위험 : 해킹 시 뒤에 or user_id list '%%' 조건이 추가되면 무조건 로그인
			String sql = "select * from stretchingwork_user "
					+ "WHERE user_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			// 보인 상 DAO 에서 Password Check
			if (rs.next()) {
				String dbPW = rs.getString("user_pw");
				if (pw.equals(dbPW)) {
					Account account = new Account(id, pw, 
												rs.getString("user_name"), rs.getDate("user_birthday"),
												rs.getString("user_addr"), rs.getString("user_photo"));
				
					req.setAttribute("result", "Login Success");
					req.getSession().setAttribute("userAccount", account);
				} else {
					req.setAttribute("result", "Login Failed (Wrong Password)");
				}
			} else {
				req.setAttribute("result", "Login Failed (ID not Existed)");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "Login Failed (DB Error)");
			
		} 
		AaronDBManager.disconnect(con, pstmt, rs);
	}
	
	public boolean loginCheck(HttpServletRequest req) {
		Account loginCheck = (Account) req.getSession().getAttribute("userAccount");
		if (loginCheck != null) {
			req.setAttribute("isLogin", true);
		} else {
			req.setAttribute("isLogin", false);
		} 
		return (loginCheck != null);
	}
	
	public void logout(HttpServletRequest req) {
		req.getSession().setAttribute("userAccount", null);
	}

	public void signup(HttpServletRequest req) throws IOException {
		String path = req.getSession().getServletContext().getRealPath("profilePhoto");
		System.out.println(path);
	
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 10485760, "euc-kr", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			
			String id = mr.getParameter("signupID");
			String pw = mr.getParameter("signupPW");
			String name = mr.getParameter("signupName");
			String year = mr.getParameter("year");
			int month = Integer.parseInt(mr.getParameter("month"));
			int day = Integer.parseInt(mr.getParameter("day"));
			String birthday = String.format("%s%02d%02d", year, month, day);
			String address = mr.getParameter("signupAddr1") + "&" + mr.getParameter("signupAddr2") + "&" + mr.getParameter("signupAddr3");
			String photo = mr.getFilesystemName("signupPhoto");
			photo = URLEncoder.encode(photo, "euc-kr").replace("+", " ");
		
			
			
			String sql = "insert into stretchingwork_user "
					+ "values(?, ?, ?, to_date(?, 'YYYYMMDD'), ?, ?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, birthday);
			pstmt.setString(5, address);
			pstmt.setString(6, photo);
			
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "Sign Up Success");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("result", "Sign Up Failed");
			// 가입 실패 시 이미 업로드 되어 있을 프로필 사진 삭제 (Tomcat이랑 상관 없음)
			File f = new File(path + "/" + mr.getFilesystemName("signupPhoto"));
			f.delete();
		}
		AaronDBManager.disconnect(con, pstmt, null);
	}

	public void update(HttpServletRequest req) {
		String path = req.getSession().getServletContext().getRealPath("profilePhoto");
		
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, path, 10485760, "euc-kr", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		
		Account account = (Account) req.getSession().getAttribute("userAccount");
		String oldPhoto = account.getPhoto();
		String newPhoto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = AaronDBManager.connect("taeHwanPool");
			
			String id = mr.getParameter("userID");
			String pw = mr.getParameter("changePW");
			String name = mr.getParameter("editName");
			String year = mr.getParameter("year");
			int month = Integer.parseInt(mr.getParameter("month"));
			int day = Integer.parseInt(mr.getParameter("day"));
			String birthday = String.format("%s%02d%02d", year, month, day);
			
			String addr1 = mr.getParameter("editAddr1");
			String addr2 = mr.getParameter("editAddr2");
			String addr3 = mr.getParameter("editAddr3");
			String addr = addr1 + "&" + addr2 + "&" + addr3;
			newPhoto = mr.getFilesystemName("editPhoto");
			if (newPhoto == null) {
				newPhoto = oldPhoto;
			} else {
				newPhoto = URLEncoder.encode(newPhoto, "euc-kr").replace("+", " ");
			}
			
			String Sql = "update stretchingwork_USER "
							+ "set user_pw = ?, user_name = ?, user_birthday = to_date(?, 'YYYYMMDD'), user_addr = ?, user_photo = ? "
							+ "where user_id = ?";
				
			pstmt = con.prepareStatement(Sql);
			pstmt.setString(1, pw); 
			pstmt.setString(2, name);
			pstmt.setString(3, birthday);
			pstmt.setString(4, addr);
			pstmt.setString(5, newPhoto);
			pstmt.setString(6, id);
				
			if (pstmt.executeUpdate() == 1) {
				req.setAttribute("result", "Update Success");	
				if (!oldPhoto.equals(newPhoto)) {
					oldPhoto = URLDecoder.decode(oldPhoto, "euc-kr");
					new File(path + "/" + oldPhoto).delete();
				}
				// 수정된 계정 갱신
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Account newAccount = new Account(id, pw, name, sdf.parse(birthday), addr, newPhoto);
				req.getSession().setAttribute("userAccount", newAccount);
					
			} else {
				req.setAttribute("result", "Update Failed");	
				if (!oldPhoto.equals(newPhoto)) {
					newPhoto = URLDecoder.decode(newPhoto, "euc-kr");
					new File(path + "/" + newPhoto).delete();
				}		
			}
		
		} catch (Exception e) {
			req.setAttribute("result", "Update Failed");
			if (!oldPhoto.equals(newPhoto)) {
				try  {
					newPhoto = URLDecoder.decode(newPhoto, "euc-kr");
					new File(path + "/" + newPhoto).delete();
				} catch (UnsupportedEncodingException e1) {
				}
			}
		}
		AaronDBManager.disconnect(con, pstmt, null);
	}
}
