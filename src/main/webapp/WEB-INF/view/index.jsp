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
%>
<body>
    <div class="page-container">
        <jsp:include page="cabecera.jsp"/>
        <jsp:include page="logout.jsp"/>

        <div class="page-content">
            <!-- Formulario de búsqueda -->
            <form:form method="post" action="/buscar" modelAttribute="busqueda" class="search-form">
                <div class="search-input-wrapper">
                    <div class="search-field">
                        <form:input path="texto" type="text" id="searchInput" name="busqueda" class="search-input" placeholder="Buscar película..."/>
                        <span class="search-icon">🔍</span>
                    </div>
                    <form:button class="search-button">Buscar</form:button>
                </div>
            </form:form>


            <!-- Formulario de filtrado -->
            <form:form method="post" action="/filtrar" modelAttribute="filtro" class="filter-form">
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

                    <form:button class="filter-button">Filtrar</form:button>
                </div>
            </form:form>


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

            <%
                boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
            %>

            <% if (isAdmin) { %>
            <a href="/editmovie" class="add-movie-btn">+ Añadir nueva película</a>
            <% } %>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
    <script src="/js/indexScript.js"></script>
</body>
</html>
