package Pagos;

public class InformacionPago {
    private int numeroTarjeta;
    private int cvv;
    private String nombre;
    private double montoEfectivo;
    private double fondos;

    // Constructor completo
    public InformacionPago(int numeroTarjeta, int cvv, String nombre) {
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
        this.nombre = nombre;
        this.montoEfectivo = 0;
        this.fondos = 0;
    }

    // Constructor por defecto
    public InformacionPago() {
        this(0, 0, "");
    }

    // Métodos para manejar efectivo y fondos
    public void agregarEfectivo(double monto) {
        montoEfectivo += monto;
    }

    public void agregarFondos(double monto) {
        fondos += monto;
    }

    public void restarEfectivo(double monto) {
        montoEfectivo -= monto;
    }

    public void restarFondos(double monto) {
        fondos -= monto;
    }

    // Getters y setters
    public int getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(int numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMontoEfectivo() {
        return montoEfectivo;
    }

    public void setMontoEfectivo(double montoEfectivo) {
        this.montoEfectivo = montoEfectivo;
    }

    public double getFondos() {
        return fondos;
    }

    public void setFondos(double fondos) {
        this.fondos = fondos;
    }
}
