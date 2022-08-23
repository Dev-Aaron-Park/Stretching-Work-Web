package com.aaron.stretchingwork.menuGuestBook;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaron.stretchingwork.account.AccountDAO;
import com.aaron.token.generator.AaronTokenGenerator;

@WebServlet("/GuestBookWriteController")
public class GuestBookWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestBookWriteController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			AaronTokenGenerator.generate(request);
			request.setAttribute("contentsPage", "menu/guestbook/write.jsp");
		} else {
			request.setAttribute("contentsPage", "home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			GuestBookDAO.getGuestbookdao().createGuestbook(request);
			GuestBookDAO.getGuestbookdao().readGuestbook(1, request);
			request.setAttribute("contentsPage", "menu/guestbook/guestbook.jsp");
		} else {
			request.setAttribute("contentsPage", "home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}
}
