<%-- 
    Document   : Plantilla
    Created on : 29 set. 2023, 19:59:40
    Author     : Bryan
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Corporación Luana</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="./CSS/style.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-black">
            <div class="container bg-black">
                <a class="navbar-brand" href="#">
                    <img src="./img/logo2.png" alt="Logo" height="30">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                        aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                    <ul class="navbar-nav">
                         <li class="nav-item">
                            <a class="nav-link text-light" href="./index.jsp">INICIO</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="./crud_placa.jsp">PLACA</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="./crud_CPU.jsp">PROCESADOR</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="./crud_tarjeta.jsp">TARJETA GRAFICA</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-light" href="./crud_memoria.jsp">MEMORIA RAM</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
</html>
