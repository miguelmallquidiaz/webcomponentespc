<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Compra, java.util.*"%>
<%@page import="java.math.RoundingMode"%>
<%@page import="java.math.BigDecimal"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculadora de componentes</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style_1.css">
    </head>
    <body>
        <jsp:include page="./plantillas/PlantillaNavbar.jsp" />
        <%
            HttpSession ses = request.getSession();
            List<Compra> lista = (ArrayList<Compra>) ses.getAttribute("canasta");
            String contenido = null;
            String contenido2 = null;

            int cantidadCPU = 0;
            int cantidadPlaca = 0;
            int cantidadMemoria = 0;
            int cantidadTarjetaGrafica = 0;

            double precioTotal = 0.0;

            if (lista != null) {
                for (Compra x : lista) {
                    if (x.getTipo().equals("cpu")) {
                        cantidadCPU++;
                    }
                    if (x.getTipo().equals("placa")) {
                        cantidadPlaca++;
                    }
                    if (x.getTipo().equals("memoria")) {
                        cantidadMemoria++;
                    }
                    if (x.getTipo().equals("tarjetagrafica")) {
                        cantidadTarjetaGrafica++;
                    }
                    precioTotal += x.getPrecio();
                }
            }
            // Redondear precioTotal a dos decimales utilizando BigDecimal
            BigDecimal precioTotalBigDecimal = new BigDecimal(precioTotal);
            precioTotalBigDecimal = precioTotalBigDecimal.setScale(2, RoundingMode.HALF_UP);
            precioTotal = precioTotalBigDecimal.doubleValue();
        %>
        
        <div class="slider-frame">
        <ul>
            <li><img src="img/banner.jpg" alt=""></li>
            <li><img src="img/banner.jpg" alt=""></li>
            <li><img src="img/banner.jpg" alt=""></li>
            <li><img src="img/banner.jpg" alt=""></li>
        </ul>
        </div>


        <div class="container my-5 title">
            <div class="row">
                <div class="col-12">
                    <h1 class="text-center text-lg-left text-black">Elegir tus componentes: </h1>
                </div>
            </div>
        </div>

        <form id="id_form" action="controlUsuario?opc=3" method="post">
            <div class="container mt-4">
                <div class="row align-items-center">
                    <div class="col">
                        <p class="text-left fs-4">CPU: </p>
                    </div>
                </div>
                <%
                    int fila = 0;
                    int fila3 = 0;
                    String contenido3 = null;
                    int fila4 = 0;
                    String contenido4 = null;
                    if (cantidadCPU > 0) {
                        for (Compra x : lista) {
                            if (x.getTipo().equals("cpu")) {
                                contenido = "cpu";
                %>    
                <div class="row align-items-center">
                    <div class="col-12 col-md-3 d-none d-md-block" id="procesadorContainer">
                        <img src="<%=x.getImagen()%>" alt="Tu imagen" class="img-fluid img-height">
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6"><%=x.getNombre()%></p>
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6">1 unidades</p>
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6" id="precioCpu"><%=x.getPrecio()%></p>
                    </div>
                    <div class="col-12 col-md-3 mt-3 mt-md-0">
                        <a href="controlUsuario?opc=5&remover=<%=fila%>" class="btn btn-secondary btn-auto">Remover</a>
                    </div>
                </div>
                <%

                            }
                            fila++;
                        }
                    }
                    if (cantidadCPU == 0) {
                %>

                <div class="row align-items-center" >
                    <div class="col-12 col-md-3 mt-3 mt-md-0">
                        <button type="submit" name="cpu" class="btn btn-primary btn-auto">Seleccionar componente</button>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <div class="container mt-4">
                <div class="row align-items-center">
                    <div class="col">
                        <p class="text-left fs-4">Placa madre</p>
                    </div>
                </div>
                <%                    int fila2 = 0;
                    if (cantidadPlaca > 0) {
                        for (Compra x : lista) {
                            if (x.getTipo().equals("placa")) {
                                contenido2 = "placa";
                %>    
                <div class="row align-items-center">
                    <div class="col-12 col-md-3 d-none d-md-block" id="procesadorContainer">
                        <img src=<%=x.getImagen()%> alt="Tu imagen" class="img-fluid img-height">
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6"><%=x.getNombre()%></p>
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6">1 unidades</p>
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6" id="precioCpu"><%=x.getPrecio()%></p>
                    </div>
                    <div class="col-12 col-md-3 mt-3 mt-md-0">
                        <a href="controlUsuario?opc=5&remover=<%=fila2%>" class="btn btn-secondary btn-auto">Remover</a>
                    </div>
                </div>
                <%

                            }
                            fila2++;
                        }
                    }
                    if (cantidadPlaca == 0) {
                %>

                <div class="row align-items-center" >
                    <div class="col-12 col-md-3 mt-3 mt-md-0">
                        <button type="submit" name="placa" class="btn btn-primary btn-auto">Seleccionar componente</button>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <div class="container mt-4">
                <div class="row align-items-center">
                    <div class="col">
                        <p class="text-left fs-4">Memoria Ram</p>
                    </div>
                </div>
                <%
                    if (cantidadMemoria > 0) {
                        for (Compra x : lista) {
                            if (x.getTipo().equals("memoria")) {
                                contenido3 = "memoria";
                %>    
                <div class="row align-items-center">
                    <div class="col-12 col-md-3 d-none d-md-block" id="procesadorContainer">
                        <img src=<%=x.getImagen()%> alt="Tu imagen" class="img-fluid img-height">
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6"><%=x.getNombre()%></p>
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6">1 unidades</p>
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6" id="precioCpu"><%=x.getPrecio()%></p>
                    </div>
                    <div class="col-12 col-md-3 mt-3 mt-md-0">
                        <a href="controlUsuario?opc=5&remover=<%=fila3%>" class="btn btn-secondary btn-auto">Remover</a>
                    </div>
                </div>
                <%

                            }
                            fila3++;
                        }
                    }
                    if (cantidadMemoria == 0) {
                %>

                <div class="row align-items-center" >
                    <div class="col-12 col-md-3 mt-3 mt-md-0">
                        <button type="submit" name="memoria" class="btn btn-primary btn-auto">Seleccionar componente</button>
                    </div>
                </div>
            </div>
            <%
                }
            %>
            <div class="container mt-4">
                <div class="row align-items-center">
                    <div class="col">
                        <p class="text-left fs-4">Tarjeta Gráfica</p>
                    </div>
                </div>
                <%
                    if (cantidadTarjetaGrafica > 0) {
                        for (Compra x : lista) {
                            if (x.getTipo().equals("tarjetagrafica")) {
                                contenido4 = "tarjetagrafica";
                %>    
                <div class="row align-items-center">
                    <div class="col-12 col-md-3 d-none d-md-block" id="procesadorContainer">
                        <img src=<%=x.getImagen()%> alt="Tu imagen" class="img-fluid img-height">
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6"><%=x.getNombre()%></p>
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6">1 unidades</p>
                    </div>
                    <div class="col-12 col-md-3 d-none d-md-block">
                        <p class="text-center text-md-left fs-6" id="precioCpu"><%=x.getPrecio()%></p>
                    </div>
                    <div class="col-12 col-md-3 mt-3 mt-md-0">
                        <a href="controlUsuario?opc=5&remover=<%=fila4%>" class="btn btn-secondary btn-auto">Remover</a>
                    </div>
                </div>
                <%
                            }
                            fila4++;
                        }
                    }
                    if (cantidadTarjetaGrafica == 0) {
                %>

                <div class="row align-items-center" >
                    <div class="col-12 col-md-3 mt-3 mt-md-0">
                        <button type="submit" name="tarjetagrafica" class="btn btn-primary btn-auto">Seleccionar componente</button>
                    </div>
                </div>
            </div>
            <%
                }
            %>
        </form>

        <div class="container mt-4 p-4">
            <div class="row align-items-center">
                <div class="col-3">
                    <label for="precioTotal" class="text-left fs-4">Precio Total:</label>
                </div>
                <div class="col-3">
                    <input type="text" id="precioTotal" name="precioTotal" value="<%=precioTotal%>" readonly>
                </div>
            </div>
        </div>
    </body>
</html>
