<%@ page import="java.util.Set" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Movie movie = (Movie) request.getAttribute("movie");
    Set<Review> reviews = movie.getReviews();

    boolean isAdmin = true; //permisos
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= movie.getTitle() %></title>
    <link rel="stylesheet" type="text/css" href="/css/VerPeliculaEstilo.css">
    <script src="/js/VerPeliculaScript.js"></script>

</head>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>
    <div class="movie-container">
        <div class="left-panel">
            <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" width="180"/>
            <div class="movie-meta">
                <p><strong>Director:</strong> [director aquí]</p> <!--FALTA-->
                <p><strong>Main actor:</strong> [actor aquí]</p> <!--FALTA-->
                <p><strong>Release:</strong> <%= movie.getReleaseDate() %></p>
                <p><strong>Runtime:</strong> <%= movie.getRuntime() %> mins</p>
                <p><strong>Budget:</strong> <%=movie.getBudget()%>€</p>
            </div>
        </div>
        <div class="right-panel">
            <h1>
                <%= movie.getTitle() %>
                <span class="rating-box"><%= movie.getVoteAverage()%>/10</span>
            </h1>

            <div class="tabs">
                <button>Overview</button>
                <button>Cast</button>
                <button>Trailer</button>
                <button>Crew</button>
                <button>Details</button>
            </div>

            <div class="movie-info">
                <p><%= movie.getOverview() %></p>
            </div>


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
                    <input type="submit" value="Submit"/>
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
                <a href="/editmovie?id=<%= movie.getId() %>">Modificar Película</a>
                <a href="/deletemovie?id=<%= movie.getId() %>" onclick="return confirm('¿Está seguro de que quiere borrar la película <%= movie.getTitle() %>?')">Borrar</a>
            </div>
            <% } %>

        </div>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>
