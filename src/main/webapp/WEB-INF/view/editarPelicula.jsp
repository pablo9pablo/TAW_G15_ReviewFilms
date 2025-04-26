<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>

<%
    boolean isEditing = true;
    Movie movie = (Movie) request.getAttribute("movie");
    if(movie.getId() == null) isEditing = false;
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= isEditing ? "Editar Película" : "Crear Película" %></title>
</head>
<body>
<jsp:include page="logout.jsp"/>


<h2><%= isEditing ? "Editar " + movie.getTitle() : "Crear Película" %></h2>

<form action="/savemovie" method="post">
    <input type="hidden" name="id" value="<%= (movie.getId()==null? -1: movie.getId()) %>">

    Título: <input type="text" name="title" value="<%= movie.getTitle() != null ? movie.getTitle() : "" %>" required><br>

    Título original: <input type="text" name="originalTitle" value="<%= movie.getOriginalTitle() != null ? movie.getOriginalTitle() : "" %>" required><br>

    Fecha de estreno: <input type="date" name="releaseDate"
                             value="<%= movie.getReleaseDate() != null ? movie.getReleaseDate() : "" %>"><br>

    Duración (min): <input type="number" name="runtime" value="<%= movie.getRuntime() != null ? movie.getRuntime() : "" %>"><br>

    Presupuesto: <input type="number" step="0.01" name="budget" value="<%= movie.getBudget() != null ? movie.getBudget() : "" %>"><br>

    Ingresos: <input type="number" step="0.01" name="revenue" value="<%= movie.getRevenue() != null ? movie.getRevenue() : "" %>"><br>

    Idioma original: <input type="text" name="originalLanguage" value="<%= movie.getOriginalLanguage() != null ? movie.getOriginalLanguage() : "" %>"><br>

    Sinopsis:<br>
    <textarea name="overview" rows="4" cols="50"><%= movie.getOverview() != null ? movie.getOverview() : "" %></textarea><br>

    URL de imagen: <input type="text" name="imageUrl" value="<%= movie.getImageUrl() != null ? movie.getImageUrl() : "" %>"><br><br>

    <input type="submit" value="Guardar Película">
</form>

</body>
</html>
