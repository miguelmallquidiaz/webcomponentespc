/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.*;
import dao.Negocio;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HP
 */
public class controlUsuario extends HttpServlet {

    Negocio obj1 = new Negocio();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int op = Integer.parseInt(request.getParameter("opc"));
        if (op == 3) {
            listadoArticulo(request, response);
        }
        if (op == 4) {
            carrito(request, response);
        }
        if (op == 5) {
            EliminarItem(request, response);
        }
    }

    protected void listadoArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        String cpuValue = request.getParameter("cpu");
        String placaValue = request.getParameter("placa");
        String memoriaValue = request.getParameter("memoria");
        String tarjetagraficaValue = request.getParameter("tarjetagrafica");
        String nombreCpu = (String) ses.getAttribute("nombreCpu");
        String nombrePlaca = (String) ses.getAttribute("nombrePlaca");
        String nombreMemoria = (String) ses.getAttribute("nombreMemoria");

        List<Compra> lista = (List<Compra>) ses.getAttribute("canasta");
        List<Articulos> componentesCompatibles = new ArrayList<>();
        List<Articulos> listaCPUs = obj1.listadoArticulos("cpu");
        List<Articulos> listaPlacasMadres = obj1.listadoArticulos("placa");
        List<Articulos> listaMemoriaRam = obj1.listadoArticulos("memoria");

        //Si se seleciona CPU
        if (cpuValue != null) {
            try {
                // Verifica si hay una placa base y memoria en el carrito
                boolean placaBaseEnCarrito = false;
                boolean memoriaEnCarrito = false;

                if (lista != null) {
                    for (Compra compra : lista) {
                        if ("placa".equals(compra.getTipo())) {
                            placaBaseEnCarrito = true;
                        }
                        if ("memoria".equals(compra.getTipo())) {
                            memoriaEnCarrito = true;
                        }
                    }
                }

                if (placaBaseEnCarrito && memoriaEnCarrito) {
                    // Si hay tanto una placa base como memoria RAM en el carrito, muestra solo las CPUs compatibles
                    nombrePlaca = obtenerNombrePlacaBaseEnCarrito(lista);
                    nombreMemoria = obtenerNombreDeMemoriaEnCarrito(lista);
                    for (Articulos cpu : listaCPUs) {
                        // Agrega aquí la lógica para determinar la compatibilidad de CPU con la placa base
                        if (esCompatibleCpu(cpu, nombrePlaca, nombreMemoria)) {
                            componentesCompatibles.add(cpu);
                        }
                    }
                    request.setAttribute("dato", componentesCompatibles);
                } else if (memoriaEnCarrito) {
                    nombreMemoria = obtenerNombreDeMemoriaEnCarrito(lista);
                    for (Articulos cpu : listaCPUs) {
                        // Agrega aquí la lógica para determinar la compatibilidad de CPU con la placa base
                        if (esCompatibleCpuConMemoria(cpu, nombreMemoria)) {
                            componentesCompatibles.add(cpu);
                        }
                    }
                    request.setAttribute("dato", componentesCompatibles);
                } else if (placaBaseEnCarrito) {
                    nombrePlaca = obtenerNombrePlacaBaseEnCarrito(lista);
                    for (Articulos cpu : listaCPUs) {
                        // Agrega aquí la lógica para determinar la compatibilidad de CPU con la placa base
                        if (esCompatibleCpuConPlacaBase(cpu, nombrePlaca)) {
                            componentesCompatibles.add(cpu);
                        }
                    }
                    request.setAttribute("dato", componentesCompatibles);
                } else {
                    // Si no hay una placa base en el carrito, muestra la lista completa de CPUs
                    request.setAttribute("dato", listaCPUs);
                }
                request.setAttribute("tipo", "cpu");
                request.getRequestDispatcher("ComponentTable.jsp").forward(request, response);
            } catch (Exception e) {
            }
        }

        if (placaValue != null) {
            try {
                // Verifica si hay una CPU y memoria en el carrito
                boolean cpuEnCarrito = false;
                boolean memoriaEnCarrito = false;

                if (lista != null) {
                    for (Compra compra : lista) {
                        if ("cpu".equals(compra.getTipo())) {
                            cpuEnCarrito = true;
                        }
                        if ("memoria".equals(compra.getTipo())) {
                            memoriaEnCarrito = true;
                        }
                    }
                }

                // Si hay una CPU en el carrito, muestra solo las placas base compatibles
                if (cpuEnCarrito && memoriaEnCarrito) {
                    nombreCpu = obtenerNombreDeCpuEnCarrito(lista);
                    nombreMemoria = obtenerNombreDeMemoriaEnCarrito(lista);
                    for (Articulos placa : listaPlacasMadres) {
                        if (esCompatibleConPlaca(placa, nombreCpu, nombreMemoria)) {
                            componentesCompatibles.add(placa);
                        }
                    }
                    request.setAttribute("dato", componentesCompatibles);
                } else if (cpuEnCarrito) {
                    nombreCpu = obtenerNombreDeCpuEnCarrito(lista);
                    for (Articulos placa : listaPlacasMadres) {
                        if (esCompatiblePlacaBaseConCPU(placa, nombreCpu)) {
                            componentesCompatibles.add(placa);
                        }
                    }
                    request.setAttribute("dato", componentesCompatibles);
                } else if (memoriaEnCarrito) {
                    nombreMemoria = obtenerNombreDeMemoriaEnCarrito(lista);
                    for (Articulos placa : listaPlacasMadres) {
                        if (esCompatiblePlacaConMemoria(placa, nombreMemoria)) {
                            componentesCompatibles.add(placa);
                        }
                    }
                    request.setAttribute("dato", componentesCompatibles);
                } else {
                    // Si no hay una CPU en el carrito, muestra la lista completa de placas base
                    request.setAttribute("dato", listaPlacasMadres);
                }
                request.setAttribute("tipo", "placa");
                ses.getAttribute("nombreCpu");
                request.getRequestDispatcher("ComponentTable.jsp").forward(request, response);
            } catch (Exception e) {
            }
        }
        if (memoriaValue != null) {
            try {
                boolean cpuEnCarrito = false;
                boolean placaBaseEnCarrito = false;
                if (lista != null) {
                    for (Compra compra : lista) {
                        if ("cpu".equals(compra.getTipo())) {
                            cpuEnCarrito = true;
                        }
                        if ("placa".equals(compra.getTipo())) {
                            placaBaseEnCarrito = true;
                        }
                    }
                }

                // Si hay una CPU en el carrito, muestra solo las memorias RAM compatibles
                if (cpuEnCarrito && placaBaseEnCarrito) {
                    nombreCpu = obtenerNombreDeCpuEnCarrito(lista);
                    nombrePlaca = obtenerNombrePlacaBaseEnCarrito(lista);
                    for (Articulos memoria : listaMemoriaRam) {
                        if (esCompatibleMemoria(memoria, nombreCpu, nombrePlaca)) {
                            componentesCompatibles.add(memoria);
                        }
                    }
                    request.setAttribute("dato", componentesCompatibles);
                }else if(cpuEnCarrito){
                    nombreCpu = obtenerNombreDeCpuEnCarrito(lista);
                    for (Articulos memoria : listaMemoriaRam) {
                        if (esCompatibleMemoriaConCPU(memoria, nombreCpu)) {
                            componentesCompatibles.add(memoria);
                        }
                    }
                    request.setAttribute("dato", componentesCompatibles);
                } 
                else if(placaBaseEnCarrito){
                    nombrePlaca = obtenerNombrePlacaBaseEnCarrito(lista);
                    for (Articulos memoria : listaMemoriaRam) {
                        if (esCompatibleMemoriaConPlaca(memoria, nombrePlaca)) {
                            componentesCompatibles.add(memoria);
                        }
                    }
                    request.setAttribute("dato", componentesCompatibles);
                } 
                else {
                    // Si no hay una CPU en el carrito, muestra la lista completa de memorias RAM
                    request.setAttribute("dato", listaMemoriaRam);
                }
                request.setAttribute("tipo", "memoria");
                request.getRequestDispatcher("ComponentTable.jsp").forward(request, response);
            } catch (Exception e) {
            }
        }
        if (tarjetagraficaValue != null) {
            request.setAttribute("dato", obj1.listadoArticulos("tarjetagrafica"));
            request.setAttribute("tipo", "tarjetagrafica");
            request.getRequestDispatcher("ComponentTable.jsp").forward(request, response);
        }
    }

    //Obtner nombre de los productos
    private String obtenerNombrePlacaBaseEnCarrito(List<Compra> lista) {
        for (Compra compra : lista) {
            if ("placa".equals(compra.getTipo())) {
                return compra.getNombre();
            }
        }
        return null;
    }

    private String obtenerNombreDeCpuEnCarrito(List<Compra> lista) {
        for (Compra compra : lista) {
            if ("cpu".equals(compra.getTipo())) {
                return compra.getNombre();
            }
        }
        return null;
    }

    private String obtenerNombreDeMemoriaEnCarrito(List<Compra> lista) {
        for (Compra compra : lista) {
            if ("memoria".equals(compra.getTipo())) {
                return compra.getNombre();
            }
        }
        return null;
    }

    //Para CPU lógica
    private boolean esCompatibleCpu(Articulos cpu, String nombrePlaca, String nombreMemoria) {
        if (("G.Skill Flare X5 32 GB".equals(nombreMemoria))
                && ("Intel Core i5-12400F".equals(cpu.getNombre())) && ("MSI B550 GAMING GEN3".equals(nombrePlaca))) {
            return true;
        }
        if (("Corsair Vengeance LPX 16 GB".equals(nombreMemoria))
                && ("AMD Ryzen 5 5600X".equals(cpu.getNombre())) || ("Intel Core i5-12400F".equals(cpu.getNombre()))
                && ("Gigabyte Z790 AORUS ELITE AX".equals(nombrePlaca) || "MSI B550 GAMING GEN3".equals(nombrePlaca))) {
            return true;
        }
        if (("Corsair Vengeance RGB Pro 16 GB".equals(nombreMemoria))
                && ("AMD Ryzen 5 5600X".equals(cpu.getNombre())) || ("Intel Core i5-12400F".equals(cpu.getNombre()))
                && ("Gigabyte Z790 AORUS ELITE AX".equals(nombrePlaca) || "MSI B550 GAMING GEN3".equals(nombrePlaca))) {
            return true;
        }

        return false;
    }

    private boolean esCompatibleCpuConMemoria(Articulos cpu, String nombreMemoria) {
        // Agrega aquí la lógica para determinar la compatibilidad de la CPU con la placa base y la memoria RAM
        if ("Intel Core i5-12400F".equals(cpu.getNombre()) && "G.Skill Flare X5 32 GB".equals(nombreMemoria)) {
            return true;
        }
        if ("AMD Ryzen 5 5600X".equals(cpu.getNombre()) && "Corsair Vengeance LPX 16 GB".equals(nombreMemoria) || "Corsair Vengeance LPX 16 GB".equals(nombreMemoria)) {
            return true;
        }
        return false;
    }

    private boolean esCompatibleCpuConPlacaBase(Articulos cpu, String nombrePlaca) {
        // Agrega aquí la lógica para determinar la compatibilidad de CPU con la placa base
        if ("Intel Core i5-12400F".equals(cpu.getNombre()) && "Gigabyte Z790 AORUS ELITE AX".equals(nombrePlaca)) {
            return true;
        }
        if ("AMD Ryzen 5 5600X".equals(cpu.getNombre()) && "MSI B550 GAMING GEN3".equals(nombrePlaca)) {
            return true;
        }
        return false;
    }

    //Para placa lógica
    private boolean esCompatibleConPlaca(Articulos placa, String nombreCpu, String nombreMemoria) {
        if (("G.Skill Flare X5 32 GB".equals(nombreMemoria))
                && ("Intel Core i5-12400F".equals(nombreCpu)) && ("MSI B550 GAMING GEN3".equals(placa.getNombre()))) {
            return true;
        }
        if (("Corsair Vengeance LPX 16 GB".equals(nombreMemoria))
                && ("AMD Ryzen 5 5600X".equals(nombreCpu)) || ("Intel Core i5-12400F".equals(nombreCpu))
                && ("Gigabyte Z790 AORUS ELITE AX".equals(placa.getNombre()) || "MSI B550 GAMING GEN3".equals(placa.getNombre()))) {
            return true;
        }
        if (("Corsair Vengeance RGB Pro 16 GB".equals(nombreMemoria))
                && ("AMD Ryzen 5 5600X".equals(nombreCpu)) || ("Intel Core i5-12400F".equals(nombreCpu))
                && ("Gigabyte Z790 AORUS ELITE AX".equals(placa.getNombre()) || "MSI B550 GAMING GEN3".equals(placa.getNombre()))) {
            return true;
        }

        return false;
    }

    private boolean esCompatiblePlacaConMemoria(Articulos placa, String nombreMemoria) {
        if ("Gigabyte Z790 AORUS ELITE AX".equals(placa.getNombre()) && "G.Skill Flare X5 32 GB".equals(nombreMemoria)) {
            return true;
        }
        if ("MSI B550 GAMING GEN3".equals(placa.getNombre()) && "Corsair Vengeance LPX 16 GB".equals(nombreMemoria)) {
            return true;
        }
        return false;
    }

    private boolean esCompatiblePlacaBaseConCPU(Articulos placa, String nombreCpu) {
        if ("Intel Core i5-12400F".equals(nombreCpu) && "Gigabyte Z790 AORUS ELITE AX".equals(placa.getNombre())) {
            return true;
        }
        if ("AMD Ryzen 5 5600X".equals(nombreCpu) && "MSI B550 GAMING GEN3".equals(placa.getNombre())) {
            return true;
        }
        return false;
    }

    //Para memoria lógica
    private boolean esCompatibleMemoria(Articulos memoria, String nombreCpu, String nombrePlaca) {
        if (("G.Skill Flare X5 32 GB".equals(memoria.getNombre()))
                && ("Intel Core i5-12400F".equals(nombreCpu)) && ("Gigabyte Z790 AORUS ELITE AX".equals(nombrePlaca))) {
            return true;
        }
        if (("Corsair Vengeance LPX 16 GB".equals(memoria.getNombre())|| "Corsair Vengeance RGB Pro 16 GB".equals(memoria.getNombre()))
                && ("AMD Ryzen 5 5600X".equals(nombreCpu))
                && ("MSI B550 GAMING GEN3".equals(nombrePlaca))) {
            return true;
        }
        return false;
    }

    private boolean esCompatibleMemoriaConPlaca(Articulos memoria, String nombrePlaca) {
        if ("Gigabyte Z790 AORUS ELITE AX".equals(nombrePlaca) && "G.Skill Flare X5 32 GB".equals(memoria.getNombre())) {
            return true;
        }
        if ("MSI B550 GAMING GEN3".equals(nombrePlaca) && "Corsair Vengeance LPX 16 GB".equals(memoria.getNombre())||"Corsair Vengeance RGB Pro 16 GB".equals(memoria.getNombre())) {
            return true;
        }
        return false;
    }

    private boolean esCompatibleMemoriaConCPU(Articulos memoria, String nombreCpu) {
        if ("Intel Core i5-12400F".equals(nombreCpu) && "G.Skill Flare X5 32 GB".equals(memoria.getNombre())
                || "Corsair Vengeance RGB Pro 16 GB".equals(memoria.getNombre())
                || "Corsair Vengeance LPX 16 GB".equals(memoria.getNombre())) {
            return true;
        }
        if ("AMD Ryzen 5 5600X".equals(nombreCpu) && "Corsair Vengeance RGB Pro 16 GB".equals(memoria.getNombre())
                || "Corsair Vengeance LPX 16 GB".equals(memoria.getNombre())) {
            return true;
        }
        return false;
    }

    protected void carrito(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession ses = request.getSession();
            String cod = request.getParameter("id");
            String tipo = request.getParameter("tipo");
            String nombreCpu = request.getParameter("nombre");
            Articulos ar = obj1.busArticulo(tipo, cod);
            Compra cp = new Compra();
            cp.setId(Integer.parseInt(cod));
            cp.setImagen(ar.getImagen());
            cp.setNombre(ar.getNombre());
            cp.setPrecio(ar.getPrecio());
            cp.setTipo(tipo);
            cp.setCantidad(1);
            List<Compra> lista;
            if (ses.getAttribute("canasta") == null) //no hay ningun producto
            {
                lista = new ArrayList();
            } else {
                lista = (ArrayList<Compra>) ses.getAttribute("canasta");
            }
            lista.add(cp); //agregar a lista de compras
            ses.setAttribute("canasta", lista);//actualizar la sesion
            if (tipo.equals("placa")) {
                ses.setAttribute("nombrePlaca", nombreCpu);
                System.out.println("nomreplaca:" + nombreCpu);
            }
            if (tipo.equals("cpu")) {
                ses.setAttribute("nombreCpu", nombreCpu);//actualizar la sesion
            }
            if (tipo.equals("memoria")) {
                ses.setAttribute("nombreMemoria", nombreCpu);//actualizar la sesion
            }
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (Exception e) {
        }
    }

    protected void EliminarItem(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession ses = request.getSession();
        int indice = Integer.parseInt(request.getParameter("remover"));
        List<Compra> lista = (ArrayList<Compra>) ses.getAttribute("canasta");
        lista.remove(indice);
        ses.setAttribute("canasta", lista);//actualizar la sesion
        // Redirige al usuario a la página del carrito sin los parámetros en la URL
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        //request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
