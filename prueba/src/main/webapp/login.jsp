<%-- 
    Document   : login
    Created on : 10 nov. 2023, 15:46:01
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
            <img src="../logos/logo_yard_sale.svg" alt="logo" class="logo">
         
            
            <form action="LoginController1?accion=loguear" class="form" method="post">
                <label for="username" class="label">Username</label>
                <input type="text" id="username" placeholder="nickname" class="input input-email" name="username">
                <label for="password" class="label">Password</label>
                <input type="password" id="password" placeholder="*********" class="input input-password" name="password">
                <input type="submit" value="Login in" class="primary-button login-button">
                <a href="#">Forgot my password</a>                
            </form>
            <button class="secondary-button singup-button" onclick="window.location.href='LoginController1?accion=nuevo'">Sing up</button>
            
        </div>
    </div>
</body>

</html>
