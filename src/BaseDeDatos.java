import java.util.*;
import Cuentas.*;

public class BaseDeDatos {
    private HashMap<String, Usuario> baseDeDatosUsuarios = new HashMap<>();
    private HashMap<String, Administrador> baseDeDatosAdministradores = new HashMap<>();

    public BaseDeDatos() {
        Administrador admin = new Administrador("Admin", 30, "admin123", "admin@aeroviajes.com");
        agregarAdministrador(admin);
    }

    public void agregarUsuario(Usuario usuario) {
        baseDeDatosUsuarios.put(usuario.getEmail(), usuario);
    }

    public void agregarAdministrador(Administrador administrador) {
        baseDeDatosAdministradores.put(administrador.getEmail(), administrador);
    }

    public void eliminarUsuario(String email) {
        baseDeDatosUsuarios.remove(email);
    }

    public void eliminarAdministrador(String email) {
        baseDeDatosAdministradores.remove(email);
    }

    public Usuario obtenerUsuario(String email) {
        return baseDeDatosUsuarios.get(email);
    }

    public Administrador obtenerAdministrador(String email) {
        return baseDeDatosAdministradores.get(email);
    }

    public boolean validarUsuario(String email, String password) {
        Usuario usuario = baseDeDatosUsuarios.get(email);
        return usuario != null && usuario.getPassword().equals(password);
    }

    public boolean validarAdministrador(String email, String password) {
        Administrador administrador = baseDeDatosAdministradores.get(email);
        return administrador != null && administrador.getPassword().equals(password);
    }

    public String obtenerRol(String email) {
        if (baseDeDatosAdministradores.containsKey(email)) {
            return "Administrador";
        } else if (baseDeDatosUsuarios.containsKey(email)) {
            return "Usuario";
        } else {
            return "No encontrado";
        }
    }
}
