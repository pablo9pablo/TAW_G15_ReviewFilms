<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.GenreDTO" %>

<%
    List<GenreDTO> genres = (List<GenreDTO>) request.getAttribute("genres");
    GenreDTO genreForm = (GenreDTO) request.getAttribute("genreForm");
    String formMode = (genreForm != null && genreForm.getId() != null) ? "Editar" : "Crear";
%>

<html>
<head><title>Géneros</title></head>
<body>
<br>
<h2><%= formMode %> Género</h2>
<form:form modelAttribute="genreForm" action="/saveGenre" method="post">
    <form:hidden path="id" />
    Nombre: <form:input path="name" /><br/>
    <input type="submit" value="Guardar" />
</form:form>
<br>

<h2>Lista de Géneros</h2>
<table border="1">
    <tr><th>ID</th><th>Nombre</th><th>Acciones</th></tr>
    <% for (GenreDTO genre : genres) { %>
    <tr>
        <td><%= genre.getId() %></td>
        <td><%= genre.getName() %></td>
        <td>
            <a href="editGenre?id=<%= genre.getId() %>">Editar</a> |
            <a href="deleteGenre?id=<%= genre.getId() %>">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>

<hr/>
</body>
</html>
