package Pagos;

public abstract class MetodoPago {
    private String tipo;
    private String detalles;

    public boolean validarMetodoPago(){
        return true;
    }

    public abstract void pagar(double monto);


}
