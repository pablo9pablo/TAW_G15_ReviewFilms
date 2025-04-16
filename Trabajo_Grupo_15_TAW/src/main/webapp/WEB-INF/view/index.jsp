<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.GenreEntity" %>
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
        .search-form,
        .filter-form {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 20px 0;
            gap: 15px;
        }

        .search-input-wrapper {
            display: flex;
            flex-direction: row;
            align-items: center;
            width: 50%;
        }

        .search-label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .search-field {
            position: relative;
            width: 100%;
            display: flex;
            align-items: center;
        }

        .search-input {
            width: 100%;
            padding: 10px 40px 10px 35px;
            font-size: 16px;
            border-radius: 999px;
            border: 2px solid #333;
            outline: none;
        }

        .search-icon {
            position: absolute;
            left: 10px;
            top: 50%;
            transform: translateY(-50%);
            font-size: 18px;
            color: #888;
        }

        .search-button {
            background-color: #2c3e50;
            color: white;
            padding: 8px 16px;
            font-size: 14px;
            border-radius: 12px;
            border: 2px solid #2c3e50;
            cursor: pointer;
            margin-left:25px;
        }

        .filters-wrapper {
            display: flex;
            flex-direction: row;
            gap: 30px;
            align-items: flex-end;
            flex-wrap: wrap;
        }

        .filter-group {
            display: flex;
            flex-direction: column;
        }


        .filter-group input,
        .filter-group select {
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 150px;
            box-sizing: border-box;
        }

        .filter-group label {
            margin-bottom: 5px;
            font-weight: bold;
        }

        .filter-button {
            background-color: #2c3e50;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            height: fit-content;
            font-weight: bold;
        }

        .filter-button:hover {
            background-color: #34495e;
        }




    </style>
</head>

<%
    List<MovieEntity> movieList = (List<MovieEntity>) request.getAttribute("movieList");
    List<MovieEntity> movieListBusqueda=(List<MovieEntity>) request.getAttribute("movieListBusqueda");
    List<GenreEntity> genreList = (List<GenreEntity>) request.getAttribute("genreList");
%>

<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>

    <form method="post" action="/buscar" class="search-form">
        <!-- Barra de búsqueda -->
        <div class="search-input-wrapper">
            <div class="search-field">
                <input type="text" id="searchInput" name="busqueda" class="search-input">
                <span class="search-icon">🔍</span>
            </div>
            <!-- Botón de búsqueda -->
            <input type="submit" class="search-button" value="Buscar"</input>
        </div>
    </form>

    <form method="post" action="/filtrar" class="filter-form">

        <div class="filters-wrapper">
            <span class="filter-label">Browse by:</span>

            <div class="range-group">
                <label for="yearInput">Year:</label>
                <input type="number" id="yearInput" name="year" min="1895" max="2025" value="2025" class="year-input">
            </div>

            <div class="range-group">
                <label for="ratingSelect">Rating:</label>
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

            <select class="filter-select" name="genre">
                <option value="">Genre</option>

                <c:forEach var="genre" items="${genreList}">
                    <option value="${genre.name}">${genre.name}</option>
                </c:forEach>


            </select>


            <input type="submit" class="filter-button" value="Filtrar">
        </div>
    </form>


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
