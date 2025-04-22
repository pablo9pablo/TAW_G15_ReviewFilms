<%@ page import="java.util.Set" %>
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
    <link rel="stylesheet" type="text/css" href="/css/VerPeliculaEstilo.css">
</head>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>
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
            <% } %>

            <div class="reviews">
                <h3>Reviews (<%=reviews.size()%>)</h3>

                <form action="/newreview" method="post">
                    <input type="hidden" name="movieId" value="<%= movie.getId() %>"/>
                    <input type="hidden" name="rating" id="rating" required>

                    <textarea name="comment" placeholder="Write a review" rows="2" cols="60"></textarea><br/>

                    <div class="star-rating">
                        <% for (int i = 1; i <= 5; i++) { %>
                            <span class="star" data-value="<%= i * 2 %>">&#9733;</span>
                        <% } %>
                    </div>
                    <br/>
                    <input type="submit" value="Añadir review"/>
                </form>

                    <% for (Review review : reviews) {
                            int score = review.getRating();
                            String scoreClass = score >= 8 ? "score-green" : score >= 5 ? "score-yellow" : "score-red";
                        %>
                        <div class="review">
                            <span class="review-score <%= scoreClass %>"><%= score %>/10</span>
                            <p><strong><%= review.getUser().getEmail() %>:</strong></p>
                            <p><%= review.getDescription() %></p>
                        </div>
                    <% } %>

            </div>

            <% if (isAdmin) { %>
            <div class="actions">
                <form action="/editmovie" method="post">
                    <input type="hidden" name="id" value="<%= movie.getId() %>">
                    <input type="submit" value="Modificar Película">
                </form>
                <a href="/deletemovie?id=<%= movie.getId() %>" onclick="return confirm('¿Está seguro de que quiere borrar la película <%= movie.getTitle() %>?')">Borrar</a>
            </div>
            <% } %>

        </div>

        <form  method="post" action="/marcarComoVista" class="watched-button-form">
            <input type="hidden" name="id" value="<%= movie.getId() %>">
            <button type="submit" class="watched-button">
                &#128065;
            </button>
        </form>


    </div>

    <jsp:include page="footer.jsp"/>

</div>
<script src="/js/VerPeliculaScript.js"></script>
</body>
</html>
