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



public class InsertMovie extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();

		String scr=req.getParameter("screenid");
		System.out.println(scr);
		
		Connection con=null;
	
		int result=0;
		try {
			con=DBUtil.getDbConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql="insert into movie values(?,?,?)";
		int screenId=Integer.parseInt( req.getParameter("screenid"));
		String movieName=req.getParameter("moviename");
		int nfticket=Integer.parseInt(req.getParameter("nfticket"));
		try {
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, screenId);
			ps.setString(2, movieName);
			ps.setInt(3, nfticket);
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
		System.out.println("end");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int screenId=Integer.parseInt( req.getParameter("screenId"));
		Connection con=null;
		ResultSet result=null;
		try {
			con =DBUtil.getDbConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="select *  from movie where screenid= ?";
		ArrayList<Movie> m=new ArrayList<Movie>();
		try {
			java.sql.PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, screenId);
			result=ps.executeQuery();
			if(result.next()) {
			
						Movie i= new Movie();
						i.setScreenId(result.getInt(1));
						i.setMovieName(result.getString(2));
						i.setnFTicket(result.getInt(3));
						m.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				req.setAttribute("movie", m);
				RequestDispatcher ds=req.getRequestDispatcher("UpdateMovie.jsp");
				ds.include(req, resp);

		
	}

}
