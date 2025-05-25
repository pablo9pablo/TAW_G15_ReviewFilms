<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.GenreDTO" %>

<%
    List<GenreDTO> genres = (List<GenreDTO>) request.getAttribute("genres");
%>

<table border="1" cellspacing="0" cellpadding="5" width="60%" align="center">
    <caption><h2>Lista de Generos</h2></caption>
    <thead>
    <tr>
        <th align="left">Nombre</th>
    </tr>
    </thead>
    <tbody>
    <% for (GenreDTO genre : genres) { %>
    <tr>
        <td><%= (genre.getName() != null) ? genre.getName() : "-" %></td>
    </tr>
    <% } %>
    </tbody>
</table>
