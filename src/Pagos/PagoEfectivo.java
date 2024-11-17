package Pagos;

public class PagoEfectivo extends MetodoPago {
    private double cambioEntregado;

    public PagoEfectivo(InformacionPago info) {
        super("Pago en Efectivo", "Pago realizado en efectivo", info);
        this.cambioEntregado = 0.0;
    }

    public void agregarEfectivo(double monto) {
        getInfo().agregarEfectivo(monto);
    }

    public void restarEfectivo(double monto) {
        getInfo().restarEfectivo(monto);
    }

    @Override
    public void pagar(double monto, InformacionPago informacionPago) {
        // Validar que se entrega efectivo suficiente
        if (!validarMetodoPago(monto, informacionPago)) {
            System.out.println("El pago en efectivo no pudo completarse. El monto es insuficiente.");
            return;
        }

        // Calcular el cambio a entregar
        double montoEntregado = informacionPago.getMontoEfectivo();
        this.cambioEntregado = montoEntregado - monto;

        // Restar el monto utilizado del efectivo disponible
        informacionPago.restarEfectivo(monto);

        System.out.println("Pago en efectivo completado exitosamente. Monto: " + monto);
        System.out.println("Cambio entregado: " + cambioEntregado);
    }

    @Override
    public boolean validarMetodoPago(double costo, InformacionPago informacionPago) {
        // Asegurarse de que se entrega suficiente efectivo para cubrir el costo
        double montoEntregado = informacionPago.getMontoEfectivo();
        if (montoEntregado < costo) {
            System.out.println("El efectivo entregado es insuficiente para cubrir el costo.");
            return false;
        }
        return true;
    }

    public double getCambioEntregado() {
        return cambioEntregado;
    }

    public void setCambioEntregado(double cambioEntregado) {
        this.cambioEntregado = cambioEntregado;
    }
}
