package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Integer contador;

    @Column(unique = true)
    private String titulo;

    @Column(name = "id_libro", unique = true)
    private Integer idLibro;

    @Column(name = "idiomas")
    private List<String> idiomas = new ArrayList<>();

    @Column(name = "numero_de_descargas")
    private Integer numeroDeDescargas;

    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    public Libro(){}

    public Libro(DatosLibro libroDatos) {
        this.idLibro = libroDatos.idLibro();
        this.titulo = libroDatos.titulo();
        this.numeroDeDescargas = libroDatos.numeroDeDescargas();

        if(!libroDatos.idiomas().isEmpty()) {
            this.idiomas = new ArrayList<>(libroDatos.idiomas());
        }

        if(!libroDatos.autores().isEmpty()) {
            this.autores = new ArrayList<>();
            for (DatosAutor autorDatos : libroDatos.autores()){
                this.autores.add(new Autor(autorDatos));
            }
        }
    }

    public Integer getContador() {
        return contador;
    }

    public void setContador(Integer contador) {
        this.contador = contador;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getId_libro() {
        return idLibro;
    }

    public void setId_libro(Integer id_libro) {
        this.idLibro = idLibro;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Integer getDescargas() {
        return numeroDeDescargas;
    }

    public void setDescargas(Integer descargas) {
        this.numeroDeDescargas = descargas;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getNombresAutores() {
        if (autores == null || autores.isEmpty()) {
            return "Sin autor";
        }
        return autores.stream()
                .map(Autor::getNombre)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Sin autor");
    }

    @Override
    public String toString() {
        return """
            Libro
            ID: %d
            Título: %s
            Autor(es): %s
            Idioma(s): %s
            Descargas: %d
            """.formatted(idLibro, titulo, autores, idiomas, numeroDeDescargas);
    }
}
