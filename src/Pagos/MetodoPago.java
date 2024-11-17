package Pagos;

public abstract class MetodoPago {
    private String tipo;
    private String detalles;
    private InformacionPago info;

    public MetodoPago(String tipo, String detalles, InformacionPago info) {
        this.tipo = tipo;
        this.detalles = detalles;
        this.info=info;
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

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public InformacionPago getInfo() {
        return info;
    }

    public abstract void pagar(double monto, InformacionPago informacionPago);

    public abstract boolean validarMetodoPago(double costo, InformacionPago informacionPago);
}
