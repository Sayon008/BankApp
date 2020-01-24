package com.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel {

	private String name;
	private String accno;
	private int balance;
	private int custid;
	private String pass;
	private int amount;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getCustid() {
		return custid;
	}

	public void setCustid(int custid) {
		this.custid = custid;

	}

	public void setPass(String pass) {
		this.pass = pass;

	}

	public LoginModel() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "sayon", "sayon");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean login() {
		try {
			ps = con.prepareStatement("select * from bankapp where cust_id=? and pass=?");
			ps.setInt(1, custid);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			while (rs.next()) {
				accno = rs.getString(2);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean checkbal() {
		try {
			ps = con.prepareStatement("select * from BANKAPP where acc_no=?");
			ps.setString(1, accno);
			rs = ps.executeQuery();

			while (rs.next()) {
				balance = rs.getInt(3);
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean changePassword() {
		try {
			ps = con.prepareStatement("update BANKAPP set pass=? where acc_no=?");
			ps.setString(1, pass);
			ps.setString(2, accno);
			int rows = ps.executeUpdate();

			if (rows == 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addAmount() {
		try {
			ps = con.prepareStatement("select balance from BANKAPP where acc_no=?");
			ps.setString(1, accno);
			rs = ps.executeQuery();
			while (rs.next()) {
				balance = rs.getInt(1);
				balance += amount;
				ps = con.prepareStatement("update BANKAPP set balance=? where acc_no=?");
				ps.setInt(1, balance);
				ps.setString(2, accno);
				int row = ps.executeUpdate();
				if (row > 0) {
					return true;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean removeAmount() {
		try {
			ps = con.prepareStatement("select balance from BANKAPP where acc_no=?");
			ps.setString(1, accno);
			rs = ps.executeQuery();
			while (rs.next()) {
				balance = rs.getInt(1);
				if (balance > amount) {
					balance -= amount;
					ps = con.prepareStatement("update BANKAPP set balance=? where acc_no=?");
					ps.setInt(1, balance);
					ps.setString(2, accno);
					int row = ps.executeUpdate();
					if (row > 0) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean transferAmount()
	{
		try {
			ps = con.prepareStatement("update bankapp set balance=balance-? where acc_no=?");
			ps.setInt(1, amount);
			ps.setString(2, accno);
			int rows= ps.executeUpdate();

			while(rows>0)
			{
				return true;
			}
		
	}catch(Exception e)
		{
		e.printStackTrace();
		}
		return false;
		}
}