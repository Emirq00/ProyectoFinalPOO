package Organizacion;

import Eventos.Evento;
import Pagos.MetodoPago;

import java.time.LocalDate;
import java.util.List;

public class Compra {
    private List<Boleto> boletos;
    private MetodoPago metodoPago;
    private int costoTotal;
    private int cargosAdicionales;
    private String estado;

    //Dentro de la clase MenuPrincipalCliente hay un método que nos debe permitir hacer la compra de los boletos, entonces 
    //ahi se hará la conexión entre el menú y esta clase
    
    public void iniciarCompra(Evento evento, List<Asiento> asientos){
        //implementacion
    }

    public int calcularCostoTotal(){
        return -1;
        //implementacion
    }

    public void generarResumenCompra(){
        //Dentro de la implementación de los tickets, hay un método que se encarga de imprimir el resumen de la compra
    }

    public void confirmarCompra(){
        //implementacion
    }

    public void cancelarCompra(LocalDate FechaEvento){
        //implementacion
    }


}
