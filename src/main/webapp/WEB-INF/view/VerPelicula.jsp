<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Set" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Movie movie = (Movie) request.getAttribute("movie");
    Set<Review> reviews = movie.getReviews();
    Set<Movie> relatedMoviesGenre = (Set<Movie>) request.getAttribute("relatedMoviesGenre");
    Set<Movie> relatedMoviesProductionCompany = (Set<Movie>) request.getAttribute("relatedMoviesProductionCompany");
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= movie.getTitle() %></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/VerPeliculaEstilo.css">
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

            <%
                String error = (String) session.getAttribute("error");
                if (error != null) {
            %>
            <p style="color:red"><%= error %></p>
            <%
                    session.removeAttribute("error");
                }
            %>

            <h1><%= movie.getTitle() %>
                <span class="rating-box"><%= movie.getVoteAverage() != null ? movie.getVoteAverage() + "/10" : "Sin calificacion" %></span>
            </h1>

            <div class="tabs">
                <button>Cast</button>
                <button>Trailer</button>
                <button>Crew</button>
            </div>

            <div class="movie-info">
                <p><%= movie.getOverview() == null? "No existe resumen de esta película": movie.getOverview()%></p>
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

            <!--CREAR REVIEW-------------------------------------------------->
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

            <!--EDITAR MOVIE--------------------------------------------------------->
            <%
                boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
            %>

            <% if (isAdmin) { %>
            <div class="actions">
                <a href="<%= request.getContextPath() %>/editmovie?id=<%=movie.getId()%>">Modificar Película</a>
                <a href="<%= request.getContextPath() %>/deletemovie?id=<%= movie.getId() %>"
                   onclick="return confirm('¿Está seguro de que quiere borrar la película <%= movie.getTitle() %>?')">
                    Borrar
                </a>
            </div>
            <% } %>
        </div>

        <!-- MARCAR COMO VISTA UNA PELÍCULA -->
        <form:form method="post" action="/marcarComoVista" modelAttribute="vista" class="watched-button-form">
            <form:input path="idMovie" type="hidden" value="${id}" />
            <form:button>Seen</form:button>
        </form:form>
        <!------------------------------------------>

    </div>

    <jsp:include page="footer.jsp"/>

</div>
<script src="/js/VerPeliculaScript.js"></script>
</body>
</html>
