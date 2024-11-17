package Tickets.FormatoTickets;

/**
 * Clase en la que se asocia toda la información referente a los vuelos redondos, este tipo de vuelos se caracterizan por tener tanto un vuelo 
 * de ida al destino como un vuelo de regreso al origen separados por un periodo de tiempo definido por el administrador, el precio de estos
 * vuelos se recomienda ser mayor al de un vuelo simple debido a que se contemplan dos vuelos en lugar de uno solo.
 */
public class VueloRedondo extends Vuelo{

    public VueloRedondo(String destino, String origen, double precioStandard, double precioPremium, double precioVIP, int ticketsStandard, int ticketsPremium, int ticketsVIP) {
        super(destino, origen, precioStandard, precioPremium, precioVIP, ticketsStandard, ticketsPremium, ticketsVIP);
    }

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
     *   - Tiempo en días entre los dos vuelos (ida y regreso).
     */
    @Override
    public String mostrarInformacionCompra() {
        return "==========================================\n"+
        "   Resumen de su compra:\n"+
        "   Origen: "+origen+"\n"+
        "   Destino: "+destino+"\n"+
        "   Fecha de vuelo: "+fecha+"\n"+
        "   Precio: "+precioStandard+"\n"+
        "   Tiempo entre vuelos: "+tiempoDias+" días\n"+
        "==========================================\n";
    }


    
}
