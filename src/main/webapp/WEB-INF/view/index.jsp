<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ReviewFilms</title>
    <link rel="stylesheet" type="text/css" href="/css/indexEstilo.css">
</head>
<%
    List<Movie> movieList = (List<Movie>) request.getAttribute("movieList");
    boolean esEditor = true;
%>
<body>
    <div class="page-container">
        <jsp:include page="cabecera.jsp"/>
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
</body>
</html>
