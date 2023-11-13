/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interface.IUsuario;
import java.util.List;
import modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import util.MySQLConexion;


/**
 *
 * @author Walter
 */
public class UsuarioDAO implements IUsuario{

    @Override
    public Boolean loguearUsuario(Usuario usuario) throws ClassNotFoundException  {
        try {
        Connection cn = MySQLConexion.getConexion();
        String vsql = "SELECT * FROM usuarios WHERE username=? AND password=?";
        try (PreparedStatement pst = cn.prepareStatement(vsql)) {
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    } catch (SQLException e) {
        System.out.println(e.toString());
        return false;
    }
    }

    @Override
  public Boolean insertarUsuario(Usuario usuario) {
    try {
        Connection cn = MySQLConexion.getConexion();
        String vsql = "INSERT INTO usuarios (nombre, apellido, correo, username, password) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement pst = cn.prepareStatement(vsql);
        pst.setString(1, usuario.getNombre());
        pst.setString(2, usuario.getApellido());
        pst.setString(3, usuario.getCorreo());
        pst.setString(4, usuario.getUsername());
        pst.setString(5, usuario.getPassword());

        int rpta = pst.executeUpdate();

        if (rpta == 1) {
            System.out.println("Registros insertados exitosamente: " + rpta);
            return true;
        } else {
            System.out.println("No se insertó ningún dato");
            return false;
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
    }
}

    @Override
    public List<Usuario> listarUsuario() {
          Connection cn = MySQLConexion.getConexion();
          String vsql = "select id,nombre,apellido,correo,username,password from usuarios";
            PreparedStatement pst;
        ResultSet rs=null;
        List<Usuario> lista = new ArrayList<>();
        try
        {
           pst=cn.prepareStatement(vsql);
       
           rs=pst.executeQuery();
           while (rs.next())
           {
              int id = rs.getInt("id");
              String nombre= rs.getString("nombre");
              String apellido= rs.getString("apellido");
              String correo= rs.getString("correo");
              String username= rs.getString("username");
              String password= rs.getString("password");
              Usuario usuario = new Usuario(id,nombre,apellido,                                            
                                            correo,username,password);
              lista.add(usuario);
              
           }
           return lista;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());       
            return null;
        }
        //El método ListarCajero devuelve la variable rs con su contenido
        //return rs;
    }

    @Override
    public Usuario mostrarUsuario(int i) {
        Connection cn = MySQLConexion.getConexion(); 
        String vsql="select * from usuarios where id=?";
        PreparedStatement pst;
        ResultSet rs=null;
        Usuario usuario = null;
        try
        {
           pst=cn.prepareStatement(vsql);
           pst.setInt(1, i);
           //ExecuteQuery ejecuta la consulta de la variable vsql
           //La variable rs recibe todo el contenido de la consulta (toda la tabla TCajero)
           rs=pst.executeQuery();
           while (rs.next())
           {
              int id = rs.getInt("id");
              String nombre= rs.getString("nombre");
              String apellido= rs.getString("apellido");
              String correo= rs.getString("correo");
              String username= rs.getString("username");
              String password= rs.getString("password");
              usuario = new Usuario(id,nombre,apellido,
                                        correo,username,password);
           }
           return usuario;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());       
            return null;
        }
    }

    @Override
    public boolean modificarUsuario(Usuario usuario) {
        try{
            Connection cn = MySQLConexion.getConexion();
            String vsql = "update usuarios set id=?,nombre=?,apellido=?,correo=?,username=?,"
                    + "password=? where id=?";           
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement(vsql);
            
            
            
            pst.setString(1, usuario.getNombre());
            pst.setString(2, usuario.getApellido());
            pst.setString(3, usuario.getCorreo());
            pst.setString(4, usuario.getUsername());
            pst.setString(5, usuario.getPassword());
            pst.setInt(6, usuario.getId());
            
            pst.execute();
            int x = pst.executeUpdate();
            System.out.println(x + " registros actualizados.");
            if (x==1){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            System.out.println(e.toString());            
        }
        return false;  
    }

    @Override
    public boolean eliminarUsuario(int id) {
        try{
            Connection cn = MySQLConexion.getConexion();
            String vsql = "delete from usuarios where id=?";           
            PreparedStatement pst = (PreparedStatement) cn.prepareStatement(vsql);
            pst.setInt(1, id);
            pst.execute();
            return true;
            /*if (x==1){
                return true;
            }
            else{
                return false;
            }*/
            
        }
        catch(SQLException e){
            System.out.println(e.toString());            
            return false;    
        }
    }

    @Override
    public List<Usuario> buscarUsuario(String texto) {
         Connection cn = MySQLConexion.getConexion(); 
        String vsql="select * from usuarios where nombre like '"+texto+"%'";
        PreparedStatement pst;
        ResultSet rs=null;
        List<Usuario> lista = new ArrayList<>();
        try
        {
           pst=cn.prepareStatement(vsql);
           //ExecuteQuery ejecuta la consulta de la variable vsql
           //La variable rs recibe todo el contenido de la consulta (toda la tabla TCajero)
           rs=pst.executeQuery();
           while (rs.next())
           {
              int id = rs.getInt("id");
              String nombre= rs.getString("nombre");
              String apellido= rs.getString("apellido");
              String correo= rs.getString("correo");
              String username= rs.getString("username");
              String password= rs.getString("password");
              Usuario usuario = new Usuario(id,nombre,apellido,
                                        correo,username,password);
              lista.add(usuario);
              
           }
           return lista;
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());       
            return null;
        }
    }
      
  
    

 
    
}
