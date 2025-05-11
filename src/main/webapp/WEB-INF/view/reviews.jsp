<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Review" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reseñas</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/reviews.css">

</head>
<%
    List<Review> reviews = (List<Review>) request.getAttribute("reviews");
%>
<body>
    <div class="page-container">
        <jsp:include page="cabecera.jsp"/>
        <jsp:include page="logout.jsp"/>

        <div class="page-content">
            <h2>Total reviews: <%=(reviews != null) ? reviews.size() : "0" %></h2>

            <div class="table-wrapper table-wrapper-scroll-left">
                    <table class="movie-table">
                        <%
                            if (reviews != null && !reviews.isEmpty()) {
                                for (Review review : reviews) {
                        %>
                        <tr>
                            <td>
                                <img src="<%= review.getMovie().getImageUrl() %>" alt="Portada" class="thumbnail">
                            </td>
                            <td class="descripcion-column">
                                <strong><%= review.getMovie().getTitle() %></strong><br>
                                <div class="descripcion-box">
                                    <span><%=review.getDescription()%></span>
                                </div>
                            </td>
                            <td>
                                <%=review.getRating()%> ⭐
                            </td>
                            <td>
                                <div class="actions-container">
                                    <!--EDITAR REVIEW-------------------------------------------------->
                                    <a href="/editReview?id=<%=review.getId()%>" class="edit-button">✏️</a>

                                    <!--BORRAR REVIEW-------------------------------------------------->
                                    <form action="/deleteReview" method="post" onsubmit="return confirm('¿Estás seguro de que quieres borrar esta reseña?');">
                                        <input type="hidden" name="id" value="<%=review.getId()%>">
                                        <button type="submit" class="delete-button">🗑️</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                        <%
                            }
                        } else {
                        %>
                        <tr>
                            <td colspan="4">No tienes reseñas aún.</td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
