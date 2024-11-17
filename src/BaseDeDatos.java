import java.util.*;
import Cuentas.*;

public class BaseDeDatos {
    HashMap<String, Usuario> baseDeDatos = new HashMap<>();
    
    public void agregarUsuario(String tipo, Usuario usuario) {
        baseDeDatos.put(tipo, usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        baseDeDatos.remove(usuario);
    }

}
