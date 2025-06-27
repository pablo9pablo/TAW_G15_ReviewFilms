
<!--OUAIL BOUAZZA MANSOURI : 100%-->


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ActorDTO" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroActor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Actores</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productionCompany.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/filtros.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<%
    List<ActorDTO> actores = (List<ActorDTO>) request.getAttribute("actors");
%>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp" />
    <div class="page-content">
        <h1 class="page-title">Gestión de Actores</h1>

        <form:form action="/actor/filtrar" method="post" modelAttribute="filtro" cssClass="filter-form">
            <label>Nombre:</label>
            <form:input path="nombre" placeholder="Buscar actor..." />
            <form:button class="button-generico">Buscar</form:button>
        </form:form>

        <div class="table-wrapper">
            <div class="table-header">
                <table class="movie-table">
                    <tr>
                        <th>Nombre</th>
                        <th>Género</th>
                        <th></th>
                    </tr>
                </table>
            </div>
            <div class="table-body-scroll">
                <table class="movie-table">
                    <%
                        if (actores != null && !actores.isEmpty()) {
                            for (ActorDTO actor : actores) {
                    %>
                    <tr>
                        <td><%= actor.getName() %></td>
                        <td><%= actor.getGender() != null ? actor.getGender() : "" %></td>
                        <td>
                            <div class="actions-container">
                                <a href="/actor/edit?id=<%= actor.getId() %>" class="edit-button" title="Editar">
                                    <i class="bi bi-pencil-fill"></i>
                                </a>
                                <form action="/actor/delete" method="post"
                                      onsubmit="return confirm('¿Estás seguro de que quieres eliminar este actor?');">
                                    <input type="hidden" name="id" value="<%= actor.getId() %>">
                                    <button type="submit" class="delete-button" title="Eliminar">
                                        <i class="bi bi-trash-fill"></i>
                                    </button>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="3">No se encontraron actores</td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>

        <a href="/actor/edit" class="button-generico" style="margin-left: 70px">
            <i class="bi bi-plus-circle"></i> Nuevo Actor
        </a>
    </div>
    <jsp:include page="footer.jsp" />
</div>
</body>
</html>
