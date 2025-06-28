<!--OUAIL BOUAZZA MANSOURI : 100%-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Editar Reparto</title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>

<h2>Editar Reparto</h2>

<form:form modelAttribute="movieCastDTO" method="post" action="/saveCast">
    <form:hidden path="movieId"/>
    <form:hidden path="actorId"/>

    Personaje:
    <form:input path="character" required="true"/><br>

    Orden de crédito:
    <form:input path="creditOrder" type="number" min="1" required="true"/><br>

    <input type="submit" value="Guardar Cambios">
</form:form>

<br>
<a href="/cast?id=${movieCastDTO.movieId}">Volver al reparto</a>

</body>
</html>
