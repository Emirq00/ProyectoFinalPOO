package Cuentas;
public class Administrador extends Usuario{
    private String nombre;
    private int edad;
    private String password;
    private String email;

    public Administrador(String nombre, int edad, String password, String email){
        this.nombre=nombre;
        this.edad=edad;
        this.password=password;
        this.email=email;
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
}
