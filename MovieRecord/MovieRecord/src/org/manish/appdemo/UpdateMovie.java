package org.manish.appdemo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateMovie extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		int screenId=Integer.parseInt( req.getParameter("screenid"));
		String movieName=req.getParameter("moviename");
		int nfticket=Integer.parseInt(req.getParameter("nfticket"));
		
		Connection con=null;
	
		int result=0;
		try {
			con=DBUtil.getDbConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql="update  movie set screenid=?,moviename=?,nfticket=?  where screenid= ?";
		try {
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, screenId);
			ps.setString(2, movieName);
			ps.setInt(3, nfticket);
			ps.setInt(4, screenId);
			result=ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result==1) {
			out.print("Movie details inserted successfully");
			RequestDispatcher rd=req.getRequestDispatcher("success.jsp");
			rd.forward(req, resp);
			
		} else {
			out.print("Oops! somethong went wrong please try again");
			RequestDispatcher rd=req.getRequestDispatcher("InsertMovie.jsp");
			rd.include(req, resp);
		}
		
	}


}
