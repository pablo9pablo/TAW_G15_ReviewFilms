<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ReviewFilms</title>
    <style>
        body {
            background: #f8f8f8;
            margin: 0;
            padding: 20px;
        }
        html, body {
            height: 100%;
            margin: 0;
        }

        .page-container {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .carousel-container {
            flex: 1;
        }


        .carousel-container {
            display: flex;
            align-items: center;
            justify-content: center;
            max-width: 100%;
            overflow: hidden;
            margin: 40px auto;
            flex: 1;
        }

        .carousel {
            display: flex;
            gap: 20px;
            overflow-x: auto;
            scroll-behavior: smooth;
            padding: 20px;
        }

        .carousel::-webkit-scrollbar {
            display: none;
        }

        .movie-card {
            flex: 0 0 auto;
            width: 180px;
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
            text-align: center;
            text-decoration: none;
            color: black;
        }

        .movie-card img {
            width: 100%;
            height: 270px;
            object-fit: cover;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
        }

        .movie-card p {
            margin: 10px;
            font-weight: bold;
        }

        .nav-arrow {
            font-size: 2rem;
            cursor: pointer;
            user-select: none;
            padding: 10px;
        }
    </style>
</head>

<%
    List<MovieEntity> movieList = (List<MovieEntity>) request.getAttribute("movieList");
%>

<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>

    <h1>Películas destacadas</h1>

    <div class="carousel-container">
        <div class="nav-arrow" onclick="scrollCarousel(-1)">&#10094;</div>

        <div class="carousel" id="carousel">
            <%
                for (MovieEntity movie : movieList) {
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
    <jsp:include page="footer.jsp"/>

</div>
<script>
    function scrollCarousel(direction) {
        const container = document.getElementById('carousel');
        const scrollAmount = 300;
        container.scrollBy({ left: direction * scrollAmount, behavior: 'smooth' });
    }
</script>

</body>
</html>
