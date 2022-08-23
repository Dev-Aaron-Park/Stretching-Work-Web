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

@WebServlet("/WorkingController")
public class WorkingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			AaronTokenGenerator.generate(request);
		}
		DateManager.getCurYear(request);
		WeatherManager.getWeathermanager().getWeather(request);
		WorkingDAO.getWorkingdao().readWorkingData(1, request);
		request.setAttribute("contentsPage", "menu/working/working.jsp");
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (AccountDAO.getAccountdao().loginCheck(request)) {
			DateManager.getCurYear(request);
			WorkingDAO.getWorkingdao().createWorkingData(request);
			WorkingDAO.getWorkingdao().readWorkingData(1, request);
			AaronTokenGenerator.generate(request);
			request.setAttribute("contentsPage", "menu/working/working.jsp");
		} else {
			request.setAttribute("contentsPage", "account/login.jsp");
		}
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
	}
}
