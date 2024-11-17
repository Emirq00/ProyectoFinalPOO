import java.util.Scanner;
import Cuentas.*;
import SistemaDeMenus.*;
public class Ejecutable {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("Bienvenido a Aeroviajes");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            if (scanner.hasNextInt()){
                opcion = scanner.nextInt();
                scanner.nextLine(); 
            }else{
                System.out.println("Entrada no válida, intente nuevamente");
                scanner.nextLine(); 
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese sus datos");
                    System.out.print("Correo: ");
                    String cor = scanner.nextLine();
                    scanner.nextLine(); 
                    System.out.print("Contraseña: ");
                    String pas = scanner.nextLine();
                    scanner.nextLine(); 
                    iniciarSesion(cor, pas);
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Saliendo del programa..."); 
                    break;
                default:
                    System.out.println("Ingrese una opción correcta");
            }
        }while(opcion != 3);
    }
    public static void iniciarSesion(String email, String password) { 
        if (baseDeDatos.esUsuarioOAdministrador(email, password)) { 
            String rol = baseDeDatos.obtenerRol(email); 
            if (rol.equals("Administrador")) { 
                System.out.println("Inicio de sesión exitoso como Administrador");  
                } 
                else { 
                    System.out.println("Inicio de sesión exitoso como Usuario"); 
                    MenuPrincipalCliente.main(); 
                } 
            } else { 
                System.out.println("Datos incorrectos, intente nuevamente"); 
            } 
        }
}
