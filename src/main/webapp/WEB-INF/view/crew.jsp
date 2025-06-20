<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.ui.FiltroCrew" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Crew</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/productionCompany.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/logout.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/filtros.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
</head>
<%
    List<CrewDTO> crewList = (List<CrewDTO>) request.getAttribute("crewList");
    FiltroCrew filtro = (FiltroCrew) request.getAttribute("filtro");
%>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp" />
    <div class="page-content">

        <h1 class="page-title">Gestión de Crew</h1>

        <form:form action="${pageContext.request.contextPath}/crew/filtrar" method="post" modelAttribute="filtro" cssClass="filter-form">
            <label>Búsqueda por nombre:</label>
            <form:input path="nombre" placeholder="Buscar crew..."/>
            <form:button class="button-generico">Buscar</form:button>
        </form:form>

        <div class="table-wrapper">
            <div class="table-header">
                <table class="movie-table">
                    <tr>
                        <th>Nombre del Miembro</th>
                        <th>Género</th>
                        <th></th>
                    </tr>
                </table>
            </div>
            <div class="table-body-scroll">
                <table class="movie-table">
                    <%
                        if(crewList != null && !crewList.isEmpty()){
                            for(CrewDTO crew : crewList){
                    %>
                    <tr>
                        <td><%= crew.getName() %></td>
                        <td><%= crew.getGender() != null ? crew.getGender() : "" %></td>
                        <td>
                            <div class="actions-container">
                                <a href="${pageContext.request.contextPath}/crew/edit?id=<%= crew.getId() %>" class="edit-button"><i class="bi bi-pencil-fill"></i></a>
                                <form action="${pageContext.request.contextPath}/crew/delete" method="post" onsubmit="return confirm('¿Estás seguro de que quieres eliminar este miembro?');" style="display:inline;">
                                    <input type="hidden" name="id" value="<%= crew.getId() %>"/>
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
                        <td colspan="3">No se han encontrado miembros del crew.</td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </div>
        </div>

        <a href="${pageContext.request.contextPath}/crew/edit" class="button-generico" style="margin-left: 70px">Añadir</a>

    </div>
    <jsp:include page="footer.jsp" />
</div>
</body>
</html>