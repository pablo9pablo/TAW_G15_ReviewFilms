<!--LUCIA ROSALES SANTIAGO: 40% -->
<!--MIGUEL LABELLA RAMÍREZ: 30% -->
<!-- MANUEL GALÁN ALFARO: 30% -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.Set" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="java.util.Optional" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Movie movie = (Movie) request.getAttribute("movie");
    Set<Review> reviews = movie.getReviews();
    Optional<Integer> hasReview = (Optional<Integer>) request.getAttribute("hasReview");

    Set<Movie> relatedMoviesGenre = (Set<Movie>) request.getAttribute("relatedMoviesGenre");
    Set<Movie> relatedMoviesProductionCompany = (Set<Movie>) request.getAttribute("relatedMoviesProductionCompany");
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= movie.getTitle() %></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/verPelicula.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
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
                <span class="rating-box"><%= movie.getVoteCount() == null || movie.getVoteCount() == 0 ? "Sin calificacion" : movie.getVoteAverage() + "/10" %></span>
            </h1>

            <div class="tabs">
                <a href="${pageContext.request.contextPath}/viewmovie?id=${movie.id}">
                    <button class="active-tab">Main</button>
                </a>

                <a href="${pageContext.request.contextPath}/movies/cast?id=${movie.id}">
                    <button>Cast</button>
                </a>
                <a href="${pageContext.request.contextPath}/movies/crew?id=${movie.id}">
                    <button>Crew</button>
                </a>
                <a href="${pageContext.request.contextPath}/productionCompanies/movieProductionCompanies?id=${movie.id}">
                    <button>Productoras</button>
                </a>
                <a href="${pageContext.request.contextPath}/movieGenres?id=${movie.id}">
                    <button>Géneros</button>
                </a>
            </div>



            <div class="movie-info">
                <p><%= movie.getOverview() == null? "No existe resumen de esta película": movie.getOverview()%></p>
            </div>


            <!-- Sección de recomendaciones por género -->
            <% if (!relatedMoviesGenre.isEmpty()) { %>
            <div class="movie-info">
                <h3>Películas similares por género</h3>
                <div class="carousel-container">
                    <div class="nav-arrow" onclick="scrollCarousel(-1, 'genre-carousel')">&#10094;</div>
                    <div class="carousel" id="genre-carousel">
                        <%
                            for (Movie relatedMovie : relatedMoviesGenre) {
                        %>
                        <a href="/viewmovie?id=<%= relatedMovie.getId() %>" class="movie-card">
                            <img src="<%= relatedMovie.getImageUrl() %>" alt="<%= relatedMovie.getOriginalTitle() %>">
                            <p><%= relatedMovie.getOriginalTitle() %>
                            </p>
                        </a>
                        <%
                            }
                        %>
                    </div>
                    <div class="nav-arrow" onclick="scrollCarousel(1, 'genre-carousel')">&#10095;</div>
                </div>
            </div>
            <% } %>

            <!-- Sección de recomendaciones por productora -->
            <% if (!relatedMoviesProductionCompany.isEmpty()) { %>
            <div class="movie-info">
                <h3>Películas de la misma productora</h3>
                <div class="carousel-container">
                    <div class="nav-arrow" onclick="scrollCarousel(-1, 'prod-carousel')">&#10094;</div>
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
                    <div class="nav-arrow" onclick="scrollCarousel(1, 'prod-carousel')">&#10095;</div>
                </div>
            </div>
            <% } %>

            <!--CREAR REVIEW-------------------------------------------------->
            <div class="reviews">
                <h3>Crear una nueva review:</h3>
                <%
                    if(hasReview.isPresent()){
                %>
                        <p class="error-message">Ya has creado una review para esta película.</p>
                        <a href="/editReview?id=<%=hasReview.get()%>&origen=pelicula" class="button-generico">Editar Review</a>
                <%
                    }else{
                %>
                        <form:form modelAttribute="review" method="post" action="/savereview">
                            <form:hidden path="id"/>
                            <form:hidden path="movieDTO.id"/>
                            <input type="hidden" name="origen" value="pelicula"/>

                            <p><strong>Descripción:</strong></p>
                            <form:textarea path="description" rows="3" cols="80" cssClass="review-textarea"
                                           placeholder="Write a review"/>

                            <div class="rating-section">
                                <p><strong>Rating:</strong></p>
                                <form:input path="rating" type="number" min="0" max="10" required="true"
                                            cssClass="rating-input"/>
                                <span class="stars">
                                    <i class="bi bi-star-fill"></i> (0-10)
                                </span><br/>
                            </div>
                            <form:button class="button-generico">Añadir review</form:button>
                        </form:form>
                <%
                    }
                %>
                <h3>Reviews (<%=movie.getVoteCount() == null ? "0" : movie.getVoteCount()%>)</h3>
                <%
                    for (Review review : reviews) {
                    int score = review.getRating();
                    String scoreClass = score >= 8 ? "score-green" : score >= 5 ? "score-yellow" : "score-red";
                %>

                    <div class="review">
                        <span class="review-score <%= scoreClass %>"><%= score %>/10</span>
                        <p><strong><%= review.getUser().getEmail() %>:</strong></p>
                        <p><%= review.getDescription() == null ? "" : review.getDescription() %></p>
                    </div>
                <% } %>
            </div>

            <!--EDITAR MOVIE--------------------------------------------------------->
            <%
                boolean isAdmin = request.isUserInRole("ROLE_ADMIN");

                if (isAdmin) {
            %>
            <div class="button-container">
                <a href="<%= request.getContextPath() %>/editmovie?id=<%=movie.getId()%>" class="button-generico">Modificar Película</a>
                <a href="<%= request.getContextPath() %>/deletemovie?id=<%= movie.getId() %>"
                   onclick="return confirm('¿Está seguro de que quiere borrar la película <%= movie.getTitle() %>?')" class="button-generico">
                    Borrar
                </a>
            </div>
            <%
            }
            %>
        </div>

        <%
            String desdeWatched = request.getParameter("desdeWatched");
            String desdeWaitingToSee = request.getParameter("desdeWaitingToSee");
            String desdeFavoritas = request.getParameter("desdeFavoritas");

            boolean ocultarBotones = "true".equals(desdeWatched) || "true".equals(desdeWaitingToSee) || "true".equals(desdeFavoritas);

            if (!ocultarBotones) {
        %>
        <!-- MARCAR  UNA PELICULA COMO VISTA -->
        <form method="post" action="/marcarComoVista" class="watched-button-form">
            <input type="hidden" name="idMovie" value="<%= movie.getId() %>">
            <button type="submit" class="icon-button green-icon" title="Marcar como vista">
                <i class="fas fa-eye"></i>
            </button>
        </form>

        <!-- MARCAR UNA PELICULA COMO PENDIENTE -->
        <form method="post" action="/pendiente" class="watched-button-form">
            <input type="hidden" name="idMovie" value="<%= movie.getId() %>">
            <button type="submit" class="icon-button brown-icon" title="Marcar como pendiente">
                <i class="fas fa-hourglass-half"></i>
            </button>
        </form>

        <!-- MARCAR UNA PELICULA COMO FAVORITA -->
        <form method="post" action="/favorita" class="watched-button-form">
            <input type="hidden" name="idMovie" value="<%= movie.getId() %>">
            <button type="submit" class="icon-button" title="Marcar como favorita">
                <i class="fas fa-star yellow-icon"></i>
            </button>
        </form>
        <%
            }
        %>

    </div>

    <jsp:include page="footer.jsp"/>

</div>
<script src="/js/VerPeliculaScript.js"></script>
</body>
</html>
