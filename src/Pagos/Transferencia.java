package Pagos;

import Cuentas.Cliente;
import Cuentas.Usuario;

import java.util.HashSet;
import java.util.Random;

public class Transferencia extends MetodoPago {
    private int cuenta;
    private double montDisponible;
    private String estado;
    private static final HashSet<Integer> listaTarjetas = new HashSet<>();
    private static final Random random = new Random();

    public Transferencia() {
        super("Transferencia Bancaria", "Pago mediante transferencia entre cuentas");
        this.montDisponible=0.0;
        this.cuenta=generarNumeroUnico();
        this.estado = "Pendiente";
    }

    private int generarNumeroUnico() {
        int numero;
        do {
            numero = random.nextInt((99999999 - 10000000) + 1) + 10000000;
        } while (!listaTarjetas.add(numero));
        return numero;
    }

    @Override
    public void pagar(InformacionPago informacionPago) {
        if (!validarMetodoPago(this.montDisponible, informacionPago)) {
            System.out.println("La transferencia no pudo completarse. Verifique los datos.");
            return;
        }

        // Simular el descuento de fondos de la cuenta origen
        this.montDisponible-= informacionPago.getMonto();
        this.estado = "Completada";

        System.out.println("Transferencia completada exitosamente. Monto de la transferencia: " + informacionPago.getMonto());
        System.out.println("Fondos restantes: "+montDisponible);
    }

    @Override
    public boolean validarMetodoPago(double montDisponible, InformacionPago informacionPago) {
        // Validar si el monto es válido
        if (montDisponible <= 0) {
            System.out.println("El monto de la transferencia debe ser mayor a cero.");
            return false;
        }

        // Validar si la cuenta origen tiene fondos suficientes
        if (montDisponible < informacionPago.getMonto()) {
            System.out.println("Fondos insuficientes en la cuenta de origen.");
            return false;
        }
        if(cuenta!=informacionPago.getNumeroTarjeta()){
            System.out.println("El numero de cuenta no coincide");
            return false;
        }
        return true;
    }


    public void agregarFondos(double monto){
        montDisponible+=monto;
    }

    public double getMonto() {
        return montDisponible;
    }

    public int getCuenta() {
        return cuenta;
    }
}
