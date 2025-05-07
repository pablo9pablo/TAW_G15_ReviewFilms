<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor" %>

<%
    boolean isEditing = true;
    Movie movie = (Movie) request.getAttribute("movie");
    List <Genre> genreList = (List<Genre>) request.getAttribute("genre");
    List <ProductionCompany> pcompanyList = (List<ProductionCompany>) request.getAttribute("pcompany");
    List <Actor> actoresList = (List<Actor>) request.getAttribute("actores");
    if(movie.getId() == null) isEditing = false;
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= isEditing ? "Editar Película" : "Crear Película" %></title>
</head>
<body>
<jsp:include page="cabecera.jsp"/>
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

    Género:<br>
    <select name="generos" multiple>
        <% for (Genre g : genreList) {
            boolean isSelected = movie.getGenres().contains(g);
        %>
        <option value="<%= g.getId() %>" <%= isSelected ? "selected" : "" %>><%= g.getName() %></option>
        <% } %>
    </select><br>

    Production company:<br>
    <select name="pcompany" multiple>
        <% for (ProductionCompany p : pcompanyList) {
            boolean isSelected = movie.getProductionCompanies().contains(p);
        %>
        <option value="<%= p.getId() %>" <%= isSelected ? "selected" : "" %>><%= p.getName() %></option>
        <% } %>
    </select><br>

    Actores:<br>
    <select name="actores" multiple>
        <% for (Actor a : actoresList) {
            boolean isSelected = movie.getMovieCasts().stream()
                    .anyMatch(mc -> mc.getActor().equals(a));

        %>
        <option value="<%= a.getId() %>" <%= isSelected ? "selected" : "" %>><%= a.getName() %></option>
        <% } %>
    </select><br>

    Sinopsis:<br>
    <textarea name="overview" rows="4" cols="50"><%= movie.getOverview() != null ? movie.getOverview() : "" %></textarea><br>

    URL de imagen: <input type="text" name="imageUrl" value="<%= movie.getImageUrl() != null ? movie.getImageUrl() : "" %>"><br><br>

    <input type="submit" value="Guardar Película">
</form>

</body>
</html>
