package com.alura.rggt.librosalura.template;

import com.alura.rggt.librosalura.modelos.Autor;
import com.alura.rggt.librosalura.modelos.Datos;
import com.alura.rggt.librosalura.modelos.Libros;
import com.alura.rggt.librosalura.service.ConsumoAPI;
import com.alura.rggt.librosalura.service.ConvierteDatos;


import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

public class Menu extends MenuTemplate{
    private static final String URL_BASE = "https://gutendex.com/books/";
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();
    String json = consumoAPI.obtenerDatos(URL_BASE);
    Datos datos = conversor.obtenerDatos(json, Datos.class);

    public void primero(){
        System.out.println("---------------------LIBROS--------------------------");
        datos.resultados().stream().map(
          ti -> ti.titulo().toUpperCase()
        ).forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void top(){
        System.out.println("¿De cuantos libros quieres tu top?");
        int topSelect = leer.nextInt();
        leer.nextLine();
        System.out.println("---------------------TOP " + topSelect + "--------------------------");
        datos.resultados().stream()
                // Comparamos de mayor a menor(reversed)
                .sorted(Comparator.comparing(Libros::numeroDeDescargas).reversed())
                .limit(topSelect) // Solo 5
                // Mapeo para modificar lo que se mostrara
                .map(li -> ("\n") +
                        "Titulo: " + li.titulo().toUpperCase()
                        + "\nAutor: " + li.autor().stream()
                        // Otra forma de decir por ejemplo a -> a.nombre()
                        .map(Autor::nombre)
                        .collect(Collectors.joining(", "))
                        + "\nDescargas: " + li.numeroDeDescargas() +
                        ("\n"))
                .forEach(System.out::println);
        System.out.println("-----------------------------------------------------");
    }

    @Override
    public void buscar() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = leer.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE+"?search=" + tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        //Optional en caso de que no se encuentre nada
        Optional<Libros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro Encontrado ");
            System.out.println(libroBuscado.get());
        }else {
            System.out.println("Libro no encontrado");
        }
    }

    @Override
    public void stats() {
        // Se crea un objeto DoubleSummaryStatistics para obtener estadísticas sobre el número de descargas de libros.
        DoubleSummaryStatistics est = datos.resultados().stream()
                .filter(d -> d.numeroDeDescargas() >0 )
                .collect(Collectors.summarizingDouble(Libros::numeroDeDescargas));
        System.out.println("Cantidad media de descargas: " + est.getAverage());
        System.out.println("Cantidad máxima de descargas: "+ est.getMax());
        System.out.println("Cantidad mínima de descargas: " + est.getMin());
        System.out.println("Cantidad de registros evaluados para calcular las estadisticas: " + est.getCount());
    }
}

