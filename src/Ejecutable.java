import Cuentas.*;
import SistemaDeMenus.*;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Ejecutable {
    public static BaseDeDatos baseDeDatos = new BaseDeDatos();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            try {
                System.out.println("Bienvenido a Aeroviajes");
                System.out.println("1. Iniciar Sesión");
                System.out.println("2. Registrarse");
                System.out.println("3. Salir");
                System.out.print("Seleccione una opción: ");
                if (scanner.hasNextInt()) {
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Entrada inválida, intente nuevamente");
                    scanner.nextLine();
                    continue;
                }
                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese sus datos");
                        System.out.print("Correo: ");
                        String email = scanner.nextLine();
                        if(!validarEmail(email)){
                            System.out.println("Direccion de e-mail invalida. Dominios aceptados " +
                                    "(@gmail.com)" + " (@hotmail.com)"+" (@outlook.com)");
                            break;
                        }
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
                        if(!validarEmail(nuevoEmail)){
                            System.out.println("Direccion de e-mail invalida. Dominios aceptados " +
                                    "(@gmail.com)" + " (@hotmail.com)"+" (@outlook.com)");
                            break;
                        }
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
            } catch (InputMismatchException e) {
                System.out.println("Lo sentimos, entrada inválida");
                scanner.nextLine();
            }
        } while (opcion != 3);
        scanner.close();
    }

    public static void iniciarSesion(String email, String password) {
        esperarAleatoriamente();
        boolean ver = false;

        if ((baseDeDatos.validarUsuario(email, password) || baseDeDatos.validarAdministrador(email, password))) {
            String rol = baseDeDatos.obtenerRol(email);
            if (rol.equals("Administrador")) {
                ver = true;
                System.out.println("Inicio de sesión exitoso como Administrador");
                Cliente usuario = baseDeDatos.obtenerUsuario();
                MenuPrincipalAdministrador.menuAdministrador();
            } else {
                ver = true;
                System.out.println("Inicio de sesión exitoso como Usuario");
                MenuPrincipalCliente.main(baseDeDatos.obtenerUsuario(email));
            }
        } else {
            System.out.println("Datos incorrectos o email inválido, intente nuevamente");

        }

    }

    public static void registrarUsuario(String nombre, int edad, String email, String password) {
        esperarAleatoriamente();
        boolean exito = false;
        do {
            try {
                if (validarEmail(email) && baseDeDatos.obtenerUsuario(email) == null) {
                    Cliente nuevoUsuario = new Cliente(nombre, edad, email, password);
                    baseDeDatos.agregarUsuario(nuevoUsuario);
                    System.out.println("Usuario registrado exitosamente.");
                    exito = true;
                } else {
                    System.out.println("El usuario ya está registrado o el email es inválido");
                    exito = true;
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error durante el registro: " + e.getMessage());
            } finally {
                if (!exito) {
                    System.out.println("Inténtelo nuevamente");
                }
            }
        } while (!exito);
    }

    public static boolean validarEmail(String email) {
        String[] dominiosValidos = {"@gmail.com", "@hotmail.com", "@outlook.com"};
        for (String dominio : dominiosValidos) {
            if (email.endsWith(dominio)) {
                return true;
            }
        }
        return false;
    }

    public static void esperarAleatoriamente() {
        Random random = new Random();
        int tiempoEspera = random.nextInt(3000);
        try {
            Thread.sleep(tiempoEspera);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}