<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ActorDTO" %>

<%
    List<ActorDTO> actores = (List<ActorDTO>) request.getAttribute("actors");
    ActorDTO actorForm = (ActorDTO) request.getAttribute("actorForm");
    String formMode = (actorForm != null && actorForm.getId() != null) ? "Editar" : "Crear";
%>

<html>
<head><title>Actores</title></head>
<body>
<br>
<h2><%= formMode %> Actor</h2>
<form:form modelAttribute="actorForm" action="/actor/saveActor" method="post">
    <form:hidden path="id" />
    Nombre: <form:input path="name" /><br/>
    Género: <form:input path="gender" /><br/>
    <input type="submit" value="Guardar" />
</form:form>
<br>
<h2>Lista de Actores</h2>

<table border="1">
    <tr>
        <th>ID</th><th>Nombre</th><th>Género</th><th>Acciones</th>
    </tr>
    <% for (ActorDTO actor : actores) { %>
    <tr>
        <td><%= actor.getId() %></td>
        <td><%= actor.getName() %></td>
        <td><%= actor.getGender() %></td>
        <td>
            <a href="/actor/editActor?id=<%= actor.getId() %>">Editar</a> |
            <a href="/actor/deleteActor?id=<%= actor.getId() %>">Eliminar</a>

        </td>
    </tr>
    <% } %>
</table>

<hr/>


</body>
</html>
