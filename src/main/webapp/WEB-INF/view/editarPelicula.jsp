<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Genre" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.ProductionCompany" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Actor" %>

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
    <form:input path="releaseDate"/><br>

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


    <br>Actores:<br>
    <a href="/cast?id=<%= (isEditing) ? movie.getId() : -1 %>"
       onclick="return confirm('¿Estás seguro de que quieres editar el reparto? ¡Perderás los cambios no guardados en la película!');">
        Editar Cast (Reparto)
    </a>


    <br>Sinopsis:<br>
    <form:textarea path="overview" rows="4" cols="50"/><br>

    URL de imagen:
    <form:input path="imageUrl"/><br><br>

    <input type="submit" value="Guardar Película">
</form:form>

</body>
</html>
