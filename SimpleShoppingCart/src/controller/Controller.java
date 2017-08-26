package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

import beans.User;
import database.Account;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DataSource ds;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
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
			
			Context env = (Context)initContext.lookup("java:comp/env");
			
			ds = (DataSource)env.lookup("jdbc/webshop");
			
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
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getParameter("action");
		
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		if(cookies != null) {
			for(Cookie oneCookie : cookies) {
				if(oneCookie.getName().equals("user")) {
					userCookie = oneCookie;
				}
			}
		}
		
		if(action == null && userCookie == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else if(userCookie != null) {
			if(action.equals("logout")) {
				request.setAttribute("email", "");
				request.setAttribute("password", "");
				request.setAttribute("validationmessage", "");
				request.setAttribute("emailmessage", "");
				request.setAttribute("pwdmessage", "");
				request.getRequestDispatcher("/loggedout.jsp").include(request, response);
				Cookie cookie = new Cookie("user", "garbage");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			else if(action.equals("add")) {
				request.getRequestDispatcher("/additem.jsp").include(request, response);
			}
			else {
				request.getRequestDispatcher("/myaccount.jsp").forward(request, response);
			}
		}
		else {
			if(action.equals("login")) {
				request.setAttribute("email", "");
				request.setAttribute("password", "");
				request.setAttribute("validationmessage", "");
				request.setAttribute("emailmessage", "");
				request.setAttribute("pwdmessage", "");
				
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else if(action.equals("createaccount")) {
				request.setAttribute("email", "");
				request.setAttribute("password", "");
				request.setAttribute("repeatpassword", "");
				request.setAttribute("validationmessage", "");
				request.setAttribute("emailmessage", "");
				request.setAttribute("pwdmessage", "");
				
				request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
			}
			
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		Cookie[] cookies = request.getCookies();
		
		if(action == null) {
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		
		Connection conn = null;
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new ServletException();
		}
		
		Account account = new Account(conn);
		
		if(action.equals("dologin")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			User user = new User(email, password);
			
			request.setAttribute("email", email);
			request.setAttribute("password", "");
			
			try {
				if(account.login(email, password)) {
					Cookie cookie = new Cookie("user", email);
					response.addCookie(cookie);
					// request.getRequestDispatcher("/myaccount.jsp").include(request, response);
					response.sendRedirect(request.getContextPath() + "/myaccount.jsp");					
				}
				else {
					request.setAttribute("validationmessage", "Email address or password incorrect");
					request.setAttribute("emailmessage", "");
					request.setAttribute("pwdmessage", "");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(action.equals("createaccount")) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String repeatPassword = request.getParameter("repeatpassword");
			
			request.setAttribute("email", email);
			request.setAttribute("password", password);
			request.setAttribute("repeatpassword", "");
			request.setAttribute("validationmessage", "");
			request.setAttribute("emailmessage", "");
			request.setAttribute("pwdmessage", "");
			
			if(!password.equals(repeatPassword)) {
				request.setAttribute("pwdmessage", "Passwords do not match");
				request.setAttribute("validationmessage", "");
				request.setAttribute("emailmessage", "");
				request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
			}
			else {
				User user = new User(email, password);
				
				if(user.validateEmail() && user.validatePassword()) {
					try {
						if(account.exists(email)) {
							request.setAttribute("emailmessage", "An account already exists with that email");
							request.setAttribute("validationmessage", "");
							request.setAttribute("pwdmessage", "");
							request.getRequestDispatcher("/createaccount.jsp").forward(request, response);	
						}
						else {
							account.create(email, password);
							request.getRequestDispatcher("/createsuccess.jsp").forward(request, response);
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						request.getRequestDispatcher("/error.jsp").forward(request, response);
					}	
				}
				else if(!user.validateEmail()) {
					request.setAttribute("emailmessage", user.getEmailMessage());
					request.setAttribute("validationmessage", "");
					request.setAttribute("pwdmessage", "");
					request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
				}
				else {
					request.setAttribute("pwdmessage", user.getPwdMessage());
					request.setAttribute("validationmessage", "");
					request.setAttribute("emailmessage", "");
					request.getRequestDispatcher("/createaccount.jsp").forward(request, response);
				}
			}
		}
		
	}

}
