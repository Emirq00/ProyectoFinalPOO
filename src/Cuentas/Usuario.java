package Cuentas;
import java.io.Serializable;
import Pagos.MetodoPago;

import java.util.List;
import java.util.Set;

public abstract class Usuario implements UsuarioBase, Serializable {
    private String nombre;
    private int edad;
    private String password;
    private String email;
    private List<String> historialBusqueda;
    private List<MetodoPago> metodosPagos;

    public Usuario() {}

    public Usuario(String nombre, int edad, String password, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.password = password;
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void registrarUsuario(String nombre, int edad, String email, String password) {
        // Implementación
    }

    @Override
    public void modificarDatos(String nombre, int edad, String email, String password) {
        // Implementación
    }

    @Override
    public void iniciarSesion(String email, String password) {
        // Implementación
    }

    @Override
    public void cerrarSesion() {
        // Implementación
    }

    public void verHistorial() {
        // Implementación
    }
}
