package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.LoginModel;

/**
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		//Getting he value of accno from the Previously created Session in Login servelet
		String accno=(String) session.getAttribute("accno");
		String newpass=request.getParameter("newpass");
		
		LoginModel m=new LoginModel();
		m.setAccno(accno);
		m.setPass(newpass);
		
		boolean status=m.changePassword();
		if(status)
		{
			response.sendRedirect("passordChange.html");
		}
		else
		{
			response.sendRedirect("failPass.html");
		}
		
	}

}
