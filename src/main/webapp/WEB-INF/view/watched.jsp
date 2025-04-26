<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ReviewFilms</title>
    <link rel="stylesheet" type="text/css" href="/css/watched.css">
</head>
<%
    List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");
    Integer genreId = (Integer) request.getAttribute("genreId");
    boolean esEditor = true;
    String selectedGenre = (genreId != null) ? genreId.toString() : "";

    List<Seen>seenMovies=(List<Seen>) request.getAttribute("seenMovies");
%>


<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>
    <jsp:include page="logout.jsp"/>



        <!-- Formulario de filtrado -->
        <form method="post" action="/filtrarSeen" class="filter-form">
            <div class="filters-wrapper">
                <span class="filter-label">Filtrar por:</span>

                <div class="range-group">
                    <label for="yearInput">Año:</label>
                    <input type="number" id="yearInput" name="year" min="1895" max="2025" class="year-input" value="<%= request.getAttribute("year") != null ? request.getAttribute("year") : "" %>">
                </div>

                <div class="range-group">
                    <label for="ratingInput">Calificación mínima:</label>
                    <input type="number" id="ratingInput" name="vote" step="0.1" min="0" max="10" class="rating-input" value="<%= request.getAttribute("vote") != null ? request.getAttribute("vote") : "" %>">
                </div>

                <div class="range-group">
                    <label for="genreSelect">Género:</label>
                    <select id="genreSelect" name="genreId">
                        <!-- Opción para todos los géneros -->
                        <option value="" <%= (selectedGenre.isEmpty()) ? "selected" : "" %>>-- Todos los géneros --</option>

                        <%
                            for (Genre g : genreList) {
                                String seleccionado = (selectedGenre.equals(g.getId().toString())) ? "selected" : "";
                        %>
                        <option value="<%= g.getId() %>" <%= seleccionado %>><%= g.getName() %></option>
                        <%
                            }
                        %>
                    </select>
                </div>


                <input type="submit" class="filter-button" value="Filtrar">
            </div>

        </form>

        <!-- Tabla de películas vistas -->
        <div class="table-wrapper table-wrapper-scroll-left">
            <form>
                <div class="content">
                    <table class="movie-table">
                        <thead>
                        <tr>
                            <th>Portada</th>
                            <th>Título</th>
                            <th>Duración</th>
                            <th>Calificación Media</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            for (Seen movie : seenMovies) {
                        %>
                        <tr>
                            <td>
                                <a href="/viewmovieSeen?id=<%=movie.getMovie().getId()%>">
                                    <img src="<%= movie.getMovie().getImageUrl() %>" alt="<%= movie.getMovie().getOriginalTitle() %>" class="thumbnail">
                                </a>
                            </td>
                            <td><%= movie.getMovie().getOriginalTitle() %></td>
                            <td><%= movie.getMovie().getRuntime()%> min</td>
                            <td><%= movie.getMovie().getVoteAverage() %></td>
                        </tr>
                        <%
                            }
                        %>
                        </tbody>
                    </table>
                </div>
            </form>
       </div>

        <jsp:include page="footer.jsp"/>
</body>
</html>