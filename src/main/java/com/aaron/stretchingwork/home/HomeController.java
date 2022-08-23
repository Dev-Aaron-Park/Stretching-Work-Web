package com.aaron.stretchingwork.home;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaron.stretchingwork.account.AccountDAO;

@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.getAccountdao().loginCheck(request);
		request.setAttribute("contentsPage", "home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAO.getAccountdao().login(request);
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			request.setAttribute("contentsPage", "home.jsp");
		} else {
			request.setAttribute("contentsPage", "account/login.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}
}
