<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
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

