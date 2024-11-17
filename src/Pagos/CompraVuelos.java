package Pagos;

import Cuentas.Cliente;
import Tickets.FormatoTickets.Vuelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CompraVuelos {

    public static void MenuCompra(Scanner cin, Cliente cliente) {
        System.out.print("Indique el ID del vuelo a comprar: ");
        int ID;
        try {
            ID = cin.nextInt();
            cin.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Debe ingresar un número entero.");
            cin.nextLine();
            return;
        }

        boolean vueloEncontrado = false;
        Vuelo vueloSeleccionado = null;
        List<Vuelo> vuelosDisponibles = Vuelo.getVuelosDisponibles(); // Asumiendo este método
        for (Vuelo vuelo : vuelosDisponibles) {
            if (vuelo.getID() == ID) {
                vueloEncontrado = true;
                vueloSeleccionado = vuelo;
                break;
            }
        }

        if (vueloEncontrado && vueloSeleccionado != null) {
            double costoVuelo = vueloSeleccionado.getPrecio();
            int opcion = -1;
            do {
                try {
                    System.out.println("==== Seleccione el método de pago ====");
                    System.out.println("1) Efectivo");
                    System.out.println("2) Tarjeta de crédito o débito");
                    System.out.println("3) Transferencia");
                    System.out.println("4) Salir del menú de compra");
                    System.out.print("Seleccione una opción: ");
                    opcion = cin.nextInt();
                    cin.nextLine(); // Consumir el salto de línea pendiente

                    switch (opcion) {
                        case 1:
                            menuEfectivo(cin, cliente, costoVuelo);
                            break;
                        case 2:
                            menuTarjeta(cin, cliente, costoVuelo);
                            break;
                        case 3:
                            menuTransferencia(cin, cliente, costoVuelo);
                            break;
                        case 4:
                            System.out.println("Saliendo del menú de compra");
                            break;
                        default:
                            System.out.println("Opción inválida");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Lo sentimos, solo se deben ingresar números");
                    cin.nextLine(); // Limpiar el buffer
                }
            } while (opcion != 4);
        } else {
            System.out.println("Lo sentimos, vuelo no encontrado");
        }
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
}
