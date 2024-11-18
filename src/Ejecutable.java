import java.util.InputMismatchException;
import java.util.Scanner;
import Cuentas.*;
import SistemaDeMenus.*;

public class Ejecutable {
    public static BaseDeDatos baseDeDatos = new BaseDeDatos();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean entradaIncorrecta = false;
        int opcion=0;
        do {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("   Bienvenido a Aeroviajes");
            System.out.println("   1. Iniciar Sesión");
            System.out.println("   2. Registrarse");
            System.out.println("   3. Salir");
            System.out.println("--------------------------------------------------------------------");
            do {
                try{
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
            } while (entradaIncorrecta);
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
                //MenuPrincipalCliente.main(usuario);
            }
        } else {
            System.out.println("Datos incorrectos, intente nuevamente");
        }


    }

    public static void registrarUsuario() {
        Scanner sc = new Scanner(System.in);
        boolean fracaso = false;
        String nombre, email, password;
        int edad=0;
        do {
            fracaso=false;
            System.out.print("Ingrese su nombre: ");
            nombre = sc.nextLine();
            if(nombre.length()<3){
                System.out.println(" * El nombre debe tener al menos 3 caracteres");
                fracaso = true;
            }
            for(char caracter: nombre.toCharArray()){
                if(Character.isDigit(caracter)){
                    System.out.println(" * El nombre no puede contener numeros");
                    fracaso = true;
                }
            }
        } while (fracaso);
            
        do {
            try {
                fracaso=false;
                System.out.print("Ingrese su edad: ");
                edad = sc.nextInt();
                if(edad<18){
                    System.out.println(" * Debe ser mayor de edad para registrarse");
                    fracaso = true;
                }

            } catch (InputMismatchException e) {
                System.out.println(" * La edad debe ser un número entero.");
                sc.nextLine(); // Limpiar el buffer
                fracaso = true;
            }
        } while (fracaso);

        sc.nextLine(); // Limpiar el buffer
            
        do {
            fracaso=false;
            System.out.print("Ingrese su correo electrónico (dominio @gmail o @hotmail): ");
            email = sc.nextLine();
            if(email.length()>12){
                if(!email.substring(email.length()-10, email.length()).equals("@gmail.com") && !email.substring(email.length()-12, email.length()).equals("@hotmail.com")){
                    System.out.println(" * El correo debe finalizar con extensión @gmail.com o @hotmail.com");
                    fracaso = true;
                }
                if(email.length()<20){
                    System.out.println(" * El correo no cumple el tamaño mínimo de 20 caracteres");
                    fracaso = true;
                }
            }else{
                System.out.println(" * El correo no cumple el tamaño mínimo");
                fracaso = true;
            }
            
        } while (fracaso);
            
        do {
            fracaso=false;
            System.out.print("Ingrese su contraseña: ");
            password = sc.nextLine();
            if(password.length()<8){
                System.out.println(" * La contraseña debe tener al menos 8 caracteres");
                fracaso = true;
            }
            int digits=0, caracaters=0, specialCaracters=0;
            for(char caracteres: password.toCharArray()){
                if(Character.isDigit(caracteres)){
                    digits++;
                }
                if (Character.isLetter(caracteres)){
                    caracaters++;
                }
                if(caracteres=='_' || caracteres=='-' || caracteres=='.' || caracteres=='@'){
                    specialCaracters++;
                }
            }
            if(digits<2){
                System.out.println(" * La contraseña debe tener al menos 2 digitos");
                fracaso = true;
            } if(caracaters<4){
                System.out.println(" * La contraseña debe tener al menos 4 letras");
                fracaso = true;
            } if(specialCaracters<1){
                System.out.println(" * La contraseña debe tener al menos 1 caracter especial ('-' ,  '_'  , '.'  , '@'  )");
                fracaso = true;
            }

        } while (fracaso);

            // Validaciones básicas
        if (baseDeDatos.obtenerUsuario(email) == null) {
            Cliente nuevoUsuario = new Cliente(nombre, edad, email, password);
            baseDeDatos.agregarUsuario(nuevoUsuario);
            System.out.println("Usuario registrado exitosamente.");
        } else {
            System.out.println("El usuario ya está registrado.");
            return;
        }   
    }
}

