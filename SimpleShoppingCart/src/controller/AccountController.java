package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import database.Items;
import database.UserAccount;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/AccountController")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			InitialContext initContext = new InitialContext();
			
			Context env = (Context) initContext.lookup("java:comp/env");
			
			ds = (DataSource) env.lookup("jdbc/webshop");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
		
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		String ids = "";
		String email = "";
		
		Cookie[] temp = request.getCookies();
		
		for(Cookie one : temp) {
			if(one.getName().equals("user")) {
				email = one.getValue();
			}
		}
		
		if(action == null) {
			System.out.println("doGet myaccount or null");
			request.getRequestDispatcher("/myaccount.jsp").forward(request, response);
		}		
		else {
			System.out.println("doGet else");
			doPost(request, response);
		}
		
		System.out.println("doGet exit");
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String ids = "";
		String email = "";
		String allitems = "";
		
		Cookie[] temp = request.getCookies();
		
		for(Cookie one : temp) {
			if(one.getName().equals("user")) {
				email = one.getValue();
			}
		}
		
		System.out.println("doPost entry");
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
		
		Items items = new Items(conn);
		UserAccount account = new UserAccount(conn);
		List<String> images = null;
		
		if(action.equals("myaccount") || action == null) {
			System.out.println("doPost myaccount or null");
			request.getRequestDispatcher("/myaccount.jsp").forward(request, response);
		}		
		else if(action.equals("add")) {
			String useritems = "";
			System.out.println("doPost add");
			try {
				items.initLoad();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(email != null && email != "") {
				useritems = account.getItems(email);
			}
			
			images = items.getImages();
			request.setAttribute("images", images);
			if(useritems != "") {
				request.setAttribute("useritems", useritems);
			}
			request.getRequestDispatcher("/additem.jsp").forward(request, response);
			
		}		
		else if(action.equals("additems")) {
			System.out.println("doPost additems THREAD ID: " + Thread.currentThread().getId());
			System.out.println("doPost addItems THREAD ID: " + Thread.currentThread().getId() + " param BEFORE: " + request.getParameter("listofitems"));
			System.out.println("doPost addItems THREAD ID: " + Thread.currentThread().getId() + " param TYPE: " + request.getParameter("listofitems").getClass());
			String listofitems = request.getParameter("listofitems");
			String allnames = "";
			String alldescs = "";
			
			if(listofitems != "") {
				request.setAttribute("listofitems", listofitems);
				
				
				allitems = account.addItems(email, listofitems);
				allnames = account.getNames(allitems);
				alldescs = account.getDescriptions(allitems);
				request.setAttribute("allitems", allitems);
				request.setAttribute("allnames", allnames);
				request.setAttribute("alldescs", alldescs);
			}
			
			request.getRequestDispatcher("/myaccount.jsp").forward(request, response);
				
		}
		else if(action.equals("removeitems")) {	
			String removelist = request.getParameter("removelist");
			System.out.println("updatedlist: " + removelist);
			account.removeItems(email, removelist);
			response.sendRedirect(request.getContextPath() + "/myaccount.jsp");
		}
		
		System.out.println("doPost exit");
		
	}

}
