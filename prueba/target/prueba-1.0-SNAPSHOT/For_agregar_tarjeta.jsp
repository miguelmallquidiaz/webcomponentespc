<%-- 
    Document   : For_agregar_tarjeta
    Created on : 4 oct. 2023, 19:48:46
    Author     : Bryan
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.PreparedStatement, java.sql.SQLException"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Agregar</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="./plantillas/PlantillaNavbar.jsp" />
        <div class="container mt-5">
            <h1>Agregar nueva tarjeta grafica</h1>
            <form action="" method="post">
                <!-- comment 
                <div class="mb-3">
                    <label for="nombre" class="form-label">ID</label>
                    <input type="number" class="form-control" id="nombre" name="nombre" required>
                </div>-->
                <div class="mb-3">
                    <label for="mensaje" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="columna1" name="columna1" required>
                </div>
                <div class="mb-3">
                    <label for="mensaje" class="form-label">Cantidad</label>
                    <input type="text" class="form-control" id="columna2" name="columna2" required>
                </div>
                <div class="mb-3">
                    <label for="mensaje" class="form-label">imagen</label>
                    <input type="text" class="form-control" id="columna3" name="columna3" required>
                </div>
                <div class="mb-3">
                    <label for="mensaje" class="form-label">Precio</label>
                    <input type="text" class="form-control" id="columna4" name="columna4" required>
                </div>
                <button type="submit" class="btn btn-success">Agregar</button>
            </form>

            <!-- Agregar código de inserción aquí -->
            <%
                String url = "jdbc:mysql://localhost:3306/aplicativo_computer";
                String usuario = "root";
                String contraseña = "";

                if (request.getMethod().equalsIgnoreCase("POST")) {
                    String columna1 = request.getParameter("columna1"); // Nombre del campo en el formulario HTML
                    int columna2 = Integer.parseInt(request.getParameter("columna2")); // Nombre del campo en el formulario HTML
                    String columna3 = request.getParameter("columna3"); // Nombre del campo en el formulario HTML
                    double columna4 = Double.parseDouble(request.getParameter("columna4")); // Nombre del campo en el formulario HTML

                    String sentenciaSQL = "INSERT INTO tarjetagrafica (Nombre, Stock, Imagen, Precio) VALUES (?, ?, ?, ?)";

                    try (Connection conexion = DriverManager.getConnection(url, usuario, contraseña); PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSQL)) {

                        // Establece los valores de las columnas
                        preparedStatement.setString(1, columna1);
                        preparedStatement.setInt(2, columna2);
                        preparedStatement.setString(3, columna3);
                        preparedStatement.setDouble(4, columna4);

                        // Ejecuta la sentencia de inserción
                        int filasAfectadas = preparedStatement.executeUpdate();

                        if (filasAfectadas > 0) {
                            // Muestra una alerta con JavaScript
            %>
            <script>
                // Redirecciona a la página principal
                window.location.href = "crud_tarjeta.jsp"; // Reemplaza "crud_memoria.jsp" con la URL de tu página principal
            </script>
            <%
            } else {
                // Muestra una alerta con JavaScript
            %>
            <script>
                // Permanece en la misma página
            </script>
            <%
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
        </div>
        <jsp:include page="./plantillas/PlantillaFooter.jsp" />
    </body>
</html>
