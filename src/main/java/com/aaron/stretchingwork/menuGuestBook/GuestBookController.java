package com.aaron.stretchingwork.menuGuestBook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaron.stretchingwork.account.Account;
import com.aaron.stretchingwork.account.AccountDAO;
import com.aaron.token.generator.AaronTokenGenerator;

@WebServlet("/GuestBookController")
public class GuestBookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.getAccountdao().loginCheck(request);
		GuestBookDAO.getGuestbookdao().readGuestbook(1, request);
		request.setAttribute("contentsPage", "menu/guestbook/guestbook.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			Account a = (Account) request.getSession().getAttribute("userAccount");
			if (GuestBookDAO.getGuestbookdao().permissionCheck(request.getParameter("guestbookNo"),
																request.getParameter("guestbookId"),
																a.getId(),
																a.getPw())) {
				
				AaronTokenGenerator.generate(request);
				GuestBook g = new GuestBook(request.getParameter("guestbookNo"),
											"null",
											"null",
											request.getParameter("guestbookTitle"),
											request.getParameter("guestbookContents"),
											"null");
				request.setAttribute("guestbook", g);
				request.setAttribute("contentsPage", "menu/guestbook/edit.jsp");
			} else {
				request.setAttribute("contentsPage", "home.jsp");
			}
		} else {
			request.setAttribute("contentsPage", "home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
