<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<style>

    .carousel-container {
        display: flex;
        align-items: center;
        justify-content: center;
        position: relative;
    }

    .carousel {
        display: flex; /* Flex para alinearlos en una fila */
        overflow-x: auto; /* Permite el desplazamiento horizontal */
        scroll-behavior: smooth; /* Suaviza el desplazamiento */
        gap: 10px; /* Espaciado entre los elementos */
        padding: 10px;
    }

    .movie-card {
        display: flex;
        flex-direction: column;
        align-items: center;
        text-align: center;
        text-decoration: none;
        color: black;
        max-width: 200px;
        border-radius: 10px;
    }

    .movie-card img {
        width: 100%; /* Asegura que las imágenes no se distorsionen */
        height: auto;
        object-fit: cover; /* Para cubrir el área sin deformar la imagen */
        border-radius: 10px;
    }

    .nav-arrow {
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        background-color: rgba(0, 0, 0, 0.5);
        color: white;
        font-size: 24px;
        padding: 10px;
        cursor: pointer;
        z-index: 1;
    }

    .nav-arrow:first-child {
        left: 0;
    }

    .nav-arrow:last-child {
        right: 0;
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

    .movie-card img {
        max-width: 200px;
        height: auto;
        border-radius: 10px;
        object-fit: cover;
    }

    .movie-card {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: 10px;
        text-align: center;
        text-decoration: none;
        color: black;
        max-width: 200px;
    }

</style>
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
<script>
    function scrollCarousel(direction) {
        const container = document.getElementById('carousel');
        const scrollAmount = 300;
        container.scrollBy({ left: direction * scrollAmount, behavior: 'smooth' });
    }
</script>
</body>
</html>
