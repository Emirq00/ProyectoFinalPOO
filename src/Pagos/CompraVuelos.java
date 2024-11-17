package Pagos;

import Tickets.FormatoTickets.Vuelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class CompraVuelos {
    public static void MenuCompra(Scanner cin){
        System.out.println("Indique el ID del vuelo a comprar: ");
        int ID= cin.nextInt();
        boolean flag=false;
        for(int x: Vuelo.getIDList()){
            if(x==ID){
                flag=true;
                break;
            }
        }
        if(flag){
            System.out.println("==== Ingrese sus datos ====");
            System.out.println("Numero de tarjeta: ");
            int numTarjeta= cin.nextInt();
            System.out.println("Ingrese los tres numeros posteriores de su trajeta (cvv): ");
            int cvv=cin.nextInt();
            System.out.println("Nombre del propietario: ");
            String name= cin.nextLine();
            System.out.println("Ingrese la fecha de expiración de la tarjeta (formato: dd-MM-yyyy): ");
            String fecha= cin.nextLine();
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern(fecha);
            LocalDate date=LocalDate.parse(fecha, formatter);
            if()
        }
        else {
            System.out.println("Lo sentimos, vuelo no encontrado");
        }
    }
}
