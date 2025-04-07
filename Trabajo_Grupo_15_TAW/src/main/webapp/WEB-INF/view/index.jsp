<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.MovieEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ReviewFilms</title>
</head>
<%
    List<MovieEntity> lista = (List<MovieEntity>) request.getAttribute("lista");
%>
<body>
<h1>Esta página es de prueba</h1>

<table border="1">

    <tr>
        <th>NOMBRE</th>
    </tr>
<%
    for (MovieEntity movie: lista) {
%>
<tr>
    <td><%= movie.getOriginalTitle() %></td>

</tr>
<%
    }
%>

</table>
</body>
</html>
