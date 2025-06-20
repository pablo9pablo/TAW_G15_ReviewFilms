<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.GenreDTO" %>

<%
    List<GenreDTO> genres = (List<GenreDTO>) request.getAttribute("genres");
%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/logout.css">
    </head>
    <body>
        <jsp:include page="cabecera.jsp"/>
        <jsp:include page="logout.jsp"/>
        <main>
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
        </main>
        <footer>
            <jsp:include page="footer.jsp"/>
            <script src="/js/VerPeliculaScript.js"></script>
        </footer>
    </body>
</html>

