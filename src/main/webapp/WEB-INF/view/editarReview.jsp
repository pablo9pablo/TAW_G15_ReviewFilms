<!--LUCIA ROSALES SANTIAGO: 100% -->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Reseña</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editarReview.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<%
    MovieDTO movie = (MovieDTO) request.getAttribute("movie");
%>
<body>
    <div class="page-container">
        <jsp:include page="cabecera.jsp"/>
        <jsp:include page="logout.jsp"/>

        <div class="page-content">
            <h2 class="page-title">Editar reseña</h2>
            <div class="table-wrapper">
                <form:form modelAttribute="review" method="post" action="/savereview">
                    <form:hidden path="id"/>
                    <form:hidden path="movieDTO.id"/>
                    <input type="hidden" name="origen" value="${origen}"/>

                    <table class="movie-table">
                        <tr>
                            <td class="thumbnail-cell">
                                <img src="<%=movie.getImageUrl()%>" alt="Poster" class="foto">
                            </td>
                            <td>
                                <strong><%=movie.getTitle()%></strong>
                                <span class="year"><%=movie.getReleaseDate().getYear()%></span>

                                <p><strong>Descripcion:</strong></p>
                                <form:textarea path="description" rows="5" cols="80" cssClass="review-textarea"/>

                                <div class="rating-section">
                                    <strong>Rating:</strong>
                                    <form:select path="rating" required="true" cssClass="rating-select">
                                        <%
                                            for(int i=0; i<=10; i++){
                                        %>
                                                 <form:option value="<%=i%>" label="<%=String.valueOf(i)%>"/>
                                        <%
                                            }
                                        %>
                                    </form:select>
                                    <i class="bi bi-star-fill"></i>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <div class="button-container">
                        <a href="<%= "pelicula".equals(request.getParameter("origen")) ? "/viewmovie?id=" + movie.getId() : "/reviews" %>"
                           class="button button-cancel">Cancelar</a>
                        <form:button class="button button-save">Guardar</form:button>
                    </div>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
