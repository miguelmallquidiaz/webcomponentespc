package Interface;
import modelo.*;
import java.util.*;
public interface InterReserva {
    List<Articulos> listadoArticulos(String tipo);
    Articulos busArticulo(String tipo, String id);
}
