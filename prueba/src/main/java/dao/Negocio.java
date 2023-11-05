package dao;
import util.MySQLConexion;
import java.util.*;
import java.sql.*;
import Interface.InterReserva;
import modelo.*;
public class Negocio implements InterReserva{
    
    @Override
    public List<Articulos> listadoArticulos(String tipo) {
        List<Articulos> lista = new ArrayList();
        try {
            Connection cn = MySQLConexion.getConexion();
            String sql = "select ID, Nombre, Imagen, Stock, Precio FROM " + tipo;
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Articulos ep = new Articulos();
                ep.setId(rs.getInt(1));
                ep.setNombre(rs.getString(2));
                ep.setImagen(rs.getString(3));
                ep.setStock(rs.getInt(4));
                ep.setPrecio(rs.getDouble(5));
                lista.add(ep);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    @Override
    public Articulos busArticulo(String tipo, String id) {
        Articulos ep=null;
     try{
       Connection cn=MySQLConexion.getConexion();
       String sql="select ID, Nombre , Imagen, Precio, Stock "
               + " from " + tipo +" where ID = " + id;
       PreparedStatement st=cn.prepareStatement(sql);
       ResultSet rs=st.executeQuery();
       if(rs.next()){
            ep=new Articulos();
           ep.setId(rs.getInt(1));
           ep.setNombre(rs.getString(2));
           ep.setImagen(rs.getString(3));
           ep.setPrecio(rs.getDouble(4));
           ep.setStock(rs.getInt(5));
       }
     }catch(Exception ex){
         ex.printStackTrace();
     }
     return ep;
    }

}
