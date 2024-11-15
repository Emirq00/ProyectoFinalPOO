package SistemaDeMenus;

import java.io.*;
import Tickets.FormatoTickets.*;
import java.util.*;

/**
 * Clase donde mostraremos un menú general con las diferentes operaciones que podemos hacer posterior al inicio de sesion, 
 * entre los que estan la consulta de los diferntes vuelos, salir al menu de inicio de sesión, descargar los boletos 
 * los menús, para las implementaciones de los diferentes árboles se manda a llamar un método con un menú secundario.
 */
public class MenuPrincipalCliente extends Menu{

    /**
     * Método main en el que mostramos el menú general con las diferentes opciones. El menú se repite indefinidamente
     * permitiendo al usuario realizar cualquier operación del sistema de manera ininterrumpida, para finalizar el programa el
     * usuario deberá ingresar la opción señalada en el menú como "Salir", permitiendo así romper el bucle while.
     */
    public static void menuPrincipal() {
        int decision=0;

        do{
            System.out.println("\n======== Bienvenido a nuestra página " +"nombre de usuario"+"========");
            System.out.println("1.- Mostrar vuelos disponibles");
            System.out.println("2.- Comprar vuelo");
            System.out.println("3.- Descargar boletos");
            System.out.println("4.- Salir");
            do{
                System.out.print("Ingresa tu entrada: ");
                incorrectEntry=false;
                try {
                    decision = scanner.nextInt();
                    if(decision<1 || decision>4){
                        System.out.println("* Ingrese una entrada válida");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("* Ingrese una entrada numérica");
                    scanner.nextLine();
                    incorrectEntry=true;
                }
            } while (incorrectEntry || decision<1 || decision>4);

            switch (decision) {
                case 1->consultaVuelos(); 
                case 2->{} //Implementación de la compra de vuelos
                case 3->{} //Implementación de la descarga de boletos
                case 4->scanner.close();
                default->System.out.println("* Ingrese una opción válida");
            }
        } while (decision!=4);
        System.err.println("Saliendo...");
    }

    /**
     * Método que muestra todos los vuelos, permitiendo al usuario observar los vuelos disponibles, para ello accederemos a un archivo
     * de objetos en el que anteriormente el administrador habrá guardado los vuelos disponibles, junto con 5 vuelos de ejemplo.
     */
    private static void consultaVuelos(){
        ObjectInputStream archivoObjetos;
        Vuelo vuelo;
        boolean haveReachEnd=false;

        try{
            archivoObjetos = new ObjectInputStream(new FileInputStream("Vuelos"));
            int i=1;
            while(true){
                vuelo = (Vuelo) archivoObjetos.readObject();
                System.out.println((i++)+".- "+vuelo.mostrarInformacionVuelo());
                if(haveReachEnd){
                    break;
                }
            }
            archivoObjetos.close();
        }
        catch (EOFException e){
            haveReachEnd=true;
        }
        catch (IOException e){
            System.out.println("IO Error: " + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound " + e.getMessage());
        }
    }
}
