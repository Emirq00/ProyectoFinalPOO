package Pagos;

import Cuentas.Usuario;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Random;

public class TarjetaCredito extends MetodoPago{
    private Usuario owner;
    private int numeroTarjeta;
    private LocalDate fechaExpiracion;
    private int cvv;
    private double limiteCredito;
    private static Random random=new Random();
    private static final HashSet<Integer> listaTarjetas=new HashSet<>();

    public TarjetaCredito(Usuario owner){
        this.owner=owner;
        do{
            this.numeroTarjeta= random.nextInt((99999999 - 10000000) + 1) + 10000000;
        }while (!listaTarjetas.add(this.numeroTarjeta));
        this.cvv=random.nextInt(100, 999);
        this.limiteCredito=50000;
        this.fechaExpiracion=LocalDate.of(2028, 11, 16);
    }
    @Override
    public void pagar(double monto, int numeroTarjeta, int cvv) {
        if(validarMetodoPago(monto, numeroTarjeta, cvv)){
            this.limiteCredito-=monto;
        }
        else{
            System.out.println("Proceso de pago rechazado. Intente nuevamente");
        }
    }

    @Override
    public boolean validarMetodoPago(double costo, int numeroTarjeta, int cvv) {
        if (String.valueOf(numeroTarjeta).length() != 8) {
            throw new IllegalArgumentException("Número de tarjeta inválido.");
        }
        if (String.valueOf(cvv).length() != 3) {
            throw new IllegalArgumentException("CVV inválido.");
        }
        if (limiteCredito < costo) {
            return false;
        }
        if(numeroTarjeta == this.numeroTarjeta && cvv == this.cvv)return true;
        return false;
    }

    public Usuario getOwner() {
        return owner;
    }


}
