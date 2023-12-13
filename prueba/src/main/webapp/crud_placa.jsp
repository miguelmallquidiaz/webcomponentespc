<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PCGAmer</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/style.css">
    </head>
    <style>
        .titulo_letter{
            align-items: center;
            padding: 2%;
        }
    </style>
    <body class="body">
        <jsp:include page="./plantillas/PlantillaNavbar.jsp" />
        <div class="text-center">
            <h1 class="titulo_letter">Tabla de Placas</h1> 
        </div>


        <%-- Establecer la información de conexión a la base de datos --%>
        <%@ page import="java.sql.Connection"%>
        <%@ page import="java.sql.DriverManager"%>
        <%@ page import="java.sql.ResultSet"%>
        <%@ page import="java.sql.SQLException"%>
        <%@ page import="java.sql.Statement"%>

        <%
            String url = "jdbc:mysql://localhost:3306/aplicativo_computer"; // Cambia la URL y el puerto según tu configuración
            String user = "root"; // Cambia el nombre de usuario
            String password = ""; // Cambia la contraseña si es necesario

            try {
                // Cargar el controlador JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establecer la conexión
                Connection conn = DriverManager.getConnection(url, user, password);

                // Crear una declaración SQL
                Statement stmt = conn.createStatement();

                // Ejecutar una consulta SQL para obtener los datos de la tabla "vehiculo"
                ResultSet rs = stmt.executeQuery("SELECT * FROM placa");
        %>
        <div class="container">
            <button type="button" id="redireccionar" class="btn btn-primary">Agregar</button>
        </div>

        <div class="container">
            <table class="container table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Stock</th>
                        <th>Imagen</th>
                        <th>Precio</th>
                        <th>Actualizar</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        int rowCounter = 1;
                        while (rs.next()) {
                    %>
                    <tr>
                        <td><%= rs.getString("ID")%></td>
                        <td><%= rs.getString("Nombre")%></td>
                        <td><%= rs.getString("Stock")%></td>                
                        <td><img id="imagen" class="img-fluid img-height" alt="Descripción de la imagen" src="<%= rs.getString("Imagen")%>"></td>
                        <td><%= rs.getString("Precio")%></td>
                        <td>
                            <button type="button" class="btn btn-success actualizar-btn" data-row="<%= rs.getString("ID")%>">Actualizar</button>
                        </td>
                        <td>
                            <button type="button" class="btn btn-danger eliminar-btn" data-row="<%= rs.getString("ID")%>">Eliminar</button>
                        </td>
                    </tr>
                    <%
                                rowCounter++;
                            }
                        } catch (ClassNotFoundException | SQLException e) {
                            out.println("Error al conectar a la base de datos: " + e.getMessage());
                        }
                    %>
                </tbody>
            </table>
        </div>
        <script>
            // Función para redireccionar al hacer clic en el botón "Agregar"
            document.getElementById("redireccionar").addEventListener("click", function () {
                window.location.href = "For_agregar_placa.jsp"; // Reemplaza con la URL de tu página de formulario de agregado
            });

            // Función para manejar los clics en los botones de "Actualizar"
            const actualizarButtons = document.querySelectorAll(".actualizar-btn");
            actualizarButtons.forEach(function (button) {
                button.addEventListener("click", function () {
                    const rowID = this.getAttribute("data-row");
                    // Aquí puedes redirigir a una página de actualización con el ID de la fila
                    window.location.href = "FormularioActualizar.jsp?id=" + rowID; // Reemplaza con tu lógica específica
                });
            });
        </script>

        <script>
            // Función para manejar los clics en los botones de "Eliminar"
            const eliminarButtons = document.querySelectorAll(".eliminar-btn");
            eliminarButtons.forEach(function (button) {
                button.addEventListener("click", function () {
                    const rowID = this.getAttribute("data-row");

                    // Realiza una solicitud AJAX para eliminar la fila
                    const xhr = new XMLHttpRequest();
                    xhr.open("POST", "EliminarPlaca.jsp", true);
                    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhr.onreadystatechange = function () {
                        if (xhr.readyState === 4 && xhr.status === 200) {
                            // Recarga la página o actualiza la tabla después de la eliminación
                            window.location.reload();
                        }
                    };
                    xhr.send("id=" + rowID);
                });
            });
        </script>

        <jsp:include page="./plantillas/PlantillaFooter.jsp" />
    </body>
</html>
