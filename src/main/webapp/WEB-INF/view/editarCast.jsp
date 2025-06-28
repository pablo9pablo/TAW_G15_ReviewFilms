<!--OUAIL BOUAZZA MANSOURI : 100%-->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieCastDTO" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor" %>

<%
    Movie movie = (Movie) request.getAttribute("movie");
    boolean isEditing = movie != null && movie.getId() != null && movie.getId() != -1;
    List<MovieCastDTO> currentCast = (List<MovieCastDTO>) request.getAttribute("currentCast");
    List<Actor> actores = (List<Actor>) request.getAttribute("actores");
    Long movieId = (isEditing && movie != null) ? movie.getId() : -1L;
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= isEditing ? "Editar Reparto - " + movie.getTitle() : "Añadir Reparto" %></title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>

<h2><%= isEditing ? "Editar Reparto - " + movie.getTitle() : "Añadir Reparto" %></h2>

<form:form modelAttribute="movieCastDTO" method="post" action="/saveCast">
    <form:hidden path="movieId"/>

    Actor:
    <form:select path="actorId" required="true">
        <form:option value="" label="-- Seleccionar Actor --"/>
        <% for (Actor actor : actores) { %>
        <form:option value="<%= actor.getId() %>" label="<%= actor.getName() %>"/>
        <% } %>
    </form:select><br>

    Personaje:
    <form:input path="character"/><br>

    Orden de crédito:
    <form:input path="creditOrder" type="number" min="1"/><br>

    <input type="submit" value="Guardar">
</form:form>

<h3>Reparto <%= isEditing ? "Actual" : "Temporal" %></h3>
<table border="1" width="100%" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th width="30%">Actor</th>
        <th width="30%">Personaje</th>
        <th width="15%">Orden</th>
        <th width="25%">Acciones</th>
    </tr>
    </thead>
    <tbody>
    <% if (currentCast != null && !currentCast.isEmpty()) {
        for (MovieCastDTO cast : currentCast) { %>
    <tr>
        <td><%= cast.getActorName() != null ? cast.getActorName() : "Desconocido" %></td>
        <td><%= cast.getCharacter() != null ? cast.getCharacter() : "-" %></td>
        <td><%= cast.getCreditOrder() != null ? cast.getCreditOrder() : "-" %></td>
        <td>
            <a href="/cast/edit?movieId=<%= movieId %>&actorId=<%= cast.getActorId() %>">Editar</a>
            &nbsp;|&nbsp;
            <a href="/deleteCast?movieId=<%= movieId %>&actorId=<%= cast.getActorId() %>"
               onclick="return confirm('¿Seguro que deseas eliminar este actor del reparto?');">
                Eliminar
            </a>
        </td>
    </tr>
    <% }
    } else { %>
    <tr><td colspan="4">No hay miembros en el reparto.</td></tr>
    <% } %>
    </tbody>
</table>

<br>
<a href="/editmovie?id=<%= isEditing ? movie.getId() : "" %>">Volver a Editar Película</a>

</body>
</html>
