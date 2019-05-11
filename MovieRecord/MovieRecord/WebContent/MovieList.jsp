<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="org.manish.appdemo.Movie" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border=2>
<tr>
 <th>ScreenId</th>
 <th>MovieScreen</th>
 <th>No of Ticket</th>
 <th>Action</th>
 </tr>
<%ArrayList<Movie> mov=  (ArrayList<Movie>)request.getAttribute("movie"); 

 for(Movie m : mov) {%>
 
 
 <tr>
<td><%= m.getScreenId() %></td>
<td><%= m.getMovieName() %></td>
<td><%= m.getnFTicket() %></td>
<td><a href="http://localhost:8080/MovieRecord/DeleteMovie?screenId=<%=  m.getScreenId()%>">Delete</a></td>
<td><a href="http://localhost:8080/MovieRecord/InsertMovie?screenId=<%=  m.getScreenId()%>">update</a></td>
</tr>
<%} %>
</table>
</body>
</html>