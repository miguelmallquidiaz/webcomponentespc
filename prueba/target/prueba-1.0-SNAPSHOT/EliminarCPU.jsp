<%-- 
    Document   : EliminarCPU
    Created on : 4 oct. 2023, 19:28:50
    Author     : Bryan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.SQLException"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Eliminar CPU</title>
</head>
<body>
    <%
        String url = "jdbc:mysql://localhost:33065/aplicativo_computer";
        String usuario = "root";
        String contraseña = "";

        int id = Integer.parseInt(request.getParameter("id"));

        String sentenciaSQL = "DELETE FROM cpu WHERE ID = ?";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSQL)) {

            // Establece el valor del parámetro
            preparedStatement.setInt(1, id);

            // Ejecuta la sentencia de eliminación
            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                out.println("Fila eliminada correctamente");
            } else {
                out.println("No se pudo eliminar la fila");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    %>
</body>
</html>

