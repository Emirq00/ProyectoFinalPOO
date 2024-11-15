package Eventos;

import java.time.LocalDate;
import java.util.List;

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
