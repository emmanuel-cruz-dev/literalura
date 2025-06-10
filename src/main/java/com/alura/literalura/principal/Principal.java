package com.alura.literalura.principal;

import com.alura.literalura.model.Datos;
import com.alura.literalura.model.DatosLibros;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner keyboard = new Scanner(System.in);
    public int selectedOption = 8;
    public String menuList = """
            Elija la opción a través de su número:
            1 - Buscar libro por título
            2 - Listar libros registrados
            3 - Listar autores registrados
            4 - Listar autores vivos en un determinado año
            5 - Listar libros por idioma
            0 - Salir
            """;

    public void showsMenu(){

        while (selectedOption != 0){
            System.out.println(menuList);
            selectedOption = keyboard.nextInt();
            keyboard.nextLine();

            switch (selectedOption){
                case 1:
                    searchBooksTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listLivingAuthorsByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private void searchBooksTitle() {
        System.out.println("Opción: 1 - Ingrese el nombre del libro que desea buscar");
        var tituloLibro = keyboard.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(tituloLibro.toLowerCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado");
        }
        return;
    }

    private void listRegisteredBooks() {
        System.out.println("Opción: 2 - Lista de libros registrados");
        return;
    }

    private void listRegisteredAuthors() {
        System.out.println("Opción: 3 - Lista de autores registrados");
        return;
    }

    private void listLivingAuthorsByYear() {
        System.out.println("Opción: 4 - Lista de autores vivos en un determinado año");
        return;
    }

    private void listBooksByLanguage(){
        System.out.println("Opción 5 - Lista de libros por idioma");
        return;
    }

    public void muestraElMenu(){
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);

        // Top 10 libros más descargados
        System.out.println("Top 10 libros más descargados");
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(l -> l.titulo())
                .forEach(System.out::println);

        // Búsqueda de libros por nombre
        System.out.println("Ingresa el nombre del libro que deseas buscar");
        var tituloLibro = keyboard.nextLine();
        json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(tituloLibro.toLowerCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            System.out.println("Libro encontrado");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("Libro no encontrado");
        }

        // Trabajando con estadísticas
        DoubleSummaryStatistics est = datos.resultados().stream()
                .filter(d -> d.numeroDeDescargas() > 0)
                .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));
        System.out.println("Cantidad media de descargas: " + est.getAverage());
        System.out.println("Cantidad máxima de descargas: " + est.getMax());
        System.out.println("Cantidad mínima de descargas: " + est.getMin());
        System.out.println("Cantidad de registros evaluados para calcular las estadísticas: " + est.getCount());
    }
}
