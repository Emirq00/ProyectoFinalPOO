import java.util.InputMismatchException;
import java.util.Scanner;
import Cuentas.*;
import SistemaDeMenus.*;

public class Ejecutable {
    public static BaseDeDatos baseDeDatos = new BaseDeDatos();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion=0;
        do {
            try{
                System.out.println("Bienvenido a Aeroviajes");
                System.out.println("1. Iniciar Sesión");
                System.out.println("2. Registrarse");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                opcion=scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        iniciarSesion();
                        break;
                    case 2:
                        registrarUsuario();
                        break;
                    case 3:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Ingrese una opción correcta");
                }
            }catch (InputMismatchException e){
                System.out.println("Entrada invalida, se esperan valores numericos");
                scanner.nextLine();
            }
        } while (opcion != 3);
        scanner.close();
    }

    public static void iniciarSesion() {
        Scanner sc = new Scanner(System.in);
        boolean ver = false;


        System.out.print("Ingrese su correo electrónico: ");
        String email = sc.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String password = sc.nextLine();

        if (baseDeDatos.validarUsuario(email, password) || baseDeDatos.validarAdministrador(email, password)) {
            String rol = baseDeDatos.obtenerRol(email);
            if (rol.equals("Administrador")) {
                ver = true;
                System.out.println("Inicio de sesión exitoso como Administrador");
                // Aquí puedes llamar al menú principal del administrador
                // MenuPrincipalAdministrador.main();
            } else {
                ver = true;
                System.out.println("Inicio de sesión exitoso como Usuario");
                Cliente usuario = baseDeDatos.obtenerUsuario(email);
                MenuPrincipalCliente.main(usuario);
            }
        } else {
            System.out.println("Datos incorrectos, intente nuevamente");
        }


    }

    public static void registrarUsuario() {
        Scanner sc = new Scanner(System.in);
        boolean exito = false;
        do {
            try {
                System.out.print("Ingrese su nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Ingrese su edad: ");
                int edad = sc.nextInt();
                sc.nextLine(); // Limpiar el buffer

                System.out.print("Ingrese su correo electrónico: ");
                String email = sc.nextLine();

                System.out.print("Ingrese su contraseña: ");
                String password = sc.nextLine();

                // Validaciones básicas
                if (nombre.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    System.out.println("Todos los campos son obligatorios.");
                    continue;
                }

                if (edad <= 0) {
                    System.out.println("La edad debe ser un número positivo.");
                    continue;
                }

                if (baseDeDatos.obtenerUsuario(email) == null) {
                    Cliente nuevoUsuario = new Cliente(nombre, edad, email, password);
                    baseDeDatos.agregarUsuario(nuevoUsuario);
                    System.out.println("Usuario registrado exitosamente.");
                    exito = true;
                } else {
                    System.out.println("El usuario ya está registrado.");
                    // Puedes decidir si permitir que el usuario intente con otro email
                }
            } catch (InputMismatchException e) {
                System.out.println("La edad debe ser un número entero.");
                sc.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("Ocurrió un error durante el registro: " + e.getMessage());
            }
            if (!exito) {
                System.out.println("Inténtelo nuevamente.");
            }
        } while (!exito);
    }

}

