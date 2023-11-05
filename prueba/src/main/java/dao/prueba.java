/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import modelo.Articulos;
import java.util.List;
/**
 *
 * @author HP
 */
public class prueba {
    public static void main(String[] args) {
        Negocio obj = new Negocio();
        String tipo = "cpu";
        tipo = "placa";
        List<Articulos> lista = obj.listadoArticulos(tipo);
        
        for (Articulos a : lista) {
            System.out.println("ID: " + a.getId());
            System.out.println("Imagen: " + a.getImagen());
            System.out.println("Nombre: " + a.getNombre());
            System.out.println("Precio: " + a.getPrecio());
            System.out.println("Stock: " + a.getStock());
            System.out.println();
        }
    }
   
}
