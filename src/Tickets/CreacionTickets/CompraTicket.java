package Tickets.CreacionTickets;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.Scanner;
import Tickets.FormatoTickets.*;

public class CompraTicket{

    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ObjectOutputStream fileOut;
        Vuelo vuelo = new VueloRedondo(), vuelo2 = new VueloRedondo(), vuelo3 = new VueloRedondo(), vuelo4 = new VueloRedondo(), vuelo5 = new VueloRedondo();
        vuelo.setDestino("Japon");
        vuelo.setFecha(LocalDateTime.now());
        vuelo.setOrigen("Mexico");
        vuelo.setPrecio(10000);
        vuelo.setTipoDeVuelo("Redondo");
        try {
            fileOut = new ObjectOutputStream(new FileOutputStream("Vuelos"));
            fileOut.writeObject(vuelo);
            vuelo2.setDestino("Uruguay");
            vuelo2.setFecha(LocalDateTime.now());
            vuelo2.setOrigen("Argentina");
            vuelo2.setPrecio(56000);
            vuelo2.setTipoDeVuelo("Redondo");
            
            fileOut.writeObject(vuelo2);
            vuelo3.setDestino("Argelia");
            vuelo3.setFecha(LocalDateTime.now());
            vuelo3.setOrigen("Mexico");
            vuelo3.setPrecio(23000);
            vuelo3.setTipoDeVuelo("Redondo");

            fileOut.writeObject(vuelo3);
            vuelo4.setDestino("Estados Unidos");
            vuelo4.setFecha(LocalDateTime.now());
            vuelo4.setOrigen("Canada");
            vuelo4.setPrecio(17000);
            vuelo4.setTipoDeVuelo("Redondo");

            fileOut.writeObject(vuelo4);
            vuelo5.setDestino("China");
            vuelo5.setFecha(LocalDateTime.now());
            vuelo5.setOrigen("India");
            vuelo5.setPrecio(21000);
            vuelo5.setTipoDeVuelo("Redondo");

            fileOut.writeObject(vuelo);

            fileOut.close();
        } 
        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        SistemaDeMenus.MenuPrincipalCliente.menuPrincipal();
    }
}
