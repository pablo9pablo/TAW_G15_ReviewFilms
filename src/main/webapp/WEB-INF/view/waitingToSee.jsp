<!-- MIGUEL LABELLA RAMÍREZ: 100% -->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Watchlist" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.WatchlistDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>ReviewFilms</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/waitingToSee.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

</head>
<%
    List<WatchlistDTO> watchlistList = (List<WatchlistDTO>) request.getAttribute("watchlistList");
%>

<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>
        <div class="page-content">
            <!-- Formulario de filtrado -->
            <form:form method="post" action="/filtrarWaitingToSee" modelAttribute="filtroWaitingToSee" class="filter-form">
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

                    <form:button class="button-generico">Filtrar</form:button>
                </div>
            </form:form>

            <!-- Formulario para ordenar ASC -->
            <div class="table-controls">
                <form:form method="post" action="/ascWaiting" modelAttribute="filtroWaitingToSee">
                    <form:hidden path="year" />
                    <form:hidden path="vote" />
                    <c:forEach var="id" items="${filtroWaitingToSee.generoIds}">
                        <input type="hidden" name="generoIds" value="${id}" />
                    </c:forEach>
                    <button type="submit" class="scroll-btn" id="scroll-up">↑</button>
                </form:form>
            </div>

            <!-- Formulario para ordenar DESC -->
            <div class="table-controls">
                <form:form method="post" action="/descWaiting" modelAttribute="filtroWaitingToSee">
                    <form:hidden path="year" />
                    <form:hidden path="vote" />
                    <c:forEach var="id" items="${filtroWaitingToSee.generoIds}">
                        <input type="hidden" name="generoIds" value="${id}" />
                    </c:forEach>
                    <button type="submit" class="scroll-btn" id="scroll-down">↓</button>
                </form:form>
            </div>


            <!-- Tabla de películas pendientes de ver -->
            <div class="table-wrapper">
                <div class="table-header">
                    <table class="movie-table">
                        <thead>
                        <tr>
                            <th>Portada</th>
                            <th>Título</th>
                            <th>Duración</th>
                            <th>Calificación Media</th>
                            <th>Eliminar de la lista</th>
                            <th>Marcar como vista</th>
                        </tr>
                        </thead>
                    </table>
                </div>

                <div class="table-body-scroll">
                    <table class="movie-table">
                        <tbody>
                        <%
                            for (WatchlistDTO movie : watchlistList) {
                        %>
                        <tr>
                            <td>
                                <a href="/viewmovie?id=<%=movie.getMovieId()%>&desdeWaitingToSee=true">
                                    <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" class="thumbnail">
                                </a>
                            </td>
                            <td><%= movie.getOriginalTitle()%></td>
                            <td><%= movie.getRuntime()%> min</td>
                            <td><%= movie.getVoteAverage() != null ? movie.getVoteAverage() : "No ha sido calificada" %></td>
                            <td>
                                <form method="post" action="/eliminarDePendiente" class="watched-button-form">
                                    <input type="hidden" name="idMovie" value="<%= movie.getMovieId() %>">
                                    <button type="submit" class="icon-button trash-button" title="Quitar de pendientes">
                                        <i class="fas fa-trash"></i>
                                    </button>
                                </form>
                            </td>
                            <td>
                                <form method="post" action="/marcarComoVistaDesdePendiente" class="watched-button-form">
                                    <input type="hidden" name="idMovie" value="<%= movie.getMovieId() %>">
                                    <button type="submit" class="icon-button green-icon" title="Marcar como vista">
                                        <i class="fas fa-eye"></i>
                                    </button>
                                </form>
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
</body>
</html>