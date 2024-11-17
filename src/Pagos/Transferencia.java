package Pagos;

import Cuentas.Cliente;
import Cuentas.Usuario;

public class Transferencia extends MetodoPago {
    private InformacionPago cuentaOrigen;
    private double monto;
    private String estado;

    public Transferencia(InformacionPago cuentaOrigen) {
        super("Transferencia Bancaria", "Pago mediante transferencia entre cuentas", cuentaOrigen);
        if (cuentaOrigen == null) {
            throw new IllegalArgumentException("La cuenta origen no puede ser nula.");
        }
        this.cuentaOrigen = cuentaOrigen;
        this.estado = "Pendiente";
    }

    @Override
    public void pagar(double monto, InformacionPago informacionPago) {
        if (!validarMetodoPago(monto, informacionPago)) {
            System.out.println("La transferencia no pudo completarse. Verifique los datos.");
            return;
        }

        // Simular el descuento de fondos de la cuenta origen
        this.cuentaOrigen.restarFondos(monto);
        this.monto = monto;
        this.estado = "Completada";

        System.out.println("Transferencia completada exitosamente. Monto: " + monto);
    }

    @Override
    public boolean validarMetodoPago(double costo, InformacionPago informacionPago) {
        // Validar si el monto es válido
        if (costo <= 0) {
            System.out.println("El monto de la transferencia debe ser mayor a cero.");
            return false;
        }

        // Validar si la cuenta origen tiene fondos suficientes
        if (cuentaOrigen.getFondos()>=costo) {
            System.out.println("Fondos insuficientes en la cuenta de origen.");
            return false;
        }

        return true;
    }

    public InformacionPago getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void agregarFondos(double monto){
        getInfo().agregarFondos(monto);
    }

    public void RestarMonto(double monto){
        getInfo().restarFondos(monto);
    }

    public double getMonto() {
        return monto;
    }

    public String getEstado() {
        return estado;
    }
}
