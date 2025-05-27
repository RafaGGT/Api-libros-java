package com.alura.rggt.librosalura.template;

import com.alura.rggt.librosalura.interfaces.ILector;

public abstract class MenuTemplate implements ILector {
    String op;

    public abstract void primero();
    public abstract void top();
    public abstract void buscar();
    public abstract void stats();

    public final void iniciarMenu() {
        do {
            System.out.println("---------------------------------------------------");
            System.out.println("---- Api Bibliotecas----");
            System.out.println("1. Lista de textos");
            System.out.println("2. Ver Tops de libros");
            System.out.println("3. Busca un libro");
            System.out.println("4. Estadisticas");
            System.out.println("0. Exit");
            System.out.println("Selecciona una opcion: ");
            op = leer.nextLine();
            System.out.println("-----------------------------------------------------");
            switch (op) {
                case "1":
                    primero();
                    break;
                case "2":
                    top();
                    break;
                case "3":
                    buscar();
                    break;
                case "4":
                    stats();
                    break;
                case "0":
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Please choose a valid option.");
            }
        } while (!op.equals("0"));
    }
}
