package Tickets.CreacionTickets;

import java.time.LocalDateTime;
import java.util.Scanner;
import Tickets.FormatoTickets.*;

public class CompraTicket{

    public static Scanner scanner = new Scanner(System.in);

    public static void MenuCompraTickets(){
        //Prueba básica
        Ticket nuevoTicket;

        System.out.println("Ingrese el tipo de asiento que desea comprar: ");
        System.out.println("1. Standard");
        System.out.println("2. Premium");
        System.out.println("3. Vip");
        System.out.print("Indique el tipo de asiento: ");
        int asiento=scanner.nextInt();
        
        switch(asiento){
            case 1->nuevoTicket = new StandardTicket(new VueloSimple());
            case 2->nuevoTicket = new PremiumTicket(new VueloSimple());
            case 3->nuevoTicket = new VipTicket(new VueloSimple());
            default-> nuevoTicket = new StandardTicket(new VueloSimple());
        }
        
        nuevoTicket.mostrarInformacion();
        System.out.println("¿Desea proceder con el pago?");
        System.out.println("1. Si");
        System.out.println("2. No");
        System.out.print("Indique su respuesta: ");
        nuevoTicket.vuelo.setFecha(LocalDateTime.now());
        nuevoTicket.imprimirTicket();
        int proseguir=scanner.nextInt();

        //Cuando creemos la cuenta daremos la opcion a la persona de que ingrese una tarjeta de crédito asociada a su cuenta, en caso 
        // de que escoja ingresarla más adelante y el programa detecte que quiere proseguir con el pago sin haber asociado una tarjeta
        // de credito, se redirigirá al usuario a que ingrese una tarjeta de crédito con sus respectivas restricciones, en caso de que 
        //ya haya asignado una tarjeta de crédito a su nombre, solamente se pedirán los datos de la fecha de expiración y el cvc. si tiene
        // tarjeta se verá de la siguiente forma:

        /* Numero de tarjeta: (colocaremos el número de la tarjeta en automático)
         * Fecha de expiración: (lo colocara el usuario)
         * cvc: (lo colocará el usuario)
         */
    }
    public static void main(String[] args) {
        MenuCompraTickets();
    }
}
