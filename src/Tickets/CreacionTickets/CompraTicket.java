package Tickets.CreacionTickets;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Cuentas.Cliente;
import Pagos.*;
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
    public static void comprarTicket(Vuelo vueloSeleccionado, Cliente cliente) {
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
                            System.out.println("Regresando...");
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
        int opcion=-1;
        int costoVuelo=100;
        do {
            try {
                System.out.println("==== Seleccione el método de pago ====");
                System.out.println("1) Efectivo");
                System.out.println("2) Tarjeta de crédito o débito");
                System.out.println("3) Transferencia");
                System.out.println("4) Salir del menú de compra");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea pendiente

                switch (opcion) {
                    case 1:
                        menuEfectivo(scanner, cliente, costoVuelo);
                        break;
                    case 2:
                        menuTarjeta(scanner, cliente, costoVuelo);
                        break;
                    case 3:
                        menuTransferencia(scanner, cliente, costoVuelo);
                        break;
                    case 4:
                        System.out.println("Saliendo del menú de compra");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Lo sentimos, solo se deben ingresar números");
                scanner.nextLine(); // Limpiar el buffer
            }
        } while (opcion != 4);

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

    public static void menuEfectivo(Scanner cin, Cliente cliente, double monto) {
        System.out.println("==== Pago en Efectivo ====");

        PagoEfectivo pagoEfectivo = null;
        // Verificar si el cliente ya tiene un método de pago en efectivo
        for (MetodoPago metodo : cliente.getMetodosPagos()) {
            if (metodo instanceof PagoEfectivo) {
                pagoEfectivo = (PagoEfectivo) metodo;
                break;
            }
        }

        if (pagoEfectivo == null) {
            // Crear un nuevo método de pago en efectivo
            InformacionPago info = new InformacionPago(0, 0, cliente.getNombre());
            pagoEfectivo = new PagoEfectivo(info);
            cliente.getMetodosPagos().add(pagoEfectivo);
        }

        System.out.print("Ingrese la cantidad de efectivo disponible: ");
        double efectivoDisponible = cin.nextDouble();
        cin.nextLine(); // Consumir el salto de línea pendiente

        pagoEfectivo.agregarEfectivo(efectivoDisponible);

        // Realizar el pago
        pagoEfectivo.pagar(monto, pagoEfectivo.getInfo());
    }

    public static void menuTarjeta(Scanner cin, Cliente cliente, double monto) {
        System.out.println("==== Pago con Tarjeta ====");

        // Solicitar los datos de la tarjeta
        System.out.print("Número de tarjeta (8 dígitos): ");
        int numTarjeta = cin.nextInt();
        cin.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Ingrese los tres números posteriores de su tarjeta (CVV): ");
        int cvv = cin.nextInt();
        cin.nextLine(); // Consumir el salto de línea pendiente
        System.out.print("Nombre del propietario: ");
        String nombreTitular = cin.nextLine();
        System.out.print("Ingrese la fecha de expiración de la tarjeta (formato: dd-MM-yyyy): ");
        String fecha = cin.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaExpiracion = LocalDate.parse(fecha, formatter);

        // Crear la información de pago
        InformacionPago infoPago = new InformacionPago(numTarjeta, cvv, nombreTitular);

        // Verificar si el cliente ya tiene una tarjeta registrada
        TarjetaCredito tarjeta = null;
        for (MetodoPago metodo : cliente.getMetodosPagos()) {
            if (metodo instanceof TarjetaCredito) {
                tarjeta = (TarjetaCredito) metodo;
                break;
            }
        }

        if (tarjeta == null) {
            // Crear una nueva tarjeta de crédito
            tarjeta = new TarjetaCredito(infoPago);
            cliente.getMetodosPagos().add(tarjeta);
        } else {

            tarjeta.setNumeroTarjeta(numTarjeta);
            tarjeta.setCvv(cvv);
            // tarjeta.setFechaExpiracion(fechaExpiracion);
        }

        tarjeta.pagar(monto, infoPago);
    }

    public static void menuTransferencia(Scanner cin, Cliente cliente, double monto) {
        System.out.println("==== Pago por Transferencia ====");

        Transferencia transferencia = null;
        // Verificar si el cliente ya tiene un método de transferencia
        for (MetodoPago metodo : cliente.getMetodosPagos()) {
            if (metodo instanceof Transferencia) {
                transferencia = (Transferencia) metodo;
                break;
            }
        }

        if (transferencia == null) {
            // Crear un nuevo método de pago por transferencia
            InformacionPago info = new InformacionPago(0, 0, cliente.getNombre());
            transferencia = new Transferencia(info);
            cliente.getMetodosPagos().add(transferencia);
        }

        System.out.print("Ingrese los fondos disponibles para la transferencia: ");
        double fondosDisponibles = cin.nextDouble();
        cin.nextLine();

        transferencia.agregarFondos(fondosDisponibles);

        // Realizar el pago
        transferencia.pagar(monto, transferencia.getInfo());
    }

    // Método para comprar un ticket y asociarlo al vuelo
    public static boolean pago(Ticket ticket) {
        if (ticket instanceof StandardTicket && ticket.getVuelo().ticketsStandardDisponibles > 0) {
            ticket.getVuelo().ticketsStandardDisponibles--;
            return true;
        } else if (ticket instanceof PremiumTicket && ticket.getVuelo().ticketsPremiumDisponibles > 0) {
            ticket.getVuelo().ticketsPremiumDisponibles--;
            return true;
        } else if (ticket instanceof VipTicket && ticket.getVuelo().ticketsVipDisponibles > 0) {
            ticket.getVuelo().ticketsVipDisponibles--;
            return true;
        }
        return false;
    }
}
