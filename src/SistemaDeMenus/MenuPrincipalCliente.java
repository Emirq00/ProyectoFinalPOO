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
        
        boolean notRecognizedEntry=false;
        String decision;
        int numeroDeVuelosDisponibles=0;
        scanner.nextLine();     //Limpieza buffer numérico del menú principal
        do {
            numeroDeVuelosDisponibles=vuelosDisponibles();
            do{
                System.out.print("Ingrese su entrada: ");
                decision = scanner.nextLine();
                notRecognizedEntry=filtroPalabras(decision, numeroDeVuelosDisponibles);
                
            } while (notRecognizedEntry);

        } while (!decision.equals("SALIR"));

        
    }

    /**
     * Método con el que vamos a mostrar los vuelos disponibles, para ello accederemos a un archivo de objetos en el que anteriormente
     * el administrador habrá guardado de los vuelos disponibles, junto con los 5 vuelos de ejemplo para el programa.
     * @return Retorna el número de vuelos almacenados dentro del archivo de objetos.
     */
    private static int vuelosDisponibles(){
        ObjectInputStream archivoObjetos=null;
        Vuelo vuelo;
        int numeroDeVuelosDisponibles=0;

        System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("   Vuelos disponibles: \n");

        try{
            archivoObjetos = new ObjectInputStream(new FileInputStream("Vuelos"));
            int i=1;
            while(true){
                vuelo = (Vuelo) archivoObjetos.readObject();
                numeroDeVuelosDisponibles++;
                System.out.println((i++)+".- "+vuelo.mostrarInformacionVuelo());
            }
        }
        catch (EOFException e){
            System.out.println("\nPara mayor información sobre los vuelos, por favor ingrese la palabra ''HELP''");
            System.out.println("Si desea reservar uno de los vielos disponible ingrese la palabra ''RESERVAR'' seguido del número del vuelo (ejemplo: RESERVAR4)");
            System.out.println("Si por el contrario desea regresar a menú principal, ingrese la palabra ''SALIR''");
            System.out.println("\n----------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
        catch (IOException e){
            System.out.println("IO Error: " + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound " + e.getMessage());
        }

        try {
            archivoObjetos.close();
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al tratar de cerrar el archivo");
        }

        return numeroDeVuelosDisponibles;
    }

    /**
     * Método con el que filtramos las distintas entradas que puede ingresar el usuario al momento de consultar los vuelos, el programa
     * se encarga de filtrar si la palabra fue reconocida o no, en casos afirmativos se realizará la acción correspondiente, en caso contrario
     * se le pedirá al usuario que ingrese una nueva entrada y se mostrarpá un mensaje de error, ya sea de no reconocer el tipo de vuelo o
     * no reconocer la entrada
     * @param decision Palabra a filtrar
     * @param numeroDeVuelosDisponibles Numero de vuelos disponibles recuperados del archivo de objetos
     * @return Devuelve true en caso de que se haya ingresado una entrada inválida, devuelve false en caso de que se haya ingresado una entrada válida.
     * Esta entrada válida puede ser "HELP", "SALIR" o "RESERVAR" seguido del número del vuelo y romperán el ciclo while en el método consultaVuelos.
     */
    private static boolean filtroPalabras(String decision, int numeroDeVuelosDisponibles){
        if(decision.equals("HELP")){
            mostrarInformacionGeneralVuelos();
            return false;

        } else if(decision.equals("SALIR")){
            System.out.println("Regresando al menú principal...");
            return false;

        } else if(decision.equals("RESERVAR")){
            System.out.println("* No se ha ingresado un número de vuelo, favor de especificarlo");
            return true;

        } else if (decision.length()>=9){
        
            if(decision.substring(0, 8).equals("RESERVAR")){
                int i;
                for(i=1; i<=numeroDeVuelosDisponibles; i++){
                    if(decision.charAt(decision.length()-1)==Integer.toString(i).charAt(0)){
                        System.out.println("Reservado"+i);
                        return false;
                    }
                }
                if(i==numeroDeVuelosDisponibles+1){
                    System.out.println("* No se ha encontrado el vuelo ingresado, por favor intente de nuevo");
                    return true;
                }
            }
        } else {
            System.out.println("* No se ha reconocido la entrada, por favor intente de nuevo");
        }
        return true;
    }


    private static void mostrarInformacionGeneralVuelos(){
        System.out.println("   Información general de los vuelos: \n");
        System.out.println("   -- Tipo de vuelo --");
        System.out.println("   Se refiere a los diferentes tipos de vuelos que se tienen disponibles, estos pueden ser de tipo sencillo o redondo.");
        System.out.println("   * Los vuelos sencillos son aquellos que solo tienen un destino y un origen.");
        System.out.println("   * Los vuelos redondos son aquellos que tienen un destino y un origen, pero además tienen un segundo vuelo de regreso.");
        System.out.println("     (NOTA: El tiempo entre el vuelo de ida y el vuelo de regreso está sujeto al vuelo seleccionado y puede variar).");
        System.out.println("   \n-- Fecha --");
        System.out.println("   Se refiere a la fecha especificada en la que el vuelo partira del origen hacia el destino, es recomendable");
        System.out.println("   que el usuario tome en cuenta la fecha de salida para evitar confusiones.");
        System.out.println("   \n  -- Precio --");
        System.out.println("   Se indica un precio base desde el cual comienzan los costos de los distintos tipos de vuelos, es importante");
        System.out.println("   señalar que la aerolínea se reserva el derecho de cambiar los precios sin previo aviso. Los precios de los");
        System.out.println("   Tickets premium y VIP son diferentes a los precios base.");
        System.out.println("   \n  -- Origen --");
        System.out.println("   Se refiere al lugar de donde parte el vuelo, es importante que el usuario tome en cuenta el lugar de origen");
        System.out.println("   para evitar confusiones al momento de comprar el boleto.");
        System.out.println("   \n  -- Destino --");
        System.out.println("   Se refiere al lugar al que llegará el vuelo, es importante que el usuario tome en cuenta el lugar de destino");
        System.out.println("   para evitar confusiones al momento de comprar el boleto.");
        System.out.println("   \n  -- Tiempo en días --");
        System.out.println("   Se refiere al tiempo que transcurre entre el vuelo de ida y el vuelo de regreso, este tiempo puede variar y solamente ");
        System.out.println("   aplica para los vuelos redondos. La fecha entre ambos vuelos está unicamente disponible al momento de la compra.\n");
    }
}
