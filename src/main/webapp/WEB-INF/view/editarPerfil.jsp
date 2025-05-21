<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Configuración de perfil</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilosComunes.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/logout.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/footer.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/editarPerfil.css">

</head>

<%
    String error = (String) request.getAttribute("error");
%>

<body>
<div class="page-container">
    <jsp:include page="cabecera.jsp" />
    <div class="page-content">
        <h1>Configuración del Perfil</h1>

        <div class="form-container">
            <form:form modelAttribute="userDTO" method="post" action="${pageContext.request.contextPath}/actualizar">

                <%
                    if(error != null){
                %>
                <div class="alert"><%=error%></div>
                <%
                    }
                %>

                <div class="form-group">
                    <h3>Cambiar correo</h3>
                    <label>Correo Electrónico:</label>
                    <form:input path="email" cssClass="input-field" type="email" required="true" placeholder="Introduce tu nuevo correo" />
                </div>

                <h3>Cambiar Contraseña</h3>

                <div class="form-group">
                    <label>Contraseña Actual:</label>
                    <form:password path="passwordActual" cssClass="input-field" placeholder="Contraseña actual" />
                </div>

                <div class="form-group">
                    <label>Nueva Contraseña:</label>
                    <form:password path="nuevaPassword" cssClass="input-field" placeholder="Nueva contraseña" minlength="4" />
                </div>

                <div class="form-group">
                    <label>Confirmar Nueva Contraseña:</label>
                    <form:password path="confirmarPassword" cssClass="input-field" placeholder="Confirmar nueva contraseña" minlength="4" />
                </div>

                <div class="button-container">
                    <form:button class="button button-save">Guardar Cambios</form:button>
                    <a href="${pageContext.request.contextPath}/perfil" class="button button-cancel">Cancelar</a>
                </div>
            </form:form>
        </div>
    </div>
    <jsp:include page="footer.jsp" />
</div>
</body>
</html>