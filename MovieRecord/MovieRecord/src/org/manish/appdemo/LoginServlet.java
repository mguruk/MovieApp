package org.manish.appdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends  HttpServlet {
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html");
      String userName = req.getParameter("username");
      String password = req.getParameter("password");
      PrintWriter out=resp.getWriter();
      //System.out.println(userName+password);
      String sql="select * from user_detail where username= ? and password = ?";
      Connection con=null;
      ResultSet result=null;
		try {
			con = DBUtil.getDbConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
   
    	try {
    	PreparedStatement ps= con.prepareStatement(sql);
    	ps.setString(1, userName);
		ps.setString(2, password);
		result=ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
      if((userName!=null && userName.length()!=0)&& (password!=null && password.length()!=0)) {
      try {
		if(result.next()) {
			HttpSession session= req.getSession();
			session.setAttribute("user", userName);
		    RequestDispatcher dispatch = req.getRequestDispatcher("home.jsp");
			dispatch.forward(req, resp);
		  }else {
			  out.print("try again credential not matched");
			  RequestDispatcher dispatch = req.getRequestDispatcher("login.jsp");
			  dispatch.include(req, resp);
		  }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
    } else {
    	out.println("please enter the username and password");
    	RequestDispatcher dispatch = req.getRequestDispatcher("login.jsp");
		dispatch.include(req, resp);
    	
    }

}
}
