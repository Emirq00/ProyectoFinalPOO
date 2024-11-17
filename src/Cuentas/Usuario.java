package Cuentas;

import Pagos.MetodoPago;

import java.util.List;
import java.util.Set;

public abstract class Usuario implements UsuarioBase {
    private String nombre;
    private int edad;
    private String password;
    private String email;
    private List<String> historialBusqueda;
    private List<MetodoPago> metodosPagos;

    public Usuario(){};

    public Usuario(String nombre, int edad, String password){
        this.nombre=nombre;
        this.edad=edad;
        this.password=password;
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

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void registrarUsuario(String nombre, int edad, String email, String password) {
        //implementacion
    }

    @Override
    public void modificarDatos(String nombre, int edad, String email, String password) {
        //implementacion
    }

    @Override
    public void iniciarSesion(String email, String password) {
        //implementacion
    }

    @Override
    public void cerrarSesion() {
        //implementacion
    }


    public void verHistorial(){
        //implementacion
    }

    public List<MetodoPago> getMetodosPagos() {
        return metodosPagos;
    }

    public String getEmail() {
        return email;
    }
}
