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

public class MovieList extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		PrintWriter out=resp.getWriter();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet result=null;
		String sql="select * from movie";
		try {
			con=DBUtil.getDbConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ps=con.prepareStatement(sql);
			result=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Movie> m=new ArrayList<Movie>();
		try {
			while(result.next()) {
//				RequestDispatcher ds=req.getRequestDispatcher("movielist.jsp");
//				ds.forward(res, response);
				
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
		RequestDispatcher ds=req.getRequestDispatcher("MovieList.jsp");
		ds.forward(req, resp);

	}

}
