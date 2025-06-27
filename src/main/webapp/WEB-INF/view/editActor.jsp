<!--OUAIL BOUAZZA MANSOURI : 100%-->


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ActorDTO" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.MovieDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tablasComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/editProductionCompany.css">
</head>
<%
    String error = (String) request.getAttribute("error");
    ActorDTO dto = (ActorDTO) request.getAttribute("actorForm");
    List<MovieDTO> relatedMovies = (List<MovieDTO>) request.getAttribute("relatedMovies");
    List<MovieDTO> unrelatedMovies = (List<MovieDTO>) request.getAttribute("unrelatedMovies");
%>
<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp"/>
    <div class="page-content">
        <h2><%= dto.getId() == null ? "Crear nuevo Actor" : "Editar " + dto.getName()%></h2>

        <%
            if (error != null) {
        %>
        <div class="alert"><%=error%></div>
        <%
            }
        %>

        <form:form method="post" action="/actor/save" modelAttribute="actorForm">
            <form:hidden path="id"/>
            <label class="nombreProductora">Nombre:</label>
            <form:input path="name" required="true"/>

            <label class="nombreProductora">Género:</label>
            <form:input path="gender"/>

            <form:button class="button-generico">Guardar</form:button>
        </form:form>

        <%
            if (dto.getId() != null) {
        %>
        <h3>Relaciones con Películas:</h3>

        <div class="table-wrapper">
            <div class="table-header">
                <table class="movie-table">
                    <tr>
                        <th>Películas asociadas</th>
                        <th></th>
                    </tr>
                </table>
            </div>
            <div class="table-body-scroll">
                <table class="movie-table">
                    <%
                        if (relatedMovies.isEmpty()) {
                    %>
                    <tr>
                        <td aria-colspan="2">No hay películas asociadas aún.</td>
                    </tr>
                    <%
                    } else {
                        for (MovieDTO relatedMovie : relatedMovies) {
                    %>
                    <tr>
                        <td><%=relatedMovie.getTitle()%></td>
                        <td>
                            <a href="/actor/removeMovie?actorId=<%=dto.getId()%>&movieId=<%=relatedMovie.getId()%>">Quitar</a>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </div>
        </div>
        <div class="table-wrapper">
            <div class="table-header">
                <table class="movie-table">
                    <tr>
                        <th>Películas disponibles para asociar</th>
                    </tr>
                </table>
            </div>
            <div>
                <form:form modelAttribute="actorForm" method="post" action="/actor/associateMovies">
                    <form:hidden path="id" />

                    <table class="movie-table">
                        <tr>
                            <td>
                                <form:select path="selectedMovieIds" multiple="true" cssStyle="width:100%; min-height:200px;">
                                    <form:options items="${unrelatedMovies}" itemValue="id" itemLabel="title"/>
                                </form:select>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align: right; padding-top: 10px;">
                                <form:button class="button-generico">Añadir seleccionadas</form:button>
                            </td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
        <%
            }
        %>
        <a href="/actor/" class="button-generico">Volver al listado de actores</a><br/>
    </div>
    <jsp:include page="footer.jsp"/>
</div>
</body>
</html>