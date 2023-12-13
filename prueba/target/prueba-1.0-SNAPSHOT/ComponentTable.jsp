<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="dao.Negocio,modelo.Articulos, java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla de componentes</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/style_1.css">
    </head>
    <body>
        <jsp:include page="/template/Navbar.jsp"/>
        <%
            List<Articulos> lista = (ArrayList<Articulos>) request.getAttribute("dato");
            String tipo = (String) request.getAttribute("tipo");
        %>
        <div class="text-center">
            <h1 class="titulo_letter">Tabla de Tipo <%=tipo%></h1> 
        </div>

        <div class="container">
            <table class="container table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Imagen</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Agregar</th>
                    </tr>
                </thead>
                    <tbody>
                    <% for (Articulos x : lista) {%>
                        <tr>
                            <td><%=x.getId()%></td>
                            <td id="nombre"><%=x.getNombre()%></td>
                            <td><img id="imagen" class="img-fluid img-height" alt="Descripción de la imagen" src="<%=x.getImagen()%>"></td>
                            <td id="precio"><%=x.getPrecio()%></td>
                            <td id="stock"><%=x.getStock()%></td>
                            <td>
                                <a href="controlUsuario?opc=4&tipo=<%=tipo%>&id=<%=x.getId()%>&nombre=<%=x.getNombre()%>">Agregar</a>
                            </td>
                        </tr>
                    <% }%>
                </tbody>
            </table>
        </div>
    </body>
</html>
