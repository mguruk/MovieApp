<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
 <%@ page import="org.manish.appdemo.Movie" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%ArrayList<Movie> mov=  (ArrayList<Movie>)request.getAttribute("movie");
 for(Movie m : mov) {%>
<h1>Movie Insertion</h1>
	<div style="border:2px solid black;width:276px;margin:auto;padding: 41px 34px 26px 33px;">
	<form action="UpdateMovie" method="post">
		screenId : <input type="text" name="screenid"    value="<%=m.getScreenId()%>"><br/><br/>
		MovieName : <input type="text" name="moviename" value="<%=m.getMovieName() %>"  ><br/><br/>
		No of ticket:<input type="text" name="nfticket" value="<%=m.getnFTicket()%>"><br/><br/>
		<input type="submit">
	</form>
	</div>
	<%} %>
	
</body>
</html>