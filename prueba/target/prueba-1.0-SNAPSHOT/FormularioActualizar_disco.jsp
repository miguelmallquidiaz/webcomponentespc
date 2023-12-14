<%-- 
    Document   : FormularioActualizar_disco
    Created on : 4 oct. 2023, 21:39:56
    Author     : Bryan
--%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Actualizar</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <div class="container mt-5">
            <h1>Actualizar</h1>
            <form action="Procesar_disco.jsp" method="post">
                <div class="mb-3">
                    <label for="id" class="form-label">ID</label>
                    <input type="number" class="form-control" id="id" name="id" readonly>
                </div>
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" required>
                </div>
                <div class="mb-3">
                    <label for="cantidad" class="form-label">Cantidad</label>
                    <input type="number" class="form-control" id="cantidad" name="cantidad" required>
                </div>
                <div class="mb-3">
                    <label for="imagen" class="form-label">Imagen</label>
                    <input type="text" class="form-control" id="imagen" name="imagen" required>
                </div>
                <div class="mb-3">
                    <label for="precio" class="form-label">Precio</label>
                    <input type="number" class="form-control" id="precio" name="precio" required>
                </div>
                <button type="submit" class="btn btn-success">Actualizar</button>
            </form>

            <%
                // Obtener el ID del producto que se desea actualizar
                int idProducto = Integer.parseInt(request.getParameter("id"));

                // Realizar una consulta para obtener los datos del producto
                String url = "jdbc:mysql://localhost:3306/aplicativo_computer";
                String usuario = "root";
                String contraseña = "";
                String sentenciaSQL = "SELECT Nombre, Stock, Imagen, Precio FROM discoduro WHERE ID = ?";

                try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSQL)) {
                    preparedStatement.setInt(1, idProducto);
                    ResultSet rs = preparedStatement.executeQuery();

                    if (rs.next()) {
                        // Obtener los valores de las columnas y mostrarlos en los campos del formulario
                        String nombre = rs.getString("Nombre");
                        int cantidad = rs.getInt("Stock");
                        String imagen = rs.getString("Imagen");
                        double precio = rs.getDouble("Precio");

                        // Asignar los valores a los campos del formulario
            %>
            <script>
                document.getElementById("id").value = <%= idProducto%>;
                document.getElementById("nombre").value = "<%= nombre%>";
                document.getElementById("cantidad").value = <%= cantidad%>;
                document.getElementById("imagen").value = "<%= imagen%>";
                document.getElementById("precio").value = <%= precio%>;
            </script>
            <%
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>
        </div>
    </body>
</html>
