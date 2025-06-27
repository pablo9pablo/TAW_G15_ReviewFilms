<!-- PABLO MARTINEZ PALOP : 100% -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.*" %>
<%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
    <title>Estadísticas de ReviewFilms</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estadisticas.css">
</head>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>

    <div class="main-stats-container">
        <div class="movies-stats">
            <h1 class="column-title">Estadísticas de Películas</h1>

            <div class="stats-section">
                <h2>Top 3 Películas Mejor Valoradas</h2>
                <table class="stats-table">
                    <tr>
                        <th>Título</th>
                        <th>Valoración</th>
                        <th>Nº Reseñas</th>
                    </tr>
                    <%
                        List<Object[]> topMovies = (List<Object[]>)request.getAttribute("topRatedMovies");
                        if (topMovies != null) {
                            for(Object[] item : topMovies) {
                                Movie movie = (Movie)item[0];
                                Double rating = (Double)item[1];
                                Long reviews = (Long)item[2];
                    %>
                    <tr>
                        <td><%= movie.getTitle() %></td>
                        <td><%= String.format("%.1f", rating) %></td>
                        <td><%= reviews %></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>

            <div class="stats-section">
                <h2>Top 3 Películas Peor Valoradas</h2>
                <table class="stats-table">
                    <tr>
                        <th>Título</th>
                        <th>Valoración</th>
                        <th>Nº Reseñas</th>
                    </tr>
                    <%
                        List<Object[]> worstMovies = (List<Object[]>)request.getAttribute("worstRatedMovies");
                        if (worstMovies != null) {
                            for(Object[] item : worstMovies) {
                                Movie movie = (Movie)item[0];
                                Double rating = (Double)item[1];
                                Long reviews = (Long)item[2];
                    %>
                    <tr>
                        <td><%= movie.getTitle() %></td>
                        <td><%= String.format("%.1f", rating) %></td>
                        <td><%= reviews %></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>

            <div class="stats-section">
                <h2>Top 5 Películas con Más Reseñas</h2>
                <table class="stats-table">
                    <tr>
                        <th>Título</th>
                        <th>Número de Reseñas</th>
                    </tr>
                    <%
                        List<Object[]> mostReviewed = (List<Object[]>)request.getAttribute("mostReviewedMovies");
                        if (mostReviewed != null) {
                            for(Object[] item : mostReviewed) {
                                Movie movie = (Movie)item[0];
                                Long reviews = (Long)item[1];
                    %>
                    <tr>
                        <td><%= movie.getTitle() %></td>
                        <td><%= reviews %></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>

            <div class="stats-section">
                <h2>Top 5 Películas Más Taquilleras</h2>
                <table class="stats-table">
                    <tr>
                        <th>Título</th>
                        <th>Recaudación ($)</th>
                    </tr>
                    <%
                        List<Movie> grossingMovies = (List<Movie>)request.getAttribute("highestGrossingMovies");
                        if (grossingMovies != null) {
                            DecimalFormat formatter = new DecimalFormat("#,###");
                            for(Movie movie : grossingMovies) {
                    %>
                    <tr>
                        <td><%= movie.getTitle() %></td>
                        <td>$<%= formatter.format(movie.getRevenue()) %></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>

            <div class="stats-section">
                <h2>Top 5 Compañías Productoras</h2>
                <table class="stats-table">
                    <tr>
                        <th>Compañía Productora</th>
                        <th>Número de Películas</th>
                    </tr>
                    <%
                        List<Object[]> producerStats = (List<Object[]>)request.getAttribute("topProductionCompanies");
                        if (producerStats != null) {
                            for(Object[] item : producerStats) {
                                String company = (String)item[0];
                                Long count = (Long)item[1];
                    %>
                    <tr>
                        <td><%= company %></td>
                        <td><%= count %></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
        </div>

        <div class="general-stats">
            <h1 class="column-title">Estadísticas Generales</h1>

            <div class="stats-section">
                <h2>Top 3 Usuarios Más Activos</h2>
                <table class="stats-table">
                    <tr>
                        <th>Usuario</th>
                        <th>Reseñas</th>
                    </tr>
                    <%
                        List<Object[]> reviewerStats = (List<Object[]>)request.getAttribute("topReviewers");
                        if (reviewerStats != null && !reviewerStats.isEmpty()) {
                            for(Object[] item : reviewerStats) {
                                String email = (String)item[0];
                                Long count = (Long)item[1];
                    %>
                    <tr>
                        <td><%= email %></td>
                        <td><%= count %></td>
                    </tr>
                    <%
                        }
                        for(int i = reviewerStats.size(); i < 3; i++) {
                    %>
                    <tr>
                        <td colspan="2" class="no-data">No hay más usuarios con reseñas</td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>

            <div class="stats-section">
                <h2>Top 3 Usuarios con Más Favoritos</h2>
                <table class="stats-table">
                    <tr>
                        <th>Usuario</th>
                        <th>Favoritos</th>
                    </tr>
                    <%
                        List<Object[]> favoriteStats = (List<Object[]>)request.getAttribute("topFavorites");
                        if (favoriteStats != null && !favoriteStats.isEmpty()) {
                            for(Object[] item : favoriteStats) {
                                String email = (String)item[0];
                                Long count = (Long)item[1];
                    %>
                    <tr>
                        <td><%= email %></td>
                        <td><%= count %></td>
                    </tr>
                    <%
                        }
                        for(int i = favoriteStats.size(); i < 3; i++) {
                    %>
                    <tr>
                        <td colspan="2" class="no-data">No hay más usuarios con favoritos</td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>

            <div class="stats-section">
                <h2>Top 5 Actores Más Frecuentes</h2>
                <table class="stats-table">
                    <tr>
                        <th>Actor</th>
                        <th>Número de Películas</th>
                    </tr>
                    <%
                        List<Object[]> actorStats = (List<Object[]>)request.getAttribute("topActors");
                        if (actorStats != null && !actorStats.isEmpty()) {
                            for(Object[] item : actorStats) {
                                String actorName = (String)item[0];
                                Long movieCount = (Long)item[1];
                    %>
                    <tr>
                        <td><%= actorName %></td>
                        <td><%= movieCount %></td>
                    </tr>
                    <%
                        }
                        for(int i = actorStats.size(); i < 5; i++) {
                    %>
                    <tr>
                        <td colspan="2" class="no-data">No hay más actores</td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>

            <div class="stats-section">
                <h2>Distribución de Películas por Género</h2>
                <table class="stats-table">
                    <tr>
                        <th>Género</th>
                        <th>Número de Películas</th>
                        <th>Porcentaje</th>
                    </tr>
                    <%
                        List<Object[]> genreStats = (List<Object[]>)request.getAttribute("moviesByGenre");
                        Long total = (Long)request.getAttribute("totalMovies");
                        if (genreStats != null && total != null) {
                            for(Object[] item : genreStats) {
                                String genre = (String)item[0];
                                Long count = (Long)item[1];
                                double percentage = (count.doubleValue() / total.doubleValue()) * 100;
                    %>
                    <tr>
                        <td><%= genre %></td>
                        <td><%= count %></td>
                        <td><%= String.format("%.1f", percentage) %>%</td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>