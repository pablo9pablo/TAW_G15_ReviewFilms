<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ReviewFilms</title>
    <link rel="stylesheet" type="text/css" href="/css/indexEstilo.css">
</head>
<%
    List<Movie> movieList = (List<Movie>) request.getAttribute("movieList");
    List<Genre> genreList = (List<Genre>) request.getAttribute("genreList");
    boolean esEditor = true;
%>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>

    <!-- Formulario de búsqueda -->
    <form method="post" action="/buscar" class="search-form">
        <div class="search-input-wrapper">
            <div class="search-field">
                <input type="text" id="searchInput" name="busqueda" class="search-input" placeholder="Buscar película...">
                <span class="search-icon">🔍</span>
            </div>
            <input type="submit" class="search-button" value="Buscar">
        </div>
    </form>

    <!-- Formulario de filtrado -->
    <form method="post" action="/filtrar" class="filter-form">
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

    <!-- Carrusel de películas -->
    <div class="carousel-container">
        <div class="nav-arrow" onclick="scrollCarousel(-1)">&#10094;</div>
        <div class="carousel" id="carousel">
            <%
                for (Movie movie : movieList) {
            %>
            <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>">
                <p><%= movie.getOriginalTitle() %></p>
            </a>
            <%
                }
            %>
        </div>
        <div class="nav-arrow" onclick="scrollCarousel(1)">&#10095;</div>
    </div>

    <% if (esEditor) { %>
    <form action="/editmovie" method="post">
        <input type="submit" value="+ Añadir nueva película" class="add-movie-btn">
    </form>
    <% } %>

    <jsp:include page="footer.jsp"/>
</div>
<script src="/js/indexScript.js"></script>
</body>
</html>
