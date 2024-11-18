package Pagos;

public class PagoEfectivo extends MetodoPago {
    private double cantidad;

    public PagoEfectivo() {
        super("Pago en Efectivo", "Pago realizado en efectivo");
        this.cantidad= 0.0;
    }

    @Override
    public void pagar(InformacionPago informacionPago) {
        // Validar que se entrega efectivo suficiente
        if (!validarMetodoPago(this.cantidad, informacionPago)) {
            System.out.println("El pago en efectivo no pudo completarse. El monto es insuficiente.");
            return;
        }

        // Calcular el cambio a entregar
        double montoCobrado = informacionPago.getMonto();
        cantidad -=montoCobrado;


        System.out.println("Pago en efectivo completado exitosamente. Monto del pago: " + informacionPago.getMonto());
        System.out.println("Cambio entregado: " + this.cantidad);
    }

    @Override
    public boolean validarMetodoPago(double disponible, InformacionPago informacionPago) {
        // Asegurarse de que se entrega suficiente efectivo para cubrir el costo
        double montoCosto = informacionPago.getMonto();
        if (disponible < montoCosto) {
            System.out.println("El efectivo entregado es insuficiente para cubrir el costo.");
            return false;
        }
        return true;
    }

    public double getCashDisponible() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    public void agregarCash(double monto){
        this.cantidad+=monto;
    }
}
