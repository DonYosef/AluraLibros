package com.aluraLibros.principal;

import com.aluraLibros.models.DatosLibros;
import com.aluraLibros.models.ListaDeLibros;
import com.aluraLibros.services.ConsumoAPI;
import com.aluraLibros.services.ConvierteDatos;

import javax.xml.crypto.Data;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    ConsumoAPI consumo = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();
    String URL_BASE = "https://gutendex.com/books/";
    Scanner teclado = new Scanner(System.in);

    public void mostrarMenu(){

        var json = consumo.obtenerDatos(URL_BASE);
        var datos = conversor.obtenerDatos(json, ListaDeLibros.class);
        System.out.println(datos);

        System.out.println("<--- TOP 5 libros más descargados --->");
        datos.arrayLibros().stream()
                .sorted(Comparator.comparing(DatosLibros::totalDescargas).reversed())
                .limit(5)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);


        System.out.println("Ingrese titulo del libro a buscar: ");
        var tituloTeclado = teclado.nextLine();

        json = consumo.obtenerDatos(URL_BASE+"?search="+tituloTeclado.replace(" ","+"));
        var datosTitulo = conversor.obtenerDatos(json, ListaDeLibros.class);

        Optional<DatosLibros> resultadoTitulo = datosTitulo.arrayLibros().stream()
                                .filter(l -> l.titulo().toUpperCase().contains(tituloTeclado.toUpperCase()))
                                .findFirst();

        if(resultadoTitulo.isPresent()){
            System.out.println("Titulo encontrado: "+ resultadoTitulo.get());
        }else{
            System.out.println("Titulo no encontrado :(");
        }

        System.out.println("<--- Estadisticas --->");
        IntSummaryStatistics est = datos.arrayLibros().stream()
                .mapToInt(DatosLibros::totalDescargas)
                .filter(d -> d > 0)
                .summaryStatistics();
        System.out.println("Total de datos: "+est.getCount());
        System.out.println("Suma: "+est.getSum());
        System.out.println("Promedio: "+est.getAverage());
        System.out.println("Maximo: "+est.getMax());
        System.out.println("Minimo: "+est.getMin());

    }
}

