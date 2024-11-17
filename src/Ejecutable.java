import java.util.Scanner;
import Cuentas.*;
import SistemaDeMenus.*;

public class Ejecutable {
    public static BaseDeDatos baseDeDatos = new BaseDeDatos();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Bienvenido a Aeroviajes");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Entrada no válida, intente nuevamente");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese sus datos");
                    System.out.print("Correo: ");
                    String email = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String password = scanner.nextLine();
                    iniciarSesion(email, password);
                    break;
                case 2:
                    System.out.println("Ingrese los datos para registrarse");
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Edad: ");
                    int edad = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Correo: ");
                    String nuevoEmail = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String nuevoPassword = scanner.nextLine();
                    registrarUsuario(nombre, edad, nuevoEmail, nuevoPassword);
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Ingrese una opción correcta");
            }
        } while (opcion != 3);
        scanner.close();
    }

    public static void iniciarSesion(String email, String password) {
        boolean ver = false;
        do {
            if (baseDeDatos.validarUsuario(email, password) || baseDeDatos.validarAdministrador(email, password)) {
                String rol = baseDeDatos.obtenerRol(email);
                if (rol.equals("Administrador")) {
                    ver = true;
                    System.out.println("Inicio de sesión exitoso como Administrador");
                    //MenuPrincipalAdministrador.main();
                } else {
                    ver = true;
                    System.out.println("Inicio de sesión exitoso como Usuario");
                    MenuPrincipalCliente.main();
                }
            } else {
                System.out.println("Datos incorrectos, intente nuevamente");
            }
        } while (!ver);
    }

    public static void registrarUsuario(String nombre, int edad, String email, String password) {
        boolean exito = false;
        do {
            try {
                if (baseDeDatos.obtenerUsuario(email) == null) {
                    Usuario nuevoUsuario = new Usuario() {};
                    nuevoUsuario.registrarUsuario(nombre, edad, email, password);
                    baseDeDatos.agregarUsuario(nuevoUsuario);
                    System.out.println("Usuario registrado exitosamente.");
                    exito = true;
                } else {
                    System.out.println("El usuario ya está registrado");
                    exito = true;
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error durante el registro: " + e.getMessage());
            } finally {
                if (!exito) {
                    System.out.println("Intentelo nuevamente");
                }
            }
        } while (!exito);
    }
}
