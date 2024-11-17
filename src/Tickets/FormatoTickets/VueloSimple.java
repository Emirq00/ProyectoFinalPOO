package Tickets.FormatoTickets;

import java.time.LocalDateTime;

/**
 * Clase en la que se asocia toda la información referente a los vuelos simples, este tipo de vuelos se caracterizan por tener 
 * unicamente un vuelo del origen al destino. El precio de estos vuelos queda a criterio del administrador.
 */
public class VueloSimple extends Vuelo{

    public VueloSimple(String origen, String destino, LocalDateTime fecha, int precio){
        this.tipoDeVuelo="Simple";
        this.origen=origen;
        this.destino=destino;
        this.fecha=fecha;
        this.precio=precio;
        this.tiempoDias=0;
        //Inicializado de los asientos y sus rangos
        String [] letras={"A","B","C","D","E","F","G","H","I","J"};
        for(int i=0;i<9;i++){
            for(int j=0;j<20;j++){
                if(j<5){
                    asientosDisponibles.put(letras[i]+j, 3);
                } else if (j<10){
                    asientosDisponibles.put(letras[i]+j, 2);
                } else{
                    asientosDisponibles.put(letras[i]+j, 1);
                }
            }
        }
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
     */
    @Override
    public String mostrarInformacionCompra(String asiento) {
        return "==========================================\n"+
        "   Resumen de su compra:\n"+
        "   Tipo de vuelo: "+tipoDeVuelo+"\n"+
        "   Origen: "+origen+"\n"+
        "   Destino: "+destino+"\n"+
        "   Asiento: "+ asiento +"\n"+ 
        "   Fecha de vuelo: "+getFecha()+"\n"+
        "   Precio: "+precio+"\n"+
        "==========================================\n";
    }

    
}
