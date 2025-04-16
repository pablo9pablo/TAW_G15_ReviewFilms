<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ReviewEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    MovieEntity movie = (MovieEntity) request.getAttribute("movie");
    Set<ReviewEntity> reviews = movie.getReviews();

    boolean isAdmin = false; //permisos
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= movie.getTitle() %></title>
    <style>
        body {
            height: 100%;
            margin: 0;
        }

        .page-container {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .movie-container {
            display: flex;
            gap: 20px;
            padding: 20px;
            flex: 1;
        }

        .left-panel {
            width: 200px;
            flex-shrink: 0;
        }

        .right-panel{
            flex: 1;
        }

        .movie-info, .reviews {
            margin-top: 20px;
        }

        .rating-box {
            background-color: darkgreen;
            color: white;
            font-weight: bold;
            padding: 5px 10px;
            border-radius: 5px;
            float: right;
        }

        .tabs button {
            padding: 8px 12px;
            margin: 0 5px;
            border-radius: 5px;
            border: 1px solid #aaa;
            background-color: white;
            cursor: pointer;
        }

        .review {
            border: 1px solid #ccc;
            padding: 10px;
            margin-top: 10px;
        }

        .review-score {
            font-weight: bold;
            padding: 3px 8px;
            border-radius: 4px;
            float: right;
        }

        .score-green {
            background-color: #28a745;
            color: white;
        }

        .score-yellow {
            background-color: #ffc107;
            color: black;
        }

        .score-red {
            background-color: #ff0707;
            color: white;
        }

        .actions a {
            margin-right: 10px;
        }

        .star-rating {
            display: inline-block;
        }

        .star {
            font-size: 25px;
            color: #ccc;
            cursor: pointer;
            transition: color 0.2s;
        }

        .star.hovered,
        .star.selected {
            color: gold;
        }
    </style>
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
                    <% for (ReviewEntity review : reviews) {
                            int score = review.getRating();
                            String scoreClass = score >= 8 ? "score-green" : score >= 5 ? "score-yellow" : "score-red";
                        %>
                        <div class="review">
                            <span class="review-score <%= scoreClass %>"><%= score %>/10</span>
                            <p><strong><%= review.getUser().getName() %>:</strong></p>
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
<script>
    const stars = document.querySelectorAll('.star-rating .star');
    const ratingInput = document.getElementById('rating');
    let selectedValue = 0; // Guarda la puntuación seleccionada

    stars.forEach(star => {
        // Hover: resalta temporalmente
        star.addEventListener('mouseenter', () => {
            const value = parseInt(star.getAttribute('data-value'));
            stars.forEach(s => {
                s.classList.toggle('hovered', parseInt(s.getAttribute('data-value')) <= value);
            });
        });

        // Salir del hover: vuelve a mostrar solo las seleccionadas
        star.addEventListener('mouseleave', () => {
            stars.forEach(s => s.classList.remove('hovered'));
            stars.forEach(s => {
                s.classList.toggle('selected', parseInt(s.getAttribute('data-value')) <= selectedValue);
            });
        });

        // Click: guarda el valor y marca las estrellas seleccionadas
        star.addEventListener('click', () => {
            selectedValue = parseInt(star.getAttribute('data-value'));
            ratingInput.value = selectedValue;

            stars.forEach(s => {
                s.classList.remove('selected');
                if (parseInt(s.getAttribute('data-value')) <= selectedValue) {
                    s.classList.add('selected');
                }
            });
        });
    });
</script>


</body>
</html>
