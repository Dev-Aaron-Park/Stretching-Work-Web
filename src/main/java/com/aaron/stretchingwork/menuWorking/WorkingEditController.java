package com.aaron.stretchingwork.menuWorking;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaron.stretchingwork.account.AccountDAO;
import com.aaron.stretchingwork.home.DateManager;

@WebServlet("/WorkingEditController")
public class WorkingEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			Working w = new Working(request.getParameter("no"),
									request.getParameter("id"),
									"null",
									request.getParameter("todo"),
									request.getParameter("memo"),
									"null");
			request.setAttribute("working", w);
			request.setAttribute("contentsPage", "menu/working/edit.jsp");
		} else {
			request.setAttribute("contentsPage", "home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			DateManager.getCurYear(request);
			WorkingDAO.getWorkingdao().updateWorkingData(request);
			WorkingDAO.getWorkingdao().readWorkingData(1, request);
			request.setAttribute("contentsPage", "menu/working/working.jsp");
		} else {
			request.setAttribute("contentsPage", "home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
