package com.pp.cruiselogsql.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pp.cruiselogsql.dao.CruiseDAOImpl;
import com.pp.cruiselogsql.dao.CruiseDao;
import com.pp.cruiselogsql.model.Cruise;
import com.pp.cruiselogsql.services.CruiseService;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/CruisesController")
public class CruisesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CruiseService cruiseService = CruiseService.getInstance();
	CruiseDao cruiseDao = CruiseDAOImpl.getInstance();
	String task = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		task = request.getParameter("task");
		if (task == null)
			task = "listCruises";

		switch (task) {
		case "addCruise":
			createCruise(request, response);
			break;

		case "listCruises":
			listCruises(request, response);
			break;
		case "showCruise":
			showCruise(request, response);
			break;
		case "editCruise":
			editCruise(request, response);
			break;
		case "updateCruise":
			updateCruise(request, response);
			break;
		case "deleteCruise":
			deleteCruise(request, response);
			break;
		case "addRandomCruise":
			Cruise cruise = cruiseService.getRandomCruise();
			cruiseDao.saveCruise(cruise);
			listCruises(request, response);
			break;
		default:
			System.out.println("Switch defaulf");
			break;
		}
	}

	private void listCruises(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Cruise> cruises = cruiseDao.getAllCruises();
		request.setAttribute("cruisesList", cruises);
		request.getRequestDispatcher("cruises-list.jsp").forward(request, response);
	}

	private void showCruise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("cruiseId"));
		request.setAttribute("cruise", cruiseDao.getCruiseById(id));
		request.getRequestDispatcher("cruise-view.jsp").forward(request, response);

	}

	private void createCruise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cruise cruise = getCruiseFromRequest(request, response);
		cruiseDao.saveCruise(cruise);
		listCruises(request, response);
	}

	private void editCruise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cruise cruise = cruiseDao.getCruiseById(Integer.parseInt(request.getParameter("cruiseId")));
		request.setAttribute("cruiseToUpdate", cruise);
		request.getRequestDispatcher("cruise-update-form.jsp").forward(request, response);

	}

	private void updateCruise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("cruiseId"));
		Cruise cruise = getCruiseFromRequest(request, response);
		cruiseDao.updateCruiseById(id, cruise);
		showCruise(request, response);
	}

	private void deleteCruise(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		cruiseDao.deleteCruiseById(Integer.parseInt(request.getParameter("cruiseId")));
		listCruises(request, response);
	}
	
	private Cruise getCruiseFromRequest(HttpServletRequest request, HttpServletResponse response) {
		Cruise cruise = new Cruise();
		cruise.setCaptainName(request.getParameter("captainName"));
		cruise.setYachtName(request.getParameter("yachtName"));
		cruise.setLocation(request.getParameter("location"));
		cruise.setDistance(Integer.parseInt(request.getParameter("distance")));
		cruise.setStartDate(LocalDate.parse(request.getParameter("startDate")));
		cruise.setEndDate(LocalDate.parse(request.getParameter("endDate")));
		
		cruise.getCrew().clear();
		int i = 0;
		while (request.getParameter("crew" + i)!=null && !request.getParameter("crew" + i).isEmpty()) {
			System.out.println(i + " "+ request.getParameter("crew" + i).isEmpty());
			cruise.getCrew().add(request.getParameter("crew" + i));
			i++;
		}
		System.out.println(i + " za ");
		return cruise;
	}

}
