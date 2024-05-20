package com.aluraLibros.principal;

import com.aluraLibros.models.Autor;
import com.aluraLibros.models.DatosLibros;
import com.aluraLibros.models.Libro;
import com.aluraLibros.models.ListaDeLibros;
import com.aluraLibros.repository.RepositoryLibros;
import com.aluraLibros.services.ConsumoAPI;
import com.aluraLibros.services.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    ConsumoAPI consumo = new ConsumoAPI();
    ConvierteDatos conversor = new ConvierteDatos();
    String URL_BASE = "https://gutendex.com/books/";
    Scanner teclado = new Scanner(System.in);

    String json;
    DatosLibros datos;

    private final RepositoryLibros repositorio;


    public Principal(RepositoryLibros repositorio){
        this.repositorio = repositorio;
    }



    private void buscarLibroPorTitulo () {
        System.out.println("Ingrese titulo del libro a buscar: ");
        var tituloTeclado = teclado.nextLine();
        json = consumo.obtenerDatos(URL_BASE+"?search="+tituloTeclado.replace(" ","+"));
        ListaDeLibros datosTitulo = conversor.obtenerDatos(json, ListaDeLibros.class);

        if (datosTitulo != null && datosTitulo.arrayLibros() != null){

            Optional<DatosLibros> resultadoLibro = datosTitulo.arrayLibros().stream()
                    .filter(t -> t.titulo() != null && t.titulo().toLowerCase().contains(tituloTeclado.toLowerCase()))
                    .findFirst();

            if (resultadoLibro.isPresent()){

                DatosLibros datosLibro = resultadoLibro.get();

                Libro libro = new Libro();
                libro.setTitulo(datosLibro.titulo());

                List<Autor> autores = datosLibro.arrayAutor().stream()
                                .map(da -> new Autor(da.nombre(),da.fecha_nacimiento(),da.fecha_fallecimiento()))
                                        .collect(Collectors.toList());
                libro.setArrayAutor(autores);
                libro.setIdiomas(datosLibro.idiomas());
                libro.setTotalDescargas(datosLibro.totalDescargas());

                System.out.println("Titulo encontrado: " + libro.toString());
                repositorio.save(libro);
                System.out.println("El titulo ha sido almacenado en la base de datos...");

            }else{
                System.out.println("El Titulo ingresado no fue encontrado");
            }

        }else{
            System.out.println("No se encontraron resultados");
        }
    }

    private void buscarLibroPorAutor() {
        System.out.println("Ingrese nombre del autor del libro a buscar: ");
        var autorTeclado = teclado.nextLine();
        json = consumo.obtenerDatos(URL_BASE+"?search="+ autorTeclado.replace(" ","+"));
        ListaDeLibros datosTitulo = conversor.obtenerDatos(json, ListaDeLibros.class);

        Optional<DatosLibros> encontrado = datosTitulo.arrayLibros().stream().findFirst();

        if (encontrado.isPresent()){

            DatosLibros datosLibro = encontrado.get();

            List<Autor> autors = encontrado.get().arrayAutor().stream()
                    .map(da -> new Autor(da.nombre(),da.fecha_nacimiento(),da.fecha_fallecimiento()))
                    .collect(Collectors.toList());

            Libro libro = new Libro();
            libro.setTitulo(datosLibro.titulo());
            libro.setArrayAutor(autors);
            libro.setIdiomas(datosLibro.idiomas());
            libro.setTotalDescargas(datosLibro.totalDescargas());

            System.out.println("Titulo encontrado: " + libro);
            repositorio.save(libro);
            System.out.println("El titulo ha sido almacenado en la base de datos...");
        }else{
            System.out.println("Autor no encontrado :(");
        }

    }

    private List<Libro> todosLosLibros() {
        List<Libro> libros = repositorio.findAll();
        libros.forEach(System.out::println);
        return libros;
    }

    private List<Autor> todosLosAutores() {
        List<Autor> autores = repositorio.findAllAutores();
        autores.forEach(System.out::println);
        return autores;
    }

    private void autoresVivosEn() {

        System.out.println("Ingrese año hasta el cual el autor se encontraba con vida: ");
        int fecha = Integer.parseInt(teclado.nextLine());

        List<Autor> listaAutoresVivos = repositorio.consultarAutoresVivos(fecha);
        listaAutoresVivos.forEach(System.out::println);
    }

    private void librosPorIdioma() {
        System.out.println("Ingrese idioma por el cual desea filtrar: ");
        String idiomaSeleccionado = teclado.nextLine();
        List<Libro> listado = repositorio.listaDelIdioma(idiomaSeleccionado);
        listado.forEach(System.out::println);
    }

    public void mostrarMenu() {

        String menu = """
                    \n
                    Digite una opción a continuación: 
                    1 - Buscar libro por Titulo
                    2 - Buscar libro por Autor
                    3 - Libros de mi colección
                    4 - Autores en mi coleccion
                    5 - Autores vivos en un determinado año
                    6 - Filtrar libros por idioma
                    0 - Salir del programa
                """;

        int opcion = -1;
        System.out.println("¡Bienvenido a la Bibliteca Web más grande de internet!");
        while (opcion != 0) {
            System.out.println(menu);
            int eleccionMenu = Integer.parseInt(teclado.nextLine());
            switch (eleccionMenu) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    buscarLibroPorAutor();
                    break;
                case 3:
                    todosLosLibros();
                    break;
                case 4:
                    todosLosAutores();
                    break;
                case 5:
                    autoresVivosEn();
                    break;
                case 6:
                    librosPorIdioma();
                    break;
                case 0:
                    opcion = 0;
                    break;
            }
        }
    }






/*


        System.out.println("<--- TOP 5 libros más descargados --->");
        datos.arrayLibros().stream()
                .sorted(Comparator.comparing(DatosLibros::totalDescargas).reversed())
                .limit(5)
                .map(l -> l.titulo().toUpperCase())
                .forEach(System.out::println);






        System.out.println("<--- Estadisticas --->");
        IntSummaryStatistics est = datos.arrayLibros().stream()
                .mapToInt(DatosLibros::totalDescargas)
                .filter(d -> d > 0)
                .summaryStatistics();
        System.out.println("Total de datos: "+est.getCount());
        System.out.println("Suma: "+est.getSum());
        System.out.println("Promedio: "+est.getAverage());
        System.out.println("Maximo: "+est.getMax());
        System.out.println("Minimo: "+est.getMin());*/

}




