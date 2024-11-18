package Pagos;

public abstract class MetodoPago {
    private String tipo;
    private String detalles;

    public MetodoPago(String tipo, String detalles) {
        this.tipo = tipo;
        this.detalles = detalles;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalles() {
        return detalles;
    }


    public abstract void pagar(InformacionPago informacionPago);

    public abstract boolean validarMetodoPago(double costo, InformacionPago informacionPago);
}
