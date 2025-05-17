<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieCastDTO" %>

<%
    Movie movie = (Movie) request.getAttribute("movie");
    boolean isEditing = movie.getId() != null;
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= isEditing ? "Editar Reparto - " + movie.getTitle() : "Añadir Reparto" %></title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>
<jsp:include page="logout.jsp"/>

<h2><%= isEditing ? "Editar Reparto - " + movie.getTitle() : "Añadir Reparto" %></h2>

<form:form modelAttribute="movieCastDTO" method="post" action="/saveCast">
    <form:hidden path="movieId"/>

    Actor:
    <form:select path="actorId" required="true">
        <form:option value="" label="-- Seleccionar Actor --"/>
        <form:options items="${actores}" itemValue="id" itemLabel="name"/>
    </form:select><br>

    Personaje:
    <form:input path="character"/><br>

    Orden de crédito:
    <form:input path="creditOrder" type="number" min="1"/><br>

    <input type="submit" value="Guardar">
</form:form>

<% if (isEditing) { %>
<h3>Reparto Actual</h3>
<table border="1">
    <tr>
        <th>Actor</th>
        <th>Personaje</th>
        <th>Orden</th>
        <th>Acciones</th>
    </tr>
    <% if (request.getAttribute("currentCast") != null) { %>
    <%
        List<MovieCastDTO> currentCast = (List<MovieCastDTO>) request.getAttribute("currentCast");
        for (MovieCastDTO cast : currentCast) {
    %>
    <tr>
        <td><%= cast.getActorName() %></td>
        <td><%= cast.getCharacter() %></td>
        <td><%= cast.getCreditOrder() %></td>
        <td>
            <a href="/cast/edit?movieId=<%= movie.getId() %>&actorId=<%= cast.getActorId() %>">Editar</a>
            <a href="/deleteCast?movieId=<%= movie.getId() %>&actorId=<%= cast.getActorId() %>">Eliminar</a>
        </td>
    </tr>
    <% } %>
    <% } %>
</table>
<% } %>

<br>
<a href="/editmovie?id=<%= movie.getId() != null ? movie.getId() : "" %>">Volver a Editar Película</a>

</body>
</html>