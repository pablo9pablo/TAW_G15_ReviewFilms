<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
html, body {
height: 100%;
margin: 0;
}

body {
display: flex;
flex-direction: column;
}

nav {
background-color: #2c3e50;
}

nav table {
width: 100%;
text-align: center;
}

nav td {
padding: 15px 20px;
}

nav a {
text-decoration: none;
color: #ecf0f1;
font-weight: bold;
font-size: 16px;
padding: 10px 15px;
border-radius: 8px;
transition: background-color 0.3s;
display: inline-block;
}

nav a:hover {
background-color: #7790ad;
}
</style>

<html>
    <nav>
        <table>
            <tr>
                <td><a href="/">Home</a></td>
                <td><a href="/watched">Watched</a></td>
                <td><a href="/reviews">Reviews</a></td>
                <td><a href="/waiting">Waiting to See</a></td>
                <td><a href="/favourites">Favourites</a></td>
            </tr>
        </table>
    </nav>
</html>

