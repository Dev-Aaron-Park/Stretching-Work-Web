package com.aaron.stretchingwork.menuGuestBook;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaron.stretchingwork.account.AccountDAO;

@WebServlet("/GuestBookEditController")
public class GuestBookEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			GuestBookDAO.getGuestbookdao().updateGuestBook(request);
			GuestBookDAO.getGuestbookdao().readGuestbook(1, request);
			request.setAttribute("contentsPage", "menu/guestbook/guestbook.jsp");
		} else {
			request.setAttribute("contentsPage", "home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
