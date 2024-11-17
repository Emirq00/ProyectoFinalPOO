package Cuentas;

import Pagos.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Cliente extends Usuario implements Observer{
    //private List<Compra> comprasRealizadas;
    private List<MetodoPago> metodosPagos;

    public Cliente() {
        //this.comprasRealizadas = new ArrayList<>();
        this.metodosPagos = new ArrayList<>();
    }
    public Cliente(String nombre, int edad, String email, String password){
        super(nombre, edad, email, password);
        this.metodosPagos = new ArrayList<>();
    }


    public List<MetodoPago> getMetodosPagos() {
        return metodosPagos;
    }



    public String verCompras() {
        // Implementación
        return "";
    }

    public void addMetodoPago(Scanner cin) {
        int opcion = -1;
        do {
            try {
                System.out.println("==== Menú de Registro de Métodos de Pago ====");
                System.out.println("1) Agregar efectivo");
                System.out.println("2) Agregar fondos a la cuenta");
                System.out.println("3) Agregar Tarjeta de Crédito");
                System.out.println("4) Salir");
                System.out.print("Seleccione una opción: ");
                opcion = cin.nextInt();

                switch (opcion) {
                    case 1:
                        agregarPagoEfectivo(cin);
                        break;

                    case 2:
                        agregarTransferencia(cin);
                        break;

                    case 3:
                        agregarTarjetaCredito(cin);
                        break;

                    case 4:
                        System.out.println("Saliendo del menú...");
                        break;

                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción del 1 al 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Solo se esperan valores numéricos.");
                cin.nextLine(); // Limpiar el buffer
            }

        } while (opcion != 4);
    }

    private void agregarPagoEfectivo(Scanner cin) {
        PagoEfectivo cash = null;
        boolean existeMetodo = false;
        for (MetodoPago x : getMetodosPagos()) {
            if (x instanceof PagoEfectivo) {
                cash = (PagoEfectivo) x;
                existeMetodo = true;
                break;
            }
        }
        if (!existeMetodo) {
            InformacionPago info = new InformacionPago(0, 0, getNombre());
            cash = new PagoEfectivo(info);
            getMetodosPagos().add(cash);
            System.out.println("Método de pago en efectivo agregado exitosamente.");
        }
        System.out.print("Indique la cantidad de efectivo a ingresar: ");
        double monto = cin.nextDouble();
        if (monto < 0) {
            System.out.println("No puede ingresar cantidades negativas.");
        } else {
            cash.agregarEfectivo(monto);
            System.out.println("Monto ingresado exitosamente.");
        }
    }

    private void agregarTransferencia(Scanner cin) {
        // Suponiendo que tienes una clase Transferencia
        Transferencia transferencia = null;
        boolean existeMetodo = false;
        for (MetodoPago x : getMetodosPagos()) {
            if (x instanceof Transferencia) {
                transferencia = (Transferencia) x;
                existeMetodo = true;
                break;
            }
        }
        if (!existeMetodo) {
            InformacionPago info = new InformacionPago(0, 0, getNombre());
            transferencia = new Transferencia(info);
            getMetodosPagos().add(transferencia);
            System.out.println("Método de pago por transferencia agregado exitosamente.");
        }
        System.out.print("Indique el monto a ingresar: ");
        double cantidad = cin.nextDouble();
        if (cantidad < 0) {
            System.out.println("No se pueden ingresar montos negativos.");
        } else {
            transferencia.agregarFondos(cantidad);
            System.out.println("Operación exitosa.");
        }
    }

    private void agregarTarjetaCredito(Scanner cin) {
        TarjetaCredito tarjeta = null;
        boolean existeMetodo = false;
        for (MetodoPago x : getMetodosPagos()) {
            if (x instanceof TarjetaCredito) {
                System.out.println("Lo sentimos, ya cuenta con una tarjeta de crédito registrada.");
                existeMetodo = true;
                break;
            }
        }
        if (!existeMetodo) {
            // Solicitar datos al usuario
            System.out.print("Ingrese su número de tarjeta (8 dígitos): ");
            int numeroTarjeta = cin.nextInt();
            System.out.print("Ingrese su CVV (3 dígitos): ");
            int cvv = cin.nextInt();
            cin.nextLine(); // Limpiar buffer
            System.out.print("Ingrese el nombre del titular: ");
            String nombreTitular = cin.nextLine();

            // Crear InformacionPago con los datos ingresados
            InformacionPago info = new InformacionPago(numeroTarjeta, cvv, nombreTitular);
            tarjeta = new TarjetaCredito(info);
            getMetodosPagos().add(tarjeta);
            System.out.println("Tarjeta de crédito agregada exitosamente.");
        }
    }
    @Override
    public void actualizar(boolean asientoDisponible) {
        if (asientoDisponible) {
            System.out.println("Hola, " + getNombre() + ", un asiento se ha liberado.");
        } else {
            System.out.println("Hola, " + getNombre() + ", el asiento ya no está disponible.");
        }
    }
}
