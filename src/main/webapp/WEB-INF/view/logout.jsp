<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout</title>
    <!-- Vinculamos el archivo CSS externo -->
    <link rel="stylesheet" type="text/css" href="/css/logout.css">
</head>
<body>

<!-- Verificación de cierre de sesión -->
<%
    String logout = request.getParameter("logout");
    if ("true".equals(logout)) {
%>

<%
    }
%>



<!-- Formulario de cierre de sesión -->
<form action="<%= request.getContextPath() + "/logout" %>" method="post">
    <button type="submit" class="logout-button">Cerrar sesión</button>
</form>

</body>
</html>
