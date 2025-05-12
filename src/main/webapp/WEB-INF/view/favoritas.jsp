<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Favorite" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ReviewFilms</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/favoritas.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<%
    List<Favorite>favouriteList=(List<Favorite>) request.getAttribute("favouriteList");
%>
<body>

</body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>
    <jsp:include page="logout.jsp"/>

    <div class="page-content">
        <!-- Formulario de filtrado -->
        <form:form method="post" action="/filtrarFavourite" modelAttribute="filtroFavourite" class="filter-form">
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

        <!-- Formulario para ordenar ASC -->
        <div class="table-controls">
            <form:form method="post" action="/ascFavourite" modelAttribute="filtroFavourite">
                <form:hidden path="year" />
                <form:hidden path="vote" />
                <c:forEach var="id" items="${filtroFavourite.generoIds}">
                    <input type="hidden" name="generoIds" value="${id}" />
                </c:forEach>
                <button type="submit" class="scroll-btn" id="scroll-up">↑</button>
            </form:form>
        </div>

        <!-- Formulario para ordenar DESC -->
        <div class="table-controls">
            <form:form method="post" action="/descFavourite" modelAttribute="filtroFavourite">
                <form:hidden path="year" />
                <form:hidden path="vote" />
                <c:forEach var="id" items="${filtroFavourite.generoIds}">
                    <input type="hidden" name="generoIds" value="${id}" />
                </c:forEach>
                <button type="submit" class="scroll-btn" id="scroll-down">↓</button>
            </form:form>
        </div>

        <!-- Tabla de películas vistas -->
        <div class="table-wrapper table-wrapper-scroll-left">
            <div class="content">
                <table class="movie-table">
                    <thead>
                    <tr>
                        <th>Portada</th>
                        <th>Título</th>
                        <th>Duración</th>
                        <th>Calificación Media</th>
                        <th>Desmarcar Como Favorita</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for (Favorite movie:favouriteList) {
                    %>
                    <tr>
                        <td>
                            <a href="/viewmovie?id=<%=movie.getMovie().getId()%>&desdeFavoritas=true">
                                <img src="<%= movie.getMovie().getImageUrl() %>" alt="<%= movie.getMovie().getOriginalTitle() %>" class="thumbnail">
                            </a>

                        </td>
                        <td><%= movie.getMovie().getOriginalTitle() %></td>
                        <td><%= movie.getMovie().getRuntime()%> min</td>
                        <td><%= movie.getMovie().getVoteAverage() != null ? movie.getMovie().getVoteAverage() : "No ha sido calificada" %></td>
                        <td>
                            <!-- QUITAR COMO FAVORITA -->
                            <form method="post" action="/quitarComoFavorita" class="watched-button-form">
                                <input type="hidden" name="idMovie" value="<%= movie.getMovie().getId() %>">
                                <button type="submit" class="icon-button" title="Desmarcar como favorita">
                                    <i class="fas fa-star yellow-icon"></i>
                                </button>
                            </form>
                            <!----------------------------------->
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>

</div>
<jsp:include page="footer.jsp"/>
</div>
<script src="/js/indexScript.js"></script>
</body>
</html>