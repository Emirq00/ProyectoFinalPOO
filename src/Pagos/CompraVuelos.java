package Pagos;

import Tickets.FormatoTickets.Vuelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
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
            int opcion=-1;
            do{
                try{
                    System.out.println("==== Seleccione el metodo de pago ====");
                    System.out.println("1) Efectivo");
                    System.out.println("2) Tarjeta de crédito o débito");
                    System.out.println("3) Transferencia");
                    System.out.println("4) Salir del menú de compra");
                    opcion=cin.nextInt();

                    switch (opcion){
                        case 1:
                            //menuEfectivo
                            break;
                        case 2:
                            //menuTarjeta
                            break;
                        case 3:
                            //menuTransferencia
                            break;
                        case 4:
                            System.out.println("Saliendo del menú de compra");
                            break;
                        default:
                            System.out.println("Opción inválida");
                    }
                }catch (InputMismatchException e){
                    System.out.println("Lo sentimos, solo se deben ingresar números");
                }
            }while (opcion!=4);
        }
        else {
            System.out.println("Lo sentimos, vuelo no encontrado");
        }
    }

    public static void menuEfectivo(){

    }

    public static void menuTarjeta(Scanner cin){
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

    }

    public static void menuTransferencia(){

    }
}
