<!--LUCIA ROSALES SANTIAGO: 5% -->
<!-- MANUEL GALÁN ALFARO: 70% -->
<!--OUAIL BOUAZZA MANSOURI : 25%-->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ReviewFilms</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/indexEstilo.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
</head>
<%
    List<Movie> movieList = (List<Movie>) request.getAttribute("movieList");
    List<Movie> bestRatingMovieList = (List<Movie>) request.getAttribute("bestRatingMovieList");
    List<Movie> moreCommentedMovieList = (List<Movie>) request.getAttribute("moreCommentedMovieList");
    List<Movie> blockbusters = (List<Movie>) request.getAttribute("blockbusters");
    List<Movie> releasesTenYearsMovieList = (List<Movie>) request.getAttribute("releasesTenYearsMovieList");
    List<Movie> superheroeMovieList = (List<Movie>) request.getAttribute("superheroeMovieList");
    List<Movie> dramaMovieList = (List<Movie>) request.getAttribute("dramaMovieList");
    List<Movie> comedyMovieList = (List<Movie>) request.getAttribute("comedyMovieList");
    List<Movie> warMovieList = (List<Movie>) request.getAttribute("warMovieList");
    List<Movie> basedTrueStoryMovieList = (List<Movie>) request.getAttribute("basedTrueStoryMovieList");

    boolean isAdmin = request.isUserInRole("ROLE_ADMIN");

    String tituloCarrusel = (String) request.getAttribute("tituloCarrusel");

%>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>
    <div style="display: flex; justify-content: flex-end; padding: 10px;">
        <a href="${pageContext.request.contextPath}/estadisticas" class="button-generico"  style="text-decoration: none;"
        >Estadísticas</a>

        <% if (isAdmin) { %>
        <a href="${pageContext.request.contextPath}/admin/menu" title="Administración" style="font-size: 24px; text-decoration: none;">
            ⚙️
        </a>
        <% } %>
    </div>

    <div class="page-content">
        <!-- Formulario combinado de búsqueda y filtrado -->
        <form:form method="post" action="/buscar-filtrar" modelAttribute="busquedaFiltro" class="search-form">
            <div class="search-input-wrapper">
                <div class="search-field">
                    <form:input path="texto" type="text" id="searchInput" name="busqueda" class="search-input" placeholder="Buscar película..."/>
                    <span class="search-icon">🔍</span>
                </div>
                <form:button class="button-generico">Buscar</form:button>
            </div>

            <!-- Formulario de filtrado -->
            <div class="filters-wrapper">
                <span class="filter-label">Filtrar por:</span>

                <div class="range-group">
                    <label for="yearInput">Año:</label>
                    <form:input path="year" id="yearInput" type="number" min="1895" max="2025" cssClass="year-input"/>
                </div>

                <div class="range-group">
                    <label for="ratingInput">Calificación mínima:</label>
                    <form:input path="vote" id="ratingInput" type="number" step="0.1" min="0" max="10" cssClass="rating-input"/>
                </div>

                <div class="range-group">
                    <label for="genreSelect">Género:</label>
                    <form:select path="generoIds" id="genreSelect" cssClass="genre-select">
                        <form:option value="" label="-- Todos los géneros --"/>
                        <form:options items="${genreList}" itemValue="id" itemLabel="name"/>
                    </form:select>
                </div>

            </div>
        </form:form>

        <!-- Carrusel de todas las películas -->
        <h3><%=tituloCarrusel%></h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'all-movies-carousel')">&#10094;</div>
            <div class="carousel" id="all-movies-carousel">
                <%
                    for (Movie movie : movieList) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'all-movies-carousel')">&#10095;</div>
        </div>

        <!-- Películas mejor valoradas -->
        <h3>Películas aclamadas por la crítica</h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'bestrating-movies-carousel')">&#10094;</div>
            <div class="carousel" id="bestrating-movies-carousel">
                <%
                    for (Movie movie : bestRatingMovieList) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'bestrating-movies-carousel')">&#10095;</div>
        </div>

        <!-- Películas más comentadas -->
        <h3>Películas más comentadas</h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'morecommented-movies-carousel')">&#10094;</div>
            <div class="carousel" id="morecommented-movies-carousel">
                <%
                    for (Movie movie : moreCommentedMovieList) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'morecommented-movies-carousel')">&#10095;</div>
        </div>

        <!-- Películas más taquilleras -->
        <h3>Películas más taquilleras</h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'blockbuster-carousel')">&#10094;</div>
            <div class="carousel" id="blockbuster-carousel">
                <%
                    for (Movie movie : blockbusters) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'blockbuster-carousel')">&#10095;</div>
        </div>

        <!-- Películas estrenadas en los últimos 10 años -->
        <h3>Estrenos en los últimos 10 años</h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'releasesTenYears-carousel')">&#10094;</div>
            <div class="carousel" id="releasesTenYears-carousel">
                <%
                    for (Movie movie : releasesTenYearsMovieList) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'releasesTenYears-carousel')">&#10095;</div>
        </div>

        <!-- Carrusel de superhéroes -->
        <h3>Películas de superhéroes</h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'superheroes-movies-carousel')">&#10094;</div>
            <div class="carousel" id="superheroes-movies-carousel">
                <%
                    for (Movie movie : superheroeMovieList) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'superheroes-movies-carousel')">&#10095;</div>
        </div>

        <!-- Carrusel de dramas -->
        <h3>Películas dramáticas</h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'drama-movies-carousel')">&#10094;</div>
            <div class="carousel" id="drama-movies-carousel">
                <%
                    for (Movie movie : dramaMovieList) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'drama-movies-carousel')">&#10095;</div>
        </div>

        <!-- Carrusel de comedias -->
        <h3>Películas de comedia</h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'comedy-movies-carousel')">&#10094;</div>
            <div class="carousel" id="comedy-movies-carousel">
                <%
                    for (Movie movie : comedyMovieList) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'comedy-movies-carousel')">&#10095;</div>
        </div>

        <!-- Carrusel de peliculas de guerra -->
        <h3>Películas bélicas</h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'war-movies-carousel')">&#10094;</div>
            <div class="carousel" id="war-movies-carousel">
                <%
                    for (Movie movie : warMovieList) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'war-movies-carousel')">&#10095;</div>
        </div>

        <!-- Carrusel de peliculas basadas en hechos reales -->
        <h3>Películas basadas en hechos reales</h3>
        <div class="carousel-container">
            <div class="nav-arrow" onclick="scrollCarousel(-1, 'based-true-history-movies-carousel')">&#10094;</div>
            <div class="carousel" id="based-true-history-movies-carousel">
                <%
                    for (Movie movie : basedTrueStoryMovieList) {
                %>
                <a href="/viewmovie?id=<%=movie.getId()%>" class="movie-card">
                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="movie-poster">
                    <div class="movie-title"><%= movie.getOriginalTitle() %></div>
                </a>
                <%
                    }
                %>
            </div>
            <div class="nav-arrow" onclick="scrollCarousel(1, 'based-true-history-movies-carousel')">&#10095;</div>
        </div>


        <% if (isAdmin) { %>
        <a href="${pageContext.request.contextPath}/editmovie" class="button-generico"> + Añadir nueva película</a>
        <% } %>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
<script src="/js/indexScript.js"></script>
</body>
</html>
