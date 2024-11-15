package SistemaDeMenus;

import java.util.Scanner;

/**
 * Plantilla general de los diferentes tipos de menú que compartiran las 2 variables de clase tanto para ingresar datos como para manejar
 * excepciones dentro de los métodos.
 */
public class Menu {
    protected static Scanner scanner = new Scanner(System.in);
    protected static boolean incorrectEntry;
}
