package com.alura.literalura.model;

import jakarta.persistence.*;

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

    @Column(name = "idLibro", unique = true)
    private Integer idLibro;

    @Column(name = "idiomas")
    private List<String> idiomas;
    private Integer numeroDeDescargas;

    @OneToOne(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Autor autores;

    public Libro(){}

    public Libro(DatosLibro libroDatos) {
        this.idLibro = libroDatos.idLibro();
        this.titulo = libroDatos.titulo();
        this.numeroDeDescargas = libroDatos.numeroDeDescargas();

        if(!libroDatos.idiomas().isEmpty()) {
            this.idiomas = libroDatos.idiomas();
        }

        if(!libroDatos.autores().isEmpty()) {
            for(DatosAutor autorDatos : libroDatos.autores()) {
                this.autores = new Autor(autorDatos);
                break;
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

    public Autor getAutores() {
        return autores;
    }

    public void setAutores(Autor autores) {
        this.autores = autores;
    }

    @Override
    public String toString() {
        return """
            📖 Libro
            ─────────────────────────────────────────────
            ID:           %d
            Título:       %s
            Autor(es):   %s
            Idioma(s):    %s
            Descargas:   %d
            """.formatted(idLibro, titulo, autores, idiomas, numeroDeDescargas);
    }
}
