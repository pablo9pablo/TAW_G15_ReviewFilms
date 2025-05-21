<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Perfil de Usuario</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/perfilUsuario.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/logout.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
</head>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp" />
    <div class="page-content">
        <div class="profile-header">
            <h2>Mi Perfil</h2>
        </div>

        <%
            String email = (String) request.getAttribute("usuarioEmail");
            Long numeroPeliculasVistas = (Long) request.getAttribute("numeroPeliculasVistas");
            Long numeroPeliculasFavoritas = (Long) request.getAttribute("numeroPeliculasFavoritas");
            Long tiempoTotalVisto = (Long) request.getAttribute("tiempoTotalVisto");
            Long peliculasPendientes = (Long) request.getAttribute("peliculasPendientes");
            List<Object[]> generosMasVistos = (List<Object[]>) request.getAttribute("generosMasVistos");
            Double duracionPromedio = (Double) request.getAttribute("duracionPromedio");
            Double puntuacionPromedio = (Double) request.getAttribute("puntuacionPromedio");
            List<Object[]> topPeliculasPorAnio = (List<Object[]>) request.getAttribute("topPeliculasPorAnio");
            List<Object[]> topPeliculasPorActor = (List<Object[]>) request.getAttribute("topPeliculasPorActor");
            if (email != null) {
        %>
        <div class="user-email">
            <strong>Email:</strong> <%= email %>
        </div>
        <a href="/editarPerfil" class="button-generico">Editar Perfil</a>

        <jsp:include page="logout.jsp" />

        <div class="table-wrapper">
            <div class="table-wrapper-scroll-left">
                <table class="movie-table">
                    <tr>
                        <th>Estadísticas Generales</th>
                        <th>Estadísticas Detalladas</th>
                    </tr>
                    <tr>
                        <td>
                            <!-- Parte izquierda sin cambios -->
                            <div class="simple-stat">
                                <span>Películas vistas: </span>
                                <span class="stat-value"><%= numeroPeliculasVistas != null ? numeroPeliculasVistas : 0 %></span>
                            </div>
                            <div class="simple-stat">
                                <span>Películas favoritas: </span>
                                <span class="stat-value"><%= numeroPeliculasFavoritas != null ? numeroPeliculasFavoritas : 0 %></span>
                            </div>
                            <div class="simple-stat">
                                <span>Tiempo total de visualización: </span>
                                <span class="stat-value"><%= tiempoTotalVisto != null ? String.format("%.1f días", tiempoTotalVisto / 1440.0) : "0 días" %></span>
                            </div>
                            <div class="simple-stat">
                                <span>Películas pendientes: </span>
                                <span class="stat-value"><%= peliculasPendientes != null ? peliculasPendientes : 0 %></span>
                            </div>

                            <div class="stat-section">
                                <h4 class="stat-section-title">Top Géneros más vistos:</h4>
                                <%
                                    if (generosMasVistos != null && !generosMasVistos.isEmpty()) {
                                        for (Object[] genero : generosMasVistos) {
                                            if (genero != null && genero.length >= 2) {
                                %>
                                <div class="list-item">
                                    <%= genero[0] %>: <%= genero[1] %> películas
                                </div>
                                <%
                                        }
                                    }
                                } else {
                                %>
                                <div class="list-item">No hay datos disponibles</div>
                                <%
                                    }
                                %>
                            </div>
                        </td>
                        <td>
                            <!-- Estadísticas detalladas -->
                            <div class="simple-stat">
                                <span>Duración promedio: </span>
                                <span class="stat-value"><%= duracionPromedio != null ? String.format("%.0f minutos", duracionPromedio) : "N/A" %></span>
                            </div>
                            <div class="simple-stat">
                                <span>Puntuación promedio: </span>
                                <span class="stat-value"><%= puntuacionPromedio != null ? String.format("%.2f", puntuacionPromedio) : "N/A" %></span>
                            </div>

                            <div class="stat-section">
                                <h4 class="stat-section-title">Top Actores más vistos:</h4>
                                <%
                                    if (topPeliculasPorActor != null && !topPeliculasPorActor.isEmpty()) {
                                        for (Object[] actor : topPeliculasPorActor) {
                                            if (actor != null && actor.length >= 2) {
                                %>
                                <div class="list-item">
                                    <%= actor[0] %>: <%= actor[1] %> películas
                                </div>
                                <%
                                        }
                                    }
                                } else {
                                %>
                                <div class="list-item">No hay datos disponibles</div>
                                <%
                                    }
                                %>
                            </div>

                            <div class="stat-section">
                                <h4 class="stat-section-title">Top Años más vistos:</h4>
                                <%
                                    if (topPeliculasPorAnio != null && !topPeliculasPorAnio.isEmpty()) {
                                        for (Object[] anio : topPeliculasPorAnio) {
                                            if (anio != null && anio.length >= 2) {
                                %>
                                <div class="list-item">
                                    <%= anio[0] %>: <%= anio[1] %> películas
                                </div>
                                <%
                                        }
                                    }
                                } else {
                                %>
                                <div class="list-item">No hay datos disponibles</div>
                                <%
                                    }
                                %>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <%
        } else {
        %>
        <div class="error-message">
            <strong>Error:</strong> No se pudo cargar la información del perfil.
        </div>
        <%
            }
        %>
    </div>
    <jsp:include page="footer.jsp" />
</div>
</body>
</html>