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
            <h2 style="margin-left: 50px;">Total reviews: <%= request.getAttribute("reviewCount") %></h2>

            <div class="table-wrapper table-wrapper-scroll-left">
                <div class="content">
                    <table class="movie-table">
                        <%
                            if (reviews != null && !reviews.isEmpty()) {
                                for (Review review : reviews) {
                        %>
                        <tr>
                            <td>
                                <img src="<%= review.getMovie().getImageUrl() %>" alt="Portada" class="thumbnail">
                            </td>
                            <td>
                                <strong><%= review.getMovie().getTitle() %></strong><br>
                                <div class="descripcion-box">
                                    <span><%=review.getDescription()%></span>
                                </div>
                            </td>
                            <td>
                                <%= review.getRating() %> ⭐
                            </td>
                            <td>
                                <!--EDITAR REVIEW-------------------------------------------------->
                                <a href="/editReview?id=<%= review.getId() %>">✏️</a>

                                <!--BORRAR REVIEW-------------------------------------------------->
                                <form action="/deleteReview" method="post" onsubmit="return confirm('¿Estás seguro de que quieres borrar esta reseña?');">
                                    <input type="hidden" name="id" value="<%= review.getId() %>">
                                    <button type="submit" style="background: none; border: none; cursor: pointer;">🗑️</button>
                                </form>
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
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
