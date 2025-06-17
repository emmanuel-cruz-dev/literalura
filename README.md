# 📚 LiterAlura

LiterAlura es una aplicación de consola desarrollada en Java que permite gestionar un catálogo personal de libros. El proyecto forma parte del challenge propuesto por AluraLatam.

## 🚀 Características
- **Realiza peticiones a la API externa de libros Gutendex**.
- **Mapea los resultados JSON a objetos Java utilizando Jackson**.
- **Almacena los datos en una base de datos PostgreSQL mediante JPA/Hibernate**.
- **Proporciona un menú interactivo para gestionar el catálogo de libros**.

## 🛠️ Tecnologías
- **Java 17+**: Lenguaje de programación principal.  
- **Spring Boot**: Framework para la configuración y gestión de dependencias.  
- **Jackson**: Deserialización de JSON a objetos Java.  
- **JPA/Hibernate**: ORM para persistencia de datos.  
- **PostgreSQL**: Base de datos relacional.  
- **Maven**: Gestor de dependencias y contrucción del proyecto.

## ✨ Menú del Sistema
Al ejecutar la aplicación, se presenta un menú interactivo con las siguientes opciones:

- Buscar libro por título - Busca libros en la API externa y los guarda en la base de datos.
- Listar libros registrados - Muestra todos los libros almacenados localmente.
- Listar autores registrados - Lista todos los autores en la base de datos.
- Listar autores vivos en un determinado año - Filtra autores que estaban vivos en un año específico.
- Listar libros por idioma - Muestra libros filtrados por código de idioma (en, es, fr, pt, etc.).

## 🌐 API Utilizada
Este proyecto utiliza [Gutendex](https://gutendex.com/) para obtener y manejar una lista de libros.
