<%@ page import="java.util.Set" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Movie movie = (Movie) request.getAttribute("movie");
    Set<Review> reviews = movie.getReviews();
    Set<Movie> relatedMoviesGenre = (Set<Movie>) request.getAttribute("relatedMoviesGenre");
    Set<Movie> relatedMoviesProductionCompany = (Set<Movie>) request.getAttribute("relatedMoviesProductionCompany");

    boolean isAdmin = true; //permisos
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= movie.getTitle() %></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/VerPeliculaWatched.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
</head>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>
    <jsp:include page="logout.jsp"/>

    <div class="movie-container">
        <div class="left-panel">
            <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" width="180"/>
            <div class="movie-meta">
                <p><strong>Release:</strong> <%= movie.getReleaseDate() %></p>
                <p><strong>Runtime:</strong> <%= movie.getRuntime() %> mins</p>
                <p><strong>Budget:</strong> <%=movie.getBudget()%>€</p>
            </div>
        </div>
        <div class="right-panel">

            <h1><%= movie.getTitle() %>
                <span class="rating-box"><%= movie.getVoteAverage() != null ? movie.getVoteAverage() : "–" %>/10</span>
            </h1>

            <div class="tabs">
                <button>Cast</button>
                <button>Trailer</button>
                <button>Crew</button>
            </div>

            <div class="movie-info">
                <p><%= movie.getOverview() %></p>
            </div>

            <!-- Sección de recomendaciones por género -->
            <% if (!relatedMoviesGenre.isEmpty()) { %>
            <div class="movie-info">
                <h3>Películas similares por género</h3>
                <div class="carousel-container">
                    <div class="nav-arrow" onclick="scrollCarousel(-1)">&#10094;</div>
                    <div class="carousel" id="genre-carousel">
                        <%
                            for (Movie relatedMovie : relatedMoviesGenre) {
                        %>
                        <a href="/viewmovie?id=<%= relatedMovie.getId() %>" class="movie-card">
                            <img src="<%= relatedMovie.getImageUrl() %>" alt="<%= relatedMovie.getOriginalTitle() %>">
                            <p><%= relatedMovie.getOriginalTitle() %></p>
                        </a>
                        <%
                            }
                        %>
                    </div>
                    <div class="nav-arrow" onclick="scrollCarousel(1)">&#10095;</div>
                </div>
            </div>
            <% } %>

            <!-- Sección de recomendaciones por productora -->
            <% if (!relatedMoviesProductionCompany.isEmpty()) { %>
            <div class="movie-info">
                <h3>Películas de la misma productora</h3>
                <div class="carousel-container">
                    <div class="nav-arrow" onclick="scrollCarousel(-1)">&#10094;</div>
                    <div class="carousel" id="prod-carousel">
                        <%
                            for (Movie relatedMovie : relatedMoviesProductionCompany) {
                        %>
                        <a href="/viewmovie?id=<%= relatedMovie.getId() %>" class="movie-card">
                            <img src="<%= relatedMovie.getImageUrl() %>" alt="<%= relatedMovie.getOriginalTitle() %>">
                            <p><%= relatedMovie.getOriginalTitle() %></p>
                        </a>
                        <%
                            }
                        %>
                    </div>
                    <div class="nav-arrow" onclick="scrollCarousel(1)">&#10095;</div>
                </div>
            </div>

            <%

               }

            %>
        </div>
        <!--QUITAR COMO VISTA UNA PELICULA-->
        <form:form method="post" action="/quitarComoVista" modelAttribute="vista">
            <form:input path="idMovie" type="hidden" value="${id}" />
            <form:button>Not Seen</form:button>
        </form:form>
        <!----------------------------------->
    </div>
    <jsp:include page="footer.jsp"/>
</div>
<script src="/js/VerPeliculaScript.js"></script>

</body>
</html>
