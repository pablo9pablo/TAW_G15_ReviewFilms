<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Movies</title>
    <h1> FILMS </h1>
</head>
<body>

<%
    List<Movie> listMovies = (List<Movie>) request.getAttribute("movies");

    for (Movie movie : listMovies) {
%>
<p><%= movie.getTitle() %></p>
<%
    }
%>




</body>

</html>