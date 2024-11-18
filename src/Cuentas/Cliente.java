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

    public void verMetodosPago(){
        if(getMetodosPagos()==null){
            System.out.println("Lo sentimos, no cuenta con ningun metodo de pago registrado");
        }
        else{
            for(MetodoPago x:getMetodosPagos()){
                System.out.println("-----------------------");
                System.out.println("Nombre del propietario: "+getNombre());
                System.out.println("Tipo: "+x.getTipo());
                System.out.println("Detalles: "+x.getDetalles());
                if(x.getClass().getName().equals("Pagos.TarjetaCredito")){
                    System.out.println("Cvv: "+((TarjetaCredito) x).getCvv());
                }
                if(x.getClass().getName().equals("Pagos.TarjetaCredito")){
                    System.out.println("Numero de tarjeta: "+((TarjetaCredito) x).getNumeroTarjeta());
                }
                if(x.getClass().getName().equals("Pagos.TarjetaCredito")){
                    System.out.println("Fecha de expiración: "+((TarjetaCredito) x).getFechaExpiracion());
                }
                if(x.getClass().getName().equals("Pagos.TarjetaCredito")){
                    System.out.println("Limite de crédito: "+((TarjetaCredito) x).getLimiteCredito());
                }
                if(x.getClass().getName().equals("Pagos.PagoEfectivo")){
                    System.out.println("Cash: "+((PagoEfectivo) x).getCashDisponible());
                }
                if(x.getClass().getName().equals("Pagos.Transferencia")){
                    System.out.println("Fondos: "+((Transferencia) x).getMonto());
                }
                System.out.println("-----------------------");
            }
        }
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
            cash=new PagoEfectivo();
            getMetodosPagos().add(cash);
            System.out.println("Método de pago en efectivo agregado exitosamente.");
        }
        System.out.print("Indique la cantidad de efectivo a ingresar: ");
        double monto = cin.nextDouble();
        if (monto < 0) {
            System.out.println("No puede ingresar cantidades negativas.");
        } else {
            cash.agregarCash(monto);
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
            transferencia = new Transferencia();
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
            tarjeta = new TarjetaCredito(getNombre());
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
