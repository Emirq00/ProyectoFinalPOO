package Eventos;

import Organizacion.Asiento;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public abstract class Evento implements EventoBase{
    private String titulo;
    private String tipo;
    private LocalDate fecha;
    private String ubicacion;
    private List<String> participantes;
    private int precio;
    private Map<Asiento, Boolean> asientosDisponibles;
    //favorito
    //Ese we

    @Override
    public String mostrarInfo() {
        return "";
    }

    @Override
    public void marcarFavorito() {
        //implementacion
    }

    public Evento buscarEventoPorFiltro(List<Filtro> filtros){
        //implementacion
    }


}
