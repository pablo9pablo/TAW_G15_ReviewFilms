<!--OUAIL BOUAZZA MANSOURI : 100%-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.entity.Movie" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO" %>

<%
    boolean isEditing = true;
    Movie movie = (Movie) request.getAttribute("movie");
    MovieDTO dto = (MovieDTO) request.getAttribute("movieDTO");

    if(movie.getId() == null) isEditing = false;
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= isEditing ? "Editar Película" : "Crear Película" %></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/EditMovie.css">
</head>
<body class="pagina-formulario">

<jsp:include page="cabecera.jsp"/>

<h2 class="titulo-formulario"><%= isEditing ? "Editar " + movie.getTitle() : "Crear Película" %></h2>

<form:form modelAttribute="MovieDTO" method="post" action="/savemovie" class="formulario-pelicula">

    <form:hidden path="id"/>

    <label class="etiqueta">Título:</label>
    <form:input path="title" required="true" cssClass="campo-texto"/><br>

    <label class="etiqueta">Título original:</label>
    <form:input path="originalTitle" required="true" cssClass="campo-texto"/><br>


    <label class="etiqueta">Fecha de estreno:</label>
    <form:input path="releaseDate" type="date" cssClass="campo-texto"/><br>

    <label class="etiqueta">Duración (min):</label>
    <form:input path="runtime" type="number" cssClass="campo-texto"/><br>

    <label class="etiqueta">Presupuesto:</label>
    <form:input path="budget" type="number" step="0.01" cssClass="campo-texto"/><br>

    <label class="etiqueta">Ingresos:</label>
    <form:input path="revenue" type="number" step="0.01" cssClass="campo-texto"/><br>

    <label class="etiqueta">Idioma original:</label>
    <form:input path="originalLanguage" cssClass="campo-texto"/><br>

    <br><label class="etiqueta">Género:</label><br>
    <form:checkboxes path="genreIds" items="${genre}" itemLabel="name" cssClass="check-grupo"/><br>

    <br><label class="etiqueta">Compañía productora:</label><br>
    <form:checkboxes path="productionCompanyIds" items="${pcompany}" itemLabel="name" cssClass="check-grupo"/><br>

    <br><label class="etiqueta">Equipo técnico:</label><br>
    <form:checkboxes path="crewIds" items="${crew}" itemLabel="name" cssClass="check-grupo"/><br>

    <br><label class="etiqueta">Sinopsis:</label><br>
    <form:textarea path="overview" rows="4" cols="50" cssClass="campo-area"/><br>

    <label class="etiqueta">URL de imagen:</label>
    <form:input path="imageUrl" cssClass="campo-texto"/><br><br>

    <!-- Botones -->
    <button type="submit" name="action" value="save" class="boton-principal">💾 Guardar</button>
    <button type="submit" name="action" value="save_and_cast" class="boton-principal">🎭 Guardar y ver reparto</button>

</form:form>

</body>
</html>
