package com.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.LoginModel;

/**
 * Servlet implementation class TransferAmount
 */
public class TransferAmount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransferAmount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		String accno=(String) session.getAttribute("accno");
		
		int amount=Integer.parseInt(request.getParameter("amount"));
		
		 LoginModel m=new LoginModel();
		 m.setAmount(amount);
		 m.setAccno(accno);
		 
		 boolean status=m.transferAmount();
		 if(status)
		 {
			 response.sendRedirect("ammountTransfer.html");
		 }
		 else
		 {
			 response.sendRedirect("amountNotTransfer.html");
		 }
	}

}
