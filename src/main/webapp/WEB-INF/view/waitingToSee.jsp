<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Seen" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Watchlist" %>
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
    List<Watchlist>watchlistList=(List<Watchlist>) request.getAttribute("watchlistList");
%>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>
    <jsp:include page="logout.jsp"/>
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

                    <form:button class="filter-button">Filtrar</form:button>
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
                                <th>Desmarcar como Pendiente</th>
                            </tr>
                            </thead>
                            <tbody>
                            <%
                                for (Watchlist movie : watchlistList) {
                            %>
                            <tr>
                                <td>
                                    <a href="/viewmovie?id=<%=movie.getMovie().getId()%>&desdeWaitingToSee=true">
                                        <img src="<%= movie.getMovie().getImageUrl() %>" alt="<%= movie.getMovie().getOriginalTitle() %>" class="thumbnail">
                                    </a>
                                </td>
                                <td><%= movie.getMovie().getOriginalTitle() %></td>
                                <td><%= movie.getMovie().getRuntime()%> min</td>
                                <td><%= movie.getMovie().getVoteAverage() %></td>
                                <td>
                                    <!-- QUITAR COMO PENDIENTE UNA PELICULA -->
                                    <form method="post" action="/quitarComoPendiente" class="watched-button-form">
                                        <input type="hidden" name="idMovie" value="<%= movie.getMovie().getId() %>">
                                        <button type="submit" class="icon-button trash-button" title="Quitar de pendientes">
                                            <i class="fas fa-trash"></i>
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