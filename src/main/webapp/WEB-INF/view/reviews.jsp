<!--LUCIA ROSALES SANTIAGO: 100% -->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ReviewDTO" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reseñas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reviews.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/filtros.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">

</head>
<%
    List<ReviewDTO> reviews = (List<ReviewDTO>) request.getAttribute("reviews");
%>
<body>
    <div class="page-container">
        <jsp:include page="cabecera.jsp"/>
        <jsp:include page="logout.jsp"/>

        <div class="page-content">
            <h1 class="page-title">Tus reseñas cinematográficas</h1>

            <!--FILTRO DE REVIEW---------------------------------------------------------------------->
            <form:form modelAttribute="filtroReview" method="post" action="/filtrarReview" cssClass="filter-form">
                    <label>Fecha de la Review:</label>
                    <form:input path="fecha" type="date"/>
                    <label>Calificación mínima:</label>
                    <form:select path="vote">
                        <form:option value="-1">Seleccione una calificación</form:option>
                        <%
                            for(int i=0; i<=10; i++){
                        %>
                            <form:option value="<%=i%>" label="<%=String.valueOf(i)%>"/>
                        <%
                            }
                        %>
                    </form:select>
                <form:button class="button-generico">Filtrar</form:button>
            </form:form>

            <h3 class="review-count">Total reviews: <%=(reviews != null) ? reviews.size() : "0" %></h3>

            <div class="table-wrapper">
                <div class="table-header">
                    <table class="movie-table">
                        <tr>
                            <th>Portada</th>
                            <th>Reseña</th>
                            <th>Calificación</th>
                            <th></th>
                        </tr>
                    </table>
                </div>
                <div class="table-body-scroll">
                    <table class="movie-table">
                        <%
                            if (reviews != null && !reviews.isEmpty()) {
                                for (ReviewDTO review : reviews) {
                                    MovieDTO movie = review.getMovieDTO();
                        %>
                        <tr>
                            <td>
                                <a href="/viewmovie?id=<%=movie.getId()%>">
                                    <img src="<%= movie.getImageUrl() %>" alt="Portada" class="thumbnail">
                                </a>
                            </td>
                            <td class="descripcion-column">
                                <strong><%= movie.getTitle()%></strong><br>
                                <div class="descripcion-box">
                                    <span><%=review.getDescription()%></span>
                                </div>
                            </td>
                            <td>
                                <%=review.getRating()%> <i class="bi bi-star-fill"></i>
                            </td>
                            <td>
                                <div class="actions-container">
                                    <!--EDITAR REVIEW-------------------------------------------------->
                                    <a href="/editReview?id=<%=review.getId()%>&origen=reviews" class="edit-button"><i class="bi bi-pencil-fill"></i></a>

                                    <!--BORRAR REVIEW-------------------------------------------------->
                                    <form action="/deleteReview" method="post" onsubmit="return confirm('¿Estás seguro de que quieres borrar esta reseña?');">
                                        <input type="hidden" name="id" value="<%=review.getId()%>">
                                        <button type="submit" class="delete-button"><i class="bi bi-trash-fill"></i></button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="4">No se han encontrado reseñas.</td>
                        </tr>
                        <%
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
