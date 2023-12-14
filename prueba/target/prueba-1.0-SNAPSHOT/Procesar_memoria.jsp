
<%-- 
    Document   : ProcesarActualizacion
    Created on : 4 oct. 2023, 00:51:47
    Author     : Bryan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.SQLException"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Procesar Actualización</title>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <h1>Procesando Actualización</h1>

        <%
            // Obtener los datos del formulario
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            String imagen = request.getParameter("imagen");
            double precio = Double.parseDouble(request.getParameter("precio"));

            // Establecer la conexión a la base de datos
            String url = "jdbc:mysql://localhost:3306/aplicativo_computer";
            String usuario = "root";
            String contraseña = "";

            String sentenciaSQL = "UPDATE memoria SET Nombre = ?, Stock = ?, Imagen = ?, Precio = ? WHERE ID = ?";

            try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSQL)) {
                preparedStatement.setString(1, nombre);
                preparedStatement.setInt(2, cantidad);
                preparedStatement.setString(3, imagen);
                preparedStatement.setDouble(4, precio);
                preparedStatement.setInt(5, id);

                int filasAfectadas = preparedStatement.executeUpdate();

                if (filasAfectadas > 0) {
        %>
        <p>Producto actualizado exitosamente.</p>
        <p><a href="crud_memoria.jsp">Volver a la lista de productos</a></p>
        <%
                } else {
        %>
        <p>Error al actualizar el producto.</p>
        <p><a href="crud_memoria.jsp">Volver a la lista de productos</a></p>
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
        %>
        <p>Error al actualizar el producto.</p>
        <p><a href="crud_memoria.jsp">Volver a la lista de productos</a></p>
        <%
            }
        %>
    </div>
</body>
</html>

