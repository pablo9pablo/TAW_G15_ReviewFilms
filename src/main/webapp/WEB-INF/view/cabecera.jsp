<!-- PABLO MARTINEZ PALOP : 10% -->
<!--LUCIA ROSALES SANTIAGO: 70% -->
<!-- MANUEL GALÁN ALFARO: 20% -->

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cabecera.css">
    <script type="text/javascript">
        function confirmarLogout() {
            return confirm('¿Estás seguro de que quieres cerrar sesión?');
        }
    </script>
</head>
<nav>
    <table>
        <tr>
            <td><a href="/">REVIEWFILMS</a></td>
            <td><a href="/watched">Watched</a></td>
            <td><a href="/reviews">Reviews</a></td>
            <td><a href="/waiting">Waiting to See</a></td>
            <td><a href="/favourites">Favourites</a></td>
            <td><a href="/perfil">Mi Perfil</a></td>
            <td>
                <a href="/logout" onclick="return confirmarLogout();">Cerrar sesión</a>
            </td>
        </tr>
    </table>
</nav>

</html>