/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import java.util.List;
import modelo.Usuario;
/**
 *
 * @author Walter
 */
public interface IUsuario {
  
    Boolean loguearUsuario(Usuario usuario)throws ClassNotFoundException;
    Boolean insertarUsuario(Usuario usuario);
    List<Usuario> listarUsuario();
    Usuario mostrarUsuario(int id);
    boolean modificarUsuario(Usuario usuario);
    boolean eliminarUsuario(int id);
    List<Usuario> buscarUsuario(String texto);
}
