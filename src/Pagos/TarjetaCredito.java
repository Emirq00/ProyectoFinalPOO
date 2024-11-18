package Pagos;

import Cuentas.Cliente;
import Cuentas.Usuario;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;

public class TarjetaCredito extends MetodoPago {
    private int numeroTarjeta;
    private LocalDate fechaExpiracion;
    private int cvv;
    private double limiteCredito;
    private static final Random random = new Random();
    private String nombreTitular;
    private static final HashSet<Integer> listaTarjetas = new HashSet<>();

    public TarjetaCredito(String nombreTitular) {
        super("Tarjeta de Crédito", "Pago con tarjeta bancaria");
        this.numeroTarjeta = generarNumeroTarjetaUnico();
        this.cvv = random.nextInt(100, 999);
        this.limiteCredito = 50000; // Límite inicial estándar
        this.fechaExpiracion = LocalDate.of(2028, 11, 16);
        this.nombreTitular=nombreTitular;
    }

    private int generarNumeroTarjetaUnico() {
        int numero;
        do {
            numero = random.nextInt((99999999 - 10000000) + 1) + 10000000;
        } while (!listaTarjetas.add(numero));
        return numero;
    }

    @Override
    public void pagar(InformacionPago informacionPago) {
        if (validarMetodoPago(this.limiteCredito, informacionPago)) {
            this.limiteCredito -= informacionPago.getMonto();
            System.out.println("Pago realizado exitosamente por un monto de: " + informacionPago.getMonto());
        } else {
            System.out.println("Proceso de pago rechazado. Intente nuevamente.");
        }
    }

    @Override
    public boolean validarMetodoPago(double costo, InformacionPago informacionPago) {
        if (String.valueOf(informacionPago.getNumeroTarjeta()).length() != 8) {
            throw new IllegalArgumentException("Número de tarjeta inválido.");
        }
        if (String.valueOf(informacionPago.getCvv()).length() != 3) {
            throw new IllegalArgumentException("CVV inválido.");
        }
        if (this.limiteCredito < costo) {
            System.out.println("Fondos insuficientes.");
            return false;
        }
        return this.numeroTarjeta == informacionPago.getNumeroTarjeta()
                && this.cvv == informacionPago.getCvv() && Objects.equals(this.nombreTitular, informacionPago.getNombreTitular())
                && this.fechaExpiracion==informacionPago.getFecha();
    }



    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public LocalDate getFechaExpiracion() {
        return fechaExpiracion;
    }

    public int getCvv() {
        return cvv;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public void setNumeroTarjeta(int numTarjeta) {
        this.numeroTarjeta=numTarjeta;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }
}
