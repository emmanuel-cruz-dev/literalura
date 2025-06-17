package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true)
    private String nombre;
    private  Integer anioNacimiento;
    private Integer anioFallecimiento;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor autores){
        this.nombre = autores.nombre();

        if(autores.anioNacimiento() == null){
            this.anioNacimiento = -1;
        } else {
            this.anioNacimiento = autores.anioNacimiento();
        }

        if(autores.anioFallecimiento() == null){
            this.anioFallecimiento = 5000;
        } else {
            this.anioFallecimiento = autores.anioFallecimiento();
        }
    }

    public Autor(String nombre){
        this.nombre = nombre;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNacimiento() {
        return anioNacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.anioNacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return anioFallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.anioFallecimiento = fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString(){
        return nombre;
    }
}
