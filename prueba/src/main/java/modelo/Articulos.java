package modelo;

public class Articulos {

    private int id;
    private String nombre;
    private String imagen;
    private int stock;
    private double precio;

    public Articulos() {
    }

    public Articulos(int id, String nombre, String imagen, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.stock = stock;
        this.precio = precio;
    }

    
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Articulos{" + "id=" + id + ", nombre=" + nombre + ", imagen=" + imagen + ", stock=" + stock + ", precio=" + precio + '}';
    }
    
    
}
