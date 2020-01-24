package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.LoginModel;

/**
 * Servlet implementation class DeductAmount
 */
public class DeductAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeductAmount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int amount=Integer.parseInt(request.getParameter("subamount"));
		
		String accno=(String) session.getAttribute("accno");
		
		LoginModel m=new LoginModel();
		m.setAccno(accno);
		m.setAmount(amount);
		
		boolean status=m.removeAmount();
		if(status)
		{
			response.sendRedirect("balanceRemove.html");
		}
		else
		{
			response.sendRedirect("balanceNotRemove.html");
		}
	}

}
