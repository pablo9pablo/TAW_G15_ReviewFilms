<%@ page import="java.util.List" %>
<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.ProductionCompanyDTO" %>

<%
    List<ProductionCompanyDTO> companies = (List<ProductionCompanyDTO>) request.getAttribute("companies");
%>

<table border="1" cellspacing="0" cellpadding="5" width="60%" align="center">
    <caption><h2>Lista de Productoras</h2></caption>
    <thead>
    <tr>
        <th align="left">Nombre</th>
    </tr>
    </thead>
    <tbody>
    <% for (ProductionCompanyDTO company : companies) { %>
    <tr>
        <td><%= (company.getName() != null) ? company.getName() : "-" %></td>
    </tr>
    <% } %>
    </tbody>
</table>
