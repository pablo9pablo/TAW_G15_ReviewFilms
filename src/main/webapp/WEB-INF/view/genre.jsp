<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.GenreDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Géneros</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productionCompany.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/logout.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/filtros.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<%
    List<GenreDTO> genres = (List<GenreDTO>) request.getAttribute("genres");
%>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp" />
    <div class="page-content">

        <h1 class="page-title">Gestión de Géneros</h1>

        <form:form action="${pageContext.request.contextPath}/genres/filtrar" method="post" modelAttribute="filtro" cssClass="filter-form">
            <label>Búsqueda por nombre:</label>
            <form:input path="nombre" placeholder="Buscar género..."/>
            <form:button class="button-generico">Buscar</form:button>
        </form:form>

        <div class="table-wrapper">
            <div class="table-header">
                <table class="movie-table">
                    <tr>
                        <th>Nombre del Género</th>
                        <th></th>
                    </tr>
                </table>
            </div>
            <div class="table-body-scroll">
                <table class="movie-table">
                    <%
                        if(genres != null && !genres.isEmpty()){
                            for(GenreDTO genre : genres){
                    %>
                    <tr>
                        <td><%= genre.getName() %></td>
                        <td>
                            <div class="actions-container">
                                <a href="/genres/edit?id=<%= genre.getId() %>" class="edit-button"><i class="bi bi-pencil-fill"></i></a>
                                <form action="/genres/delete" method="post" onsubmit="return confirm('¿Estás seguro de que quieres eliminar este género?');">
                                    <input type="hidden" name="id" value="<%= genre.getId() %>">
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
                        <td colspan="2">No se han encontrado géneros.</td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>
        <a href="/genres/edit" class="button-generico" style="margin-left: 70px">Añadir</a>
    </div>
    <jsp:include page="footer.jsp" />
</div>
</body>
</html>
