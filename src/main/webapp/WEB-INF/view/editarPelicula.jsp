<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="java.util.List" %>

<%
    boolean isEditing = true;
    Movie movie = (Movie) request.getAttribute("movie");
    if(movie.getId() == null) isEditing = false;
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= isEditing ? "Editar Película" : "Crear Película" %></title>
</head>
<body>

<jsp:include page="cabecera.jsp"/>
<jsp:include page="logout.jsp"/>

<h2><%= isEditing ? "Editar " + movie.getTitle() : "Crear Película" %></h2>

<form:form modelAttribute="MovieDTO" method="post" action="/savemovie">

    <form:hidden path="id"/>

    Título:
    <form:input path="title" required="true"/><br>

    Título original:
    <form:input path="originalTitle" required="true"/><br>

    Fecha de estreno:
    <form:input path="releaseDate" type="date"/><br>

    Duración (min):
    <form:input path="runtime" type="number"/><br>

    Presupuesto:
    <form:input path="budget" type="number" step="0.01"/><br>

    Ingresos:
    <form:input path="revenue" type="number" step="0.01"/><br>

    Idioma original:
    <form:input path="originalLanguage"/><br>

    <br><br>Género:<br>
    <form:checkboxes path="genreIds" items="${genre}" itemLabel="name"/><br>

    <br>Production company:<br>
    <form:checkboxes path="productionCompanyIds" items="${pcompany}" itemLabel="name"/><br>

    <br>Crew (Equipo técnico):<br>
    <form:checkboxes path="crewIds" items="${crew}" itemLabel="name"/><br>

    <br>Sinopsis:<br>
    <form:textarea path="overview" rows="4" cols="50"/><br>

    URL de imagen:
    <form:input path="imageUrl"/><br><br>

    <!-- Botones -->
    <button type="submit" name="action" value="save">💾 Guardar</button>
    <button type="submit" name="action" value="save_and_cast">🎭 Guardar y ver reparto</button>

</form:form>

</body>
</html>
