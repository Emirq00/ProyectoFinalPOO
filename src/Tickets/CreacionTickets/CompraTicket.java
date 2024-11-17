package Tickets.CreacionTickets;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import Tickets.FormatoTickets.*;

public class CompraTicket{

    public static Scanner scanner = new Scanner(System.in);

    public static void iniciarVuelosPrueba(){
        ObjectOutputStream fileOut;
        Vuelo vuelo1 = new VueloRedondo("Mexico", "Japon", LocalDateTime.of(2024, 12, 23, 19, 30), 70000, 7);
        Vuelo vuelo2 = new VueloRedondo("Uruguay", "Argentina", LocalDateTime.of(2025, 2, 4, 13, 0), 56000, 10);
        Vuelo vuelo3 = new VueloSimple("México", "España", LocalDateTime.of(2024, 12, 2, 15, 30), 45000);
        Vuelo vuelo4 = new VueloSimple("Canada", "Estados Unidos", LocalDateTime.of(2024, 12, 29, 21, 45), 10000); 
        Vuelo vuelo5 = new VueloSimple("India", "China", LocalDateTime.of(2024, 12, 30, 20, 0), 24000);
        try {
            fileOut = new ObjectOutputStream(new FileOutputStream("Vuelos"));
            fileOut.writeObject(vuelo1);
            fileOut.writeObject(vuelo2);
            fileOut.writeObject(vuelo3);
            fileOut.writeObject(vuelo4);
            fileOut.writeObject(vuelo5);
            fileOut.close();
        } 
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Método en el que vamos a generar todo el proceso de compra de un ticket, en el que se le pedirá al usuario que seleccione un vuelo
     * y en base al tipo de vuelo se reasignarán algunos atributos como el precio y la cantidad de asientos disponibles.
     *
     * @param vueloSeleccionado Vuelo seleccionado anteriormente por el usuario que se asignará al momento de la compra del ticket.
     */
    public static void comprarTicket(Vuelo vueloSeleccionado) {
        boolean entradaInvalida = false, indesicion = false;
        Ticket ticket = null;
        do {
            vueloSeleccionado.mostrarAsientos();
            System.out.println("\n¿Qué tipo de ticket desea comprar?");
            System.out.println("1. Standard");
            System.out.println("2. Premium");
            System.out.println("3. VIP");
            System.out.println("4. Salir");
            do {
                entradaInvalida = false;
                try {
                    System.out.print("Ingrese su entrada: ");
                    int opcion = scanner.nextInt();
                    switch(opcion){
                        case 1-> ticket = new StandardTicket(vueloSeleccionado);
                        case 2-> ticket = new PremiumTicket(vueloSeleccionado);
                        case 3-> ticket = new VipTicket(vueloSeleccionado);
                        case 4-> {
                            System.out.println("Regresado...");
                            return;
                        }
                        default->{
                            System.out.println(" *Opción no válida");
                            entradaInvalida = true;
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println(" *Ingrese una entrada numérica");
                    scanner.nextLine();
                    entradaInvalida = true;
                }
            } while (entradaInvalida);
            
            scanner.nextLine();
            String opcion=null;
            do {
                entradaInvalida = false;
                try {
                    System.out.print("Ingrese su asiento: ");
                    opcion = scanner.nextLine();
                    if(ticket.getVuelo().getAsientosDisponibles().containsKey(opcion)){
                        if(ticket.getVuelo().getAsientosDisponibles().get(opcion)==1 && ticket instanceof StandardTicket){
                            System.out.println("Asiento reservado exitosamente");
                            ticket.setAsiento(opcion);
                        }else if(ticket.getVuelo().getAsientosDisponibles().get(opcion)==2 && ticket instanceof PremiumTicket){
                            System.out.println("Asiento reservado exitosamente");
                            ticket.setAsiento(opcion);

                        }else if(ticket.getVuelo().getAsientosDisponibles().get(opcion)==3 && ticket instanceof VipTicket){
                            System.out.println("Asiento reservado exitosamente");
                            ticket.setAsiento(opcion);
                        } else {
                            System.out.println("Ingrese un asiento asociado al tipo de ticket seleccionado");
                            entradaInvalida = true;
                        }
                    } else{
                        System.out.println("Ingrese un asiento válido");
                        entradaInvalida = true;
                    }
                } catch (InputMismatchException e) {
                    System.out.println(" *Ingrese una entrada numérica");
                    entradaInvalida = true;
                }
            } while (entradaInvalida);

            System.out.println("\nResumen de compra:");
            System.out.println(ticket.getVuelo().mostrarInformacionCompra(opcion));
            System.out.println("¿Qué deseas hacer?");
            System.out.println("1. Proseguir al pago");
            System.out.println("2. Regresar a la selección de tickets");
            System.out.println("3. Regresar al menú principal");
            do {
                entradaInvalida = false;
                System.out.print("Ingrese su entrada: ");
                int op = scanner.nextInt();
                switch(op){
                    case 1-> indesicion = false;
                    case 2-> indesicion = true;
                    case 3-> {
                        System.out.println("Regresado...");
                        return;
                    }
                    default->{
                        System.out.println(" *Opción no válida");
                        entradaInvalida = true;
                    }
                }
            } while (entradaInvalida);
            
        } while (indesicion);

        //Toda la chingadera para pagar

        //Ingresamos el nuevo ticket asociado a la cuenta
        ObjectOutputStream fileOut=null;
        try {
            fileOut = new ObjectOutputStream(new FileOutputStream("src/Tickets/TicketsComprados/Fernando"));//Al final seria el nombre del usuario
            fileOut.writeObject(ticket);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        try {
            fileOut.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("¡Compra realizada con éxito!");
    }


    // Método para comprar un ticket y asociarlo al vuelo
    public static boolean pago(Ticket ticket) {
        if (ticket instanceof StandardTicket && ticket.getVuelo().getTicketsStandardDisponibles() > 0) {
            ticket.getVuelo().ticketsStandardDisponibles--;
            return true;
        } else if (ticket instanceof PremiumTicket && ticket.getVuelo().getTicketsPremiumDisponibles() > 0) {
            ticket.getVuelo().ticketsPremiumDisponibles--;
            return true;
        } else if (ticket instanceof VipTicket && ticket.getVuelo().getTicketsVIPDisponibles() > 0) {
            ticket.getVuelo().ticketsVIPDisponibles--;
            return true;
        }
        return false;
    }
}
