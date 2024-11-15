package Pagos;

import Organizacion.Boleto;

import java.time.LocalDate;

public class TarjetaCredito extends MetodoPago{
    private int numeroTarjeta;
    private LocalDate fechaExpiracion;
    private int cvv;

    @Override
    public void pagar(double monto) {
        //implememtacion
    }


}
