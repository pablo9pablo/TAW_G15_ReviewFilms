<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO" %>

<%
    List<CrewDTO> crewList = (List<CrewDTO>) request.getAttribute("crewList");
    CrewDTO crewForm = (CrewDTO) request.getAttribute("crewForm");
    String formMode = (crewForm != null && crewForm.getId() != null) ? "Editar" : "Crear";
%>

<html>
<head><title>Equipo Técnico (Crew)</title></head>
<body>
<br>
<h2><%= formMode %> Miembro del Equipo Técnico</h2>
<form:form modelAttribute="crewForm" action="/saveCrew" method="post">
    <form:hidden path="id" />
    Nombre: <form:input path="name" /><br/>
    Género: <form:input path="gender" /><br/>
    <input type="submit" value="Guardar" />
</form:form>
<br>

<h2>Lista del Equipo Técnico</h2>
<table border="1">
    <tr><th>ID</th><th>Nombre</th><th>Género</th><th>Acciones</th></tr>
    <% for (CrewDTO crew : crewList) { %>
    <tr>
        <td><%= crew.getId() %></td>
        <td><%= crew.getName() %></td>
        <td><%= crew.getGender() != null ? crew.getGender() : "" %></td>
        <td>
            <a href="editCrew?id=<%= crew.getId() %>">Editar</a> |
            <a href="deleteCrew?id=<%= crew.getId() %>">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>

<hr/>
</body>
</html>
