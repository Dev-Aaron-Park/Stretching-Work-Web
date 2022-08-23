package com.aaron.stretchingwork.menuWorking;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaron.stretchingwork.account.AccountDAO;
import com.aaron.stretchingwork.home.DateManager;
import com.aaron.token.generator.AaronTokenGenerator;

@WebServlet("/WorkingPageController")
public class WorkingPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			AaronTokenGenerator.generate(request);
		}
		DateManager.getCurYear(request);
		WorkingDAO.getWorkingdao().readWorkingData(Integer.parseInt(request.getParameter("page")), request);
		request.setAttribute("contentsPage", "menu/working/working.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			request.setAttribute("no", request.getParameter("no")); 
			request.setAttribute("id", request.getParameter("id")); 
			WorkingDAO.getWorkingdao().deleteWorkingData(request);
			DateManager.getCurYear(request);
			WorkingDAO.getWorkingdao().readWorkingData(1, request);
			AaronTokenGenerator.generate(request);
			request.setAttribute("contentsPage", "menu/working/working.jsp");
		} else {
			request.setAttribute("contentsPage", "home.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

}
