<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout</title>
    <link rel="stylesheet" type="text/css" href="/css/logout.css">
    <script type="text/javascript">
        function confirmarLogout() {
            return confirm('¿Estás seguro de que quieres cerrar sesión?');
        }
    </script>
</head>
<body>

<form action="/logout" method="post" onsubmit="return confirmarLogout();">
    <button type="submit" class="logout-button">Cerrar sesión</button>
</form>

</body>
</html>
