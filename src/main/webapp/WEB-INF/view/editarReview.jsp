<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Reseña</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/editarReview.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">

</head>
<%
    Movie movie = (Movie) request.getAttribute("movie");
%>
<body>
    <div class="page-container">
        <jsp:include page="cabecera.jsp"/>
        <jsp:include page="logout.jsp"/>

        <div class="page-content">
            <h2>Edit review:</h2>
            <div class="table-wrapper">
                <form:form modelAttribute="reviewUI" method="post" action="/savereview">
                    <form:hidden path="id"/>
                    <form:hidden path="movieId"/>

                    <table class="movie-table">
                        <tr>
                            <td class="thumbnail-cell">
                                <img src="<%=movie.getImageUrl()%>" alt="Poster" class="thumbnail">
                            </td>
                            <td>
                                <div class="movie-info">
                                    <strong><%=movie.getTitle()%></strong>
                                    <span class="year"><%=movie.getReleaseDate().getYear()%></span>
                                </div>

                                <p><strong>Descripcion:</strong></p>
                                <form:textarea path="description" rows="5" cols="80" cssClass="review-textarea"/>

                                <div class="rating-section">
                                    <p><strong>Rating:</strong></p><form:input path="rating" type="number" min="0" max="10" required="true" cssClass="rating-input"/>
                                    <span class="stars">⭐ (0-10)</span><br/>
                                </div>

                                <a href="/reviews">Cancel</a>
                                <form:button>Save Changes</form:button>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
