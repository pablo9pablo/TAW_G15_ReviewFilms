<%@ page import="es.uma.demoservice2025.trabajo_grupo_15_taw.dto.RegisterDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
</head>
<body>
<h2>Register</h2>

<% if (request.getAttribute("error") != null) { %>
<div class="error"><%= request.getAttribute("error") %></div>
<% } %>

<%
    org.springframework.validation.BindingResult bindingResult = (org.springframework.validation.BindingResult)
            request.getAttribute("org.springframework.validation.BindingResult.registerDTO");
    if (bindingResult != null && bindingResult.hasErrors()) {
%>
<div class="error">
    <ul>
        <% for (org.springframework.validation.ObjectError error : bindingResult.getAllErrors()) { %>
        <li><%= error.getDefaultMessage() %></li>
        <% } %>
    </ul>
</div>
<%
    }
%>

<% if (request.getAttribute("success") != null) { %>
<div class="success"><%= request.getAttribute("success") %></div>
<% } %>

<form method="post" action="/register">
    <label>Email:
        <input type="email" name="email" value="<%=
            request.getAttribute("registerDTO") != null &&
            ((RegisterDTO)request.getAttribute("registerDTO")).getEmail() != null ?
            ((RegisterDTO)request.getAttribute("registerDTO")).getEmail() : ""
        %>" required />
    </label>
    <label>Contraseña:
        <input type="password" name="password" value="<%=
            request.getAttribute("registerDTO") != null &&
            ((RegisterDTO)request.getAttribute("registerDTO")).getPassword() != null ?
            ((RegisterDTO)request.getAttribute("registerDTO")).getPassword() : ""
        %>" required />
    </label>
    <button type="submit">Registrar</button>
</form>

</body>
</html>
