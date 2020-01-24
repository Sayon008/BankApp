package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.LoginModel;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int custid=Integer.parseInt(request.getParameter("custid"));
		String pass=request.getParameter("pass");
		//String pass=request.getParameter("pass");
		
		LoginModel m=new LoginModel();
		m.setCustid(custid);
		m.setPass(pass);
		
		boolean status=m.login();
		String accno=m.getAccno();
		if(status)
		{
			HttpSession session=request.getSession(true);
			session.setAttribute("accno", accno);
			response.sendRedirect("home.html");
		}
		else {
			response.sendRedirect("loginFail.html");
		}
	}

}
