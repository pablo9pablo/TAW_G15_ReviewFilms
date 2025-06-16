<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Menú de Administración</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminMenu.css">
</head>
<body>
<jsp:include page="cabecera.jsp"/>
<jsp:include page="logout.jsp"/>
<h2 class="page-title">Menú de Administración - CRUDs</h2>
<ul class="actions-container">
    <li><a class="button-generico" href="${pageContext.request.contextPath}/actor/">Gestión de Actores</a></li>
    <li><a class="button-generico" href="${pageContext.request.contextPath}/crew/">Gestión de Crew</a></li>
    <li><a class="button-generico" href="${pageContext.request.contextPath}/genres/">Gestión de Géneros</a></li>
    <li><a class="button-generico" href="${pageContext.request.contextPath}/productionCompanies/">Gestión de Productores</a></li>
</ul>
</body>
</html>
