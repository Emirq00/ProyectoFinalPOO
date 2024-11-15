package Tickets.FormatoTickets;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.*;

/**
 * Clase en la que se imprime la plantilla general de los diferentes tipos de vuelos que se tendrán disponibles dentro del programa, se 
 * tendrán tanto los métodos get como set para cada uno de los diferentes atributos que lo conforman.
 * <p>
 * La información general que tendrán los vuelos son: Precio, Origen, Destino, Tiempo en días entre el primer vuelo de ida y el segundo
 * vuelo de regreso (en caso de que se trate de un vuelo redondo) y la Fecha de compra.
 */
public abstract class Vuelo implements Serializable{
    protected int precio;
    protected String origen;
    protected String destino;
    protected int tiempoDias=0;
    protected LocalDateTime fecha;
    protected String tipoDeVuelo;

    /**
     * @return Retorna el precio asignado al vuelo.
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * Método para asignar el precio de un vuelo al objeto de tipo vuelo, este método tiene como fin el asignar los valores del 
     * atributo del precio sin perder la estructura de bridge y sin utilizar un constructor.
     * @param precio Asigna un valor al precio de un vuelo.
     */
    public void setPrecio(int precio) {
        this.precio=precio;
    }

    /**
     * @return Retorna el lugar de origen del vuelo.
     */
    public String getOrigen() {
        return origen;
    }

    /**
     * Método para asignar el lugar de origen de un vuelo al objeto de tipo vuelo, este método tiene como fin el asignar el valor del 
     * atributo del origen sin perder la estructura de bridge y sin utilizar un constructor.
     * @param origen Asigna un país de origen al vuelo.
     */
    public void setOrigen(String origen) {
        this.origen=origen;
    }

    /**
     * @return Retorna el lugar de dentino del vuelo.
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Método para asignar el lugar de destino de un vuelo al objeto de tipo vuelo, este método tiene como fin el asignar el valor del 
     * atributo del destino sin perder la estructura de bridge y sin utilizar un constructor.
     * @param destino Asigna un país de destino al vuelo.
     */
    public void setDestino(String destino) {
        this.destino=destino;
    }

    /**
     * @return Retorna el tiempo en días entre los dos vuelos, si retorna 0 significa que se trata de un vuelo simple, cualquier valor
     * diferente de 0 implica un vuelo redondo.
     */
    public int getTiempoEntreVuelos(){
        return tiempoDias;
    }

    /**
     * Método para asignar el tiempo en días entre un vuelo y otro, este método tiene como fin el asignar el valor del 
     * atributo del tiempo en días sin perder la estructura de bridge y sin utilizar un constructor.
     * <p>
     * Al no utilizar esta asignación la variable de días por defecto será 0, por lo que se dará a entender que se trata de un vuelo simple.
     * @param tiempoDias Asigna el número de días entre ambos vuelos cuando se trata de un vuelo redondo.
     */
    public void setTiempoEntreVuelos(int tiempoDias){
        this.tiempoDias=tiempoDias;
    }

    /**
     * @return Retorna la fecha asociada al vuelo, esta fecha se retornará con el formato "dd/MM/yyyy a las HH:mm".
     * @throws DateTimeException Cuando durante el cambio de formato de la fecha ocurre algun error.
     */
    public String getFecha() throws DateTimeException{
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy 'a las' HH:mm");
        return fecha.format(formatoFecha);
    }

    /**
     * Método para asignar la fecha asociada al vuelo, este método tiene como fin el asignar el valor del 
     * atributo de la fecha sin perder la estructura de bridge y sin utilizar un constructor.
     * @param fecha
     */
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /**
     * @return Retorna el tipo de vuelo que se está realizando, ya sea un vuelo simple o un vuelo redondo.
     */
    public String getTipoDeVuelo() {
        return tipoDeVuelo;
    }
    /**
     * Método para asignar el tipo de vuelo que se está realizando, ya sea un vuelo simple o un vuelo redondo.
     * @param tipoDeVuelo Tipo de vuelo que se está realizando
     */
    public void setTipoDeVuelo(String tipoDeVuelo) {
        this.tipoDeVuelo = tipoDeVuelo;
    }

    /**
     * Método general en el que se mostrará toda la información asociada a un vuelo.
     * @return Retorna la cadena de cartacteres con toda la información del vuelo.
     */
    public String mostrarInformacionVuelo(){
        return ("Tipo de vuelo: "+tipoDeVuelo+"\t  Fecha: "+getFecha()+"  \t Precio: Desde $"+precio+"\t      Origen: "+origen+"\t      Destino: "+destino);
    }

    /**
     * Método general en el que se mostrará toda la información asociada a la compra de un ticket.
     * @return Retorna toda la información del ticket con un formato en específico.
     */
    public abstract String mostrarInformacionCompra();
}
