<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.CrewDTO" %>

<%
    MovieDTO movie = (MovieDTO) request.getAttribute("movie");
    List<CrewDTO> crewList = (List<CrewDTO>) request.getAttribute("crew");
%>

<table border="1" cellspacing="0" cellpadding="5" width="80%" align="center">
    <caption><h2>Crew of "<%= (movie != null && movie.getTitle() != null) ? movie.getTitle() : "-" %>"</h2></caption>
    <thead>
    <tr>
        <th align="left">Name</th>
        <th align="left">Genero</th>
    </tr>
    </thead>
    <tbody>
    <% for (CrewDTO crew : crewList) { %>
    <tr>
        <td><%= (crew.getName() != null) ? crew.getName() : "-" %></td>
        <td><%= (crew.getGender() != null) ? crew.getGender() : "-" %></td>
    </tr>
    <% } %>
    </tbody>
</table>