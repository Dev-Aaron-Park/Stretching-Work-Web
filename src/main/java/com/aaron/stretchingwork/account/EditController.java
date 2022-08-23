package com.aaron.stretchingwork.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaron.stretchingwork.home.DateManager;

@WebServlet("/EditController")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			AccountDAO.getAccountdao().getInfo(request);
			DateManager.getCurYear(request);
			request.setAttribute("contentsPage", "account/edit.jsp");
		} else {
			request.setAttribute("contentsPage", "home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			AccountDAO.getAccountdao().update(request);
			request.setAttribute("result", "UpdateSuccess");
		}
		request.setAttribute("contentsPage", "home.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
