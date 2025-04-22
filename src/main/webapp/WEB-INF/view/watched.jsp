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
    List<Movie> movieList = (List<Movie>) request.getAttribute("movieList");
    List<Seen>seenMovies=(List<Seen>) request.getAttribute("seenMovies");
    List<Genre>genreList=(List<Genre>) request.getAttribute("genreList");
%>


<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>


    <!-- Formulario de filtrado -->
    <form method="post" action="/filtrarSeen" class="filter-form">
        <div class="filters-wrapper">
            <span class="filter-label">Filtrar por:</span>


            <div class="range-group">
                <label for="yearInput">Año:</label>
                <input type="number" id="yearInput" name="year" min="1895" max="2025" value=" " class="year-input">
            </div>


            <div class="range-group">
                <label for="ratingSelect">Calificación:</label>
                <select id="ratingSelect" name="rating" class="rating-select">
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
            </div>

            <div class="range-group">
                <label for="genreSelect">Genre:</label>
                <select id="genreSelect" name="genre">
                    <c:forEach var="genre" items="${genreList}">
                        <option value="${genre.name}">${genre.name}</option>
                    </c:forEach>
                </select>
            </div>

            <input type="submit" class="filter-button" value="Filtrar">
        </div>
    </form>

    <!-- Tabla de películas vistas -->
    <div class="table-wrapper">
        <form>
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
                        <a href="/viewmovie?id=<%=movie.getId()%>">
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
        </form>
    </div>
        <jsp:include page="footer.jsp"/>

</body>
</html>