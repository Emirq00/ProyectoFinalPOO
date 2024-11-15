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

    public void iniciarCompra(Evento evento, List<Asiento> asientos){
        //implementacion
    }

    public int calcularCostoTotal(){
        return -1;
        //implementacion
    }

    public void generarResumenCompra(){
        //implementacion
    }

    public void confirmarCompra(){
        //implementacion
    }

    public void cancelarCompra(LocalDate FechaEvento){
        //implementacion
    }


}
