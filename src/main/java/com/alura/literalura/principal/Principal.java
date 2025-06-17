package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Datos;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.ConsumoAPI;
import com.alura.literalura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository)  {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public Principal() {
    }

    public void mostrarMenu() {
        var opcion = -1;

        while (opcion != 0) {
            var menu =  """
                        -------------
                        Elija la opción a través de su número
                        1 - Buscar libro por título.
                        2 - Listar libros registrados.
                        3 - Listar autores registrados.
                        4 - Listar autores vivos en un determinado año.
                        5 - Listar libros por idioma.
                        0 - Salir.
                        """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    buscarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción incorrecta...");

            }
        }
    }

    private Datos getDatosLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();
        tituloLibro = tituloLibro.replace(" ", "%20");
        var json = consumoApi.obtenerDatos(URL_BASE + tituloLibro);
        Datos datos = conversor.obtenerDatos(json, Datos.class);

        if (datos.contador() == 0 || datos.resultados().isEmpty() || datos.resultados().get(0).titulo() == null) {
            System.out.println("No se encontró el libro");
            return null;
        }
        return datos;
    }

    private void buscarLibroPorTitulo() {
        Datos datos = getDatosLibro();

        if (datos != null) {
            Libro libro = new Libro(datos.resultados().get(0));

            if (libroRepository.findByTituloContainingIgnoreCase(libro.getTitulo()).isPresent()) {
                System.out.println("El Libro ya se encuentra registrado");
            } else {
                libro = libroRepository.save(libro);
                System.out.println("Libro nuevo agregado 📚 : " + libro.getTitulo());
            }
        }
    }

    private void buscarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        System.out.println("Lista de libros");
        if (libros.isEmpty()) {
            System.out.println("❗ No hay libros registrados.");
            return;
        }

        for (Libro libro : libros) {
            System.out.println("---- LIBRO ----");
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutores());
            System.out.println("Idioma: " + String.join(", ", libro.getIdiomas()));
            System.out.println("Número de descargas: " + libro.getDescargas());
            System.out.println("----------------\n");
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("❗No hay autores registrados.");
            return;
        }

        for(Autor autor : autores) {
            System.out.println("Autor: " + autor.getNombre());
            System.out.println("Fecha de nacimiento: " + (autor.getNacimiento() != null ? autor.getNacimiento() : "Desconocido"));
            System.out.println("Fecha de fallecimiento: " + (autor.getFallecimiento() != null ? autor.getFallecimiento() : "Desconocido"));
            System.out.println("Libros: " + autor.getLibros().getTitulo() + "\n");
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar");
        var fecha = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autores = autorRepository.findForYear(fecha);

        System.out.println("Lista de autores");

        if (autores.isEmpty()) {
            System.out.println("❗No hay autores registrados.");
            return;
        }

        for(Autor autor : autores) {
            System.out.println("Autor: " + autor.getNombre());
            System.out.println("Fecha de nacimiento: " + (autor.getNacimiento() != null ? autor.getNacimiento() : "Desconocido"));
            System.out.println("Fecha de fallecimiento: " + (autor.getFallecimiento() != null ? autor.getFallecimiento() : "Desconocido"));
            System.out.println("Libros: " + autor.getLibros().getTitulo() + "\n");
        }
    }

    private void listarLibrosPorIdioma() {
        var seleccion = -1;
        var idiomaABuscar = "en";
        while(seleccion != 0) {
            var menuIdioma = """
                    Ingresa el idioma para buscar los libros
                    1 - Español (es).
                    2 - Inglés (en).
                    3 - Francés (fr).
                    4 - Portugués (pt).
                    0 - Salir de la búsqueda.
                    """;

            System.out.println(menuIdioma);
            seleccion = teclado.nextInt();
            teclado.nextLine();

            switch (seleccion) {
                case 1:
                    idiomaABuscar = "es";
                    break;
                case 2:
                    idiomaABuscar = "en";
                    break;
                case 3:
                    idiomaABuscar = "fr";
                    break;
                case 4:
                    idiomaABuscar = "pt";
                    break;
                case 0:
                    System.out.println("Saliendo de la búsqueda por idioma.");
                    return;
                default:
                    System.out.println("Error, seleccione una opción válida.");
                    continue;
            }

            List<Libro> libros = libroRepository.buscarPorIdioma(idiomaABuscar);

            if (libros.isEmpty()) {
                System.out.println("Aún no hay libros registrados en ese idioma.");
                continue;
            }

            for (Libro libro : libros) {
                System.out.println("--------------");
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutores());
                System.out.println("Idioma: " + String.join(", ", libro.getIdiomas()));
                System.out.println("Número de descargas: " + libro.getDescargas() + "\n");
            }
        }
    }
}