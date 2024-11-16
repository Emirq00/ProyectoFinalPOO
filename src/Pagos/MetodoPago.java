package Pagos;

public abstract class MetodoPago {
    private String tipo;
    private String detalles;

    public abstract void pagar(double monto, int numeroTarjeta, int cvv);


    public abstract boolean validarMetodoPago(double costo, int numeroTarjeta, int cvv);
}
