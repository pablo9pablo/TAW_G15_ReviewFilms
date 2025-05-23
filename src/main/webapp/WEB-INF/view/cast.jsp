<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieCastDTO" %>

<%
    MovieDTO movie = (MovieDTO) request.getAttribute("movie");
    List<MovieCastDTO> castList = (List<MovieCastDTO>) request.getAttribute("cast");
%>

<table border="1" cellspacing="0" cellpadding="5" width="80%" align="center">
    <caption><h2>Cast of "<%= (movie != null && movie.getTitle() != null) ? movie.getTitle() : "-" %>"</h2></caption>
    <thead>
    <tr>
        <th align="left">Actor</th>
        <th align="left">Character</th>
        <th align="left">Credit Order</th>
    </tr>
    </thead>
    <tbody>
    <% for (MovieCastDTO cast : castList) { %>
    <tr>
        <td><%= (cast.getActorName() != null) ? cast.getActorName() : "-" %></td>
        <td><%= (cast.getCharacter() != null) ? cast.getCharacter() : "-" %></td>
        <td><%= (cast.getCreditOrder() != null) ? cast.getCreditOrder() : "-" %></td>
    </tr>
    <% } %>
    </tbody>
</table>
