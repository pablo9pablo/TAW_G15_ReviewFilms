<!-- MANUEL GALÁN ALFARO: 50% -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ProductionCompanyDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    MovieDTO movie = (MovieDTO) request.getAttribute("movie");
    List<ProductionCompanyDTO> companies = (List<ProductionCompanyDTO>) request.getAttribute("companies");
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= movie.getTitle() %> - Productoras</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/verPelicula.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>

    <div class="movie-container">
        <div class="left-panel">
            <img src="<%= movie.getImageUrl() %>" alt="<%= movie.getOriginalTitle() %>" width="180"/>
            <div class="movie-meta">
                <p><strong>Release:</strong> <%= movie.getReleaseDate() %></p>
                <p><strong>Runtime:</strong> <%= movie.getRuntime() %> mins</p>
                <p><strong>Budget:</strong> <%= movie.getBudget() %>€</p>
            </div>
        </div>

        <div class="right-panel">
            <h1><%= movie.getTitle() %>
                <span class="rating-box">
                    <%= (movie.getVoteAverage() == null || movie.getVoteCount() == null || movie.getVoteCount() == 0)
                            ? "Sin calificación"
                            : movie.getVoteAverage() + "/10" %>
                </span>
            </h1>

            <div class="tabs">
                <a href="${pageContext.request.contextPath}/viewmovie?id=${movie.id}">
                    <button>Main</button>
                </a>
                <a href="${pageContext.request.contextPath}/movies/cast?id=${movie.id}">
                    <button>Cast</button>
                </a>
                <a href="${pageContext.request.contextPath}/movies/crew?id=${movie.id}">
                    <button>Crew</button>
                </a>
                <a href="${pageContext.request.contextPath}/productionCompanies/movieProductionCompanies?id=${movie.id}">
                    <button class="active-tab">Productoras</button>
                </a>
                <a href="${pageContext.request.contextPath}/movieGenres?id=${movie.id}">
                    <button>Géneros</button>
                </a>
            </div>

            <div class="movie-info">
                <p><%= movie.getOverview() == null ? "No existe resumen de esta película" : movie.getOverview() %></p>
            </div>

            <div class="movie-info">
                <h3>Lista de Productoras</h3>
                <table border="1" cellspacing="0" cellpadding="5" width="100%">
                    <thead>
                    <tr>
                        <th align="left">Nombre</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (ProductionCompanyDTO company : companies) { %>
                    <tr>
                        <td><%= (company.getName() != null) ? company.getName() : "-" %></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!--EDITAR MOVIE--------------------------------------------------------->
    <%
        boolean isAdmin = request.isUserInRole("ROLE_ADMIN");

        if (isAdmin) {
    %>
    <div class="button-container">
        <a href="<%= request.getContextPath() %>/editmovie?id=<%=movie.getId()%>" class="button-generico">Modificar Película</a>
        <a href="<%= request.getContextPath() %>/deletemovie?id=<%= movie.getId() %>"
           onclick="return confirm('¿Está seguro de que quiere borrar la película <%= movie.getTitle() %>?')" class="button-generico">
            Borrar
        </a>
    </div>
    <%
        }
    %>

    <jsp:include page="footer.jsp"/>
</div>

<script src="/js/VerPeliculaScript.js"></script>
</body>
</html>
