package org.manish.appdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteMovie extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	  int screenId=Integer.parseInt(req.getParameter("screenId"));
	  int result=0;
	  resp.setContentType("text/html");
	  PrintWriter out=resp.getWriter();
	  Connection con=null;
	  try {
		con=DBUtil.getDbConnection();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  String sql="delete from movie where screenid= ?";
	  try {
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, screenId);
		result=ps.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  if(result==1) {
		  String mes="deleted succesfully";
	  } else {
		  String mes=" not deleted";
	  }
	  RequestDispatcher ds=req.getRequestDispatcher("MovieList");
	  ds.forward(req, resp);
	}

}
