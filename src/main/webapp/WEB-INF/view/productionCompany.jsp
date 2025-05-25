<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ProductionCompanyDTO" %>

<%
    List<ProductionCompanyDTO> companies = (List<ProductionCompanyDTO>) request.getAttribute("companies");
    ProductionCompanyDTO companyForm = (ProductionCompanyDTO) request.getAttribute("companyForm");
    String formMode = (companyForm != null && companyForm.getId() != null) ? "Editar" : "Crear";
%>

<html>
<head><title>Productoras</title></head>
<body>
<br>
<h2><%= formMode %> Productora</h2>
<form:form modelAttribute="companyForm" action="/saveProductionCompany" method="post">
    <form:hidden path="id" />
    Nombre: <form:input path="name" /><br/>
    <input type="submit" value="Guardar" />
</form:form>
<br>

<h2>Lista de Productoras</h2>
<table border="1">
    <tr><th>ID</th><th>Nombre</th><th>Acciones</th></tr>
    <% for (ProductionCompanyDTO company : companies) { %>
    <tr>
        <td><%= company.getId() %></td>
        <td><%= company.getName() %></td>
        <td>
            <a href="editProductionCompany?id=<%= company.getId() %>">Editar</a> |
            <a href="deleteProductionCompany?id=<%= company.getId() %>">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>

<hr/>
</body>
</html>
