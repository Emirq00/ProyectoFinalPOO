package Tickets.FormatoTickets;

import Cuentas.Cliente;
import Cuentas.Usuario;

/**
 *  Clase abstracta en la que definimos la estructura principal de los diferentes tickets de compra que podrán ser comprados al momento 
 *  de reservar los vuelos. La clase permite generar tickets standard, premium y vip.
 */
public abstract class Ticket{

    private Usuario propietario;
    protected String tipoTicket;
    //Bridge
    protected Vuelo vuelo;

    public Usuario getPropietario() {
        return propietario;
    }

    public void setPropietario(Usuario propietario) {
        this.propietario = propietario;
    }

    /**
     * Constructor en el que vamos a generar el puente entre los tickets y los diferentes vuelos que podemos generar en el programa, este 
     * constructor nos permite asociar cualquier ticket con cualquier vuelo.
     * @param vuelo Variable de tipo vuelo en el que asociaremos el vuelo al ticket.
     */
    public Ticket(Vuelo vuelo){
        this.vuelo=vuelo;
    }

    /**
     * Método en el que podemos imprimir la información de compra del vuelo en la terminal momentos antes de que el cliente 
     * confirme el pago de su vuelo, el uso recomendable de este método es solamente para esa situación, ya que contiene un formato
     * específico con el que se da a entender que se trata del resumen de una compra.
     */
    public void mostrarInformacion(){
        System.out.println(vuelo.mostrarInformacionCompra());
    }

    /**
     * Método en el que se recupera el vuelo asociado al ticket que se va comprar.
     * @return Variable con toda la información del vuelo asociado al ticket.
     */
    public Vuelo getVuelo() {
        return vuelo;
    }

    /**
     * Método general en el que se definirá cómo se "imprimirán" los tickets dentro de nuestra computadora, ya que cada ticket contendrá
     * su forma de generar la impresión junto con información específica dependiendo la calidad del vuelo y el recorrido que realize. 
     */
    public abstract void imprimirTicket();
    
}
