package com.alura.literalura.model;

import jakarta.persistence.*;

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

    @OneToOne
    @JoinTable(
            name = "libros",
            joinColumns = @JoinColumn(name = "autores_id"),
            inverseJoinColumns = @JoinColumn(name = "Id")
    )

    private Libro libros;

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

    @Override
    public String toString(){
        return nombre + "(" + anioNacimiento + " / " + anioFallecimiento + ")";
    }

    public Libro getLibros() {
        return libros;
    }

    public void setLibros(Libro libros) {
        this.libros = libros;
    }
}
