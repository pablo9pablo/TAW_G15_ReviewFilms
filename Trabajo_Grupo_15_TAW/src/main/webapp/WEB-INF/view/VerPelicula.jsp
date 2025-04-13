<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<%
    MovieEntity movie = (MovieEntity) request.getAttribute("movie");
    boolean isAdmin = false; //para ver si tengo permisos para modificar/eliminar la pelicula
%>

<head>
    <title><%= movie.getTitle() %> - Detalles</title>
</head>

<body>
<h1><%= movie.getTitle() %></h1>
<img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" width="300"/>
<p><strong>Original Title:</strong> <%= movie.getOriginalTitle() %></p>
<p><strong>Overview:</strong> <%= movie.getOverview() %></p>
<p><strong>Release Date:</strong> <%= movie.getReleaseDate() %></p>
<p><strong>Runtime:</strong> <%= movie.getRuntime() %> mins</p>
<p><strong>Language:</strong> <%= movie.getOriginalLanguage() %></p>
<p><strong>Popularity:</strong> <%= movie.getPopularity() %></p>
<p><strong>Vote Average:</strong> <%= movie.getVoteAverage() %></p>
<br/>

<%
    // Si el usuario es administrador se muestran los botones para modificar y eliminar
    if (isAdmin) {
%>
<!--El editar esta sin terminar y el borrar no se si esta completo-->
<a href="/editmovie?id=<%= movie.getId() %>">Modificar Película</a>
<br/>
<a href="/deletemovie?id=<%= movie.getId() %>"  onclick="return confirm('¿Está seguro de que quiere borrar la pelicula <%=movie.getTitle() %>?')">Borrar</a>
<%
    }
%>
</body>
</html>
