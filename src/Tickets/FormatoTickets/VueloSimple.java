package Tickets.FormatoTickets;

/**
 * Clase en la que se asocia toda la información referente a los vuelos simples, este tipo de vuelos se caracterizan por tener 
 * unicamente un vuelo del origen al destino. El precio de estos vuelos queda a criterio del administrador.
 */
public class VueloSimple extends Vuelo{

    /**
     * Método en el que retorna toda la información de una compra de un ticket mediante un formato específico por lo que solamente se 
     * recomienda utilizarse cuando la compra del ticket esté por finalizar. La información retornada incluye:
     * <p>
     *   - Origen
     * <p>
     *   - Destino 
     * <p>
     *   - Fecha del vuelo
     * <p>
     *   - Precio
     * <p>
     */
    @Override
    public String mostrarInformacionCompra() {
        return "==========================================\n"+
        "   Resumen de su compra:\n"+
        "   Origen: "+origen+"\n"+
        "   Destino: "+destino+"\n"+
        "   Fecha de vuelo: "+fecha+"\n"+
        "   Precio: "+precio+"\n"+
        "==========================================\n";
    }

    
}
