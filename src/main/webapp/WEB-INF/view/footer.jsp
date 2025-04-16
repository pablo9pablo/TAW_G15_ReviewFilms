
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %><html>
<head>
  <style>
    footer {
      background-color: #2c3e50;
      color: white;
      padding: 20px 0;
      text-align: center;
      width: 100%;
    }

    footer a {
      color: #ccc;
      text-decoration: none;
      margin: 0 10px;
    }

    footer a:hover {
      color: white;
    }
  </style>
</head>
<body>

<footer>
  <div>
    <p>&copy; 2025 ReviewFilms. Todos los derechos reservados.</p>
    <p>
      <a href="/about">Sobre nosotros</a> |
      <a href="/contact">Contacto</a> |
      <a href="/terms">Términos de uso</a>
    </p>
    Fecha de entrada al sistema: <%= new Date(session.getCreationTime()) %>

  </div>
</footer>
</body>
</html>
