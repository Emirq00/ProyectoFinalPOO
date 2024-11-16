package Pagos;

import Cuentas.Usuario;
import Tickets.FormatoTickets.Ticket;

public class Transferencia {
    private Ticket boleto;
    private TarjetaCredito cuentaDestino;
    private String estado;

    /**
     * Inicia una transferencia asociando un boleto y una cuenta destino.
     *
     * @param boleto        El boleto que se transfiere.
     * @param destinatario  El usuario que recibe la transferencia.
     */
    public void iniciarTransferencia(Ticket boleto, Usuario destinatario) {
        if (boleto == null || destinatario == null) {
            throw new IllegalArgumentException("El boleto y el destinatario no pueden ser nulos.");
        }

        this.boleto = boleto;
        this.cuentaDestino = null;

        // Buscar la primera instancia de TarjetaCredito
        for (MetodoPago x : destinatario.getMetodosPagos()) {
            if (x instanceof TarjetaCredito) {
                this.cuentaDestino = (TarjetaCredito) x;
                break; // Salir del bucle al encontrar una TarjetaCredito
            }
        }

        if (this.cuentaDestino == null) {
            throw new IllegalStateException("El destinatario no tiene un método de pago válido (Tarjeta de Crédito).");
        }

        this.estado = "Pendiente";
        System.out.println("Transferencia iniciada. Boleto asociado: ");
        boleto.mostrarInformacion();
    }


    /**
     * Acepta y completa la transferencia.
     * Actualiza el estado del boleto y de la transferencia.
     */
    public void aceptarTransferencia() {
        if (!"Pendiente".equals(this.estado)) {
            throw new IllegalStateException("La transferencia no está en estado pendiente.");
        }

        // Simular alguna lógica de transferencia, como descontar saldo de la cuenta origen
        // Aquí podrías realizar validaciones adicionales
        this.boleto.setPropietario(this.cuentaDestino.getPropietario());
        this.estado = "Completada";

        System.out.println("Transferencia aceptada. El boleto pertenece a: "
                + this.cuentaDestino.getPropietario().getNombre());
    }
}
