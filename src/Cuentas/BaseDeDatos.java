import java.util.*;
import Cuentas.*;

public class BaseDeDatos {
    private HashMap<String, Usuario> baseDeDatosUsuarios = new HashMap<>();
    private HashMap<String, Organizador> baseDeDatosOrganizadores = new HashMap<>();

    public BaseDeDatos() {
        Organizador organizador = new Organizador("Admin", 30, "admin123", "admin@aeroviajes.com");
        agregarOrganizador(organizador);
    }

    public void agregarUsuario(Usuario usuario) {
        baseDeDatosUsuarios.put(usuario.getEmail(), usuario);
    }

    public void agregarOrganizador(Organizador organizador) {
        baseDeDatosOrganizadores.put(organizador.getEmail(), organizador);
    }

    public void eliminarUsuario(String email) {
        baseDeDatosUsuarios.remove(email);
    }

    public void eliminarOrganizador(String email) {
        baseDeDatosOrganizadores.remove(email);
    }

    public Usuario obtenerUsuario(String email) {
        return baseDeDatosUsuarios.get(email);
    }

    public Organizador obtenerOrganizador(String email) {
        return baseDeDatosOrganizadores.get(email);
    }

    public boolean validarUsuario(String email, String password) {
        Usuario usuario = baseDeDatosUsuarios.get(email);
        return usuario != null && usuario.getPassword().equals(password);
    }

    public boolean validarOrganizador(String email, String password) {
        Organizador organizador = baseDeDatosOrganizadores.get(email);
        return organizador != null && organizador.getPassword().equals(password);
    }

    public String obtenerRol(String email) {
        if (baseDeDatosOrganizadores.containsKey(email)) {
            return "Organizador";
        } else if (baseDeDatosUsuarios.containsKey(email)) {
            return "Usuario";
        } else {
            return "No encontrado";
        }
    }
}

