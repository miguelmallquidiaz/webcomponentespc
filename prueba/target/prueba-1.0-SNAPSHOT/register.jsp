<%-- 
    Document   : register
    Created on : 10 nov. 2023, 15:47:13
    Author     : Walter
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="./css/styleIR.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300;500;700&display=swap" rel="stylesheet">
</head>

<body>
    <div class="login">
        <div class="form-container">
            
         
            
            <form action="LoginController1?accion=insert" method="post" class="form">
                <label for="nombre" class="label">Nombre</label>                
                <input type="text" id="nombre" placeholder="Juanito" class="input input-name" name="txtNombre">
                <label for="apellido" class="label">Apellido</label>                
                <input type="text" id="apellido" placeholder="Ramirez" class="input input-lastname" name="txtApellido">
                <label for="correo" class="label">Email address</label>                
                <input type="text" id="correo" placeholder="user@gmail.com" class="input input-email" name="txtCorreo">
                <label for="username" class="label">Username</label>                
                <input type="text" id="username" placeholder="Nickname" class="input input-name" name="txtUsername">
                <label for="password" class="label">Password</label>
                <input type="password" id="password" placeholder="*********" class="input input-password" name="txtPassword">


                <input type="submit" value="Register in" class="primary-button login-button">
                               
            </form>

            
            
        </div>
    </div>
</body>

</html>
