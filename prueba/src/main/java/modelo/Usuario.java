/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Walter
 */
public class Usuario {
    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Usuario(){
        this.setId(0);
        this.setNombre("");
        this.setApellido("");
        this.setCorreo("");
        this.setUsername("");
        this.setPassword("");
    }
    
    public Usuario(int id, String nom,String ape,String co,String user, String pass){
        this.setId(id);
        this.setNombre(nom);
        this.setApellido(ape);
        this.setCorreo(co);
        this.setUsername(user);
        this.setPassword(pass);
    }
}
