package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.LoginModel;

public class Balance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Balance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creating session
		HttpSession session=request.getSession();
		
		//Getting he value of accno from the Previously created Session in Login servelet
		String accno=(String) session.getAttribute("accno");
		
		LoginModel m=new LoginModel();
		m.setAccno(accno);
		
		boolean status=m.checkbal();
		int balance=m.getBalance();
		
			if(status)
			{
				session.setAttribute("balance",balance);
				response.sendRedirect("checkBalanceSuccess.jsp");
			}
			else
			{
				response.sendRedirect("checkBalanceFailure.jsp");
			}
	}

}
