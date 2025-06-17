# 📚 LiterAlura

LiterAlura es una aplicación de consola desarrollada en Java que permite gestionar un catálogo personal de libros. El proyecto forma parte del challenge propuesto por AluraLatam.

## 🚀 Características
- **Realiza peticiones a la API externa de libros Gutendex**.
- **Mapea los resultados JSON a objetos Java utilizando Jackson**.
- **Almacena los datos en una base de datos PostgreSQL mediante JPA/Hibernate**.
- **Proporciona un menú interactivo para gestionar el catálogo de libros**.

## 📦 Instalación
1. Clona el repositorio:
   ```bash
   git clone https://github.com/emmanuel-cruz-dev/node-project.git

2. Instala las dependencias:
   ```bash
   npm install

## 🔧 Configuración
Asegúrate de tener:

Node.js (v16+ recomendado).

npm o yarn.

## 💻 Uso
Ejecuta los comandos desde la raíz del proyecto:

1. Obtener todos los productos
   ```bash
   npm run start GET products

2. Obtener un producto específico (ID = 1)
   ```bash
   npm run start GET products/1
   
3. Crear un producto nuevo
   ```bash
   npm run start POST products "Nombre del producto" 19.99 "categoría"
   ```

   Ejemplo:
     ```bash
      npm run start POST products "Camiseta JS" 25 "Ropa para hombres"
     ```

4. Eliminar un producto
   ```bash
   npm run start DELETE products/7

## 📝 Estructura del Código
```
.
├── src/                   # Código fuente principal
│   ├── controllers/       # Controladores de productos
│   │   └── productController.js  # Lógica de endpoints (GET, POST, DELETE)
│   ├── routes/            # Rutas de la API
│   │   └── productRoutes.js       # Definición de rutas (Express)
│   ├── services/          # Servicios externos
│   │   └── apiService.js  # Conexión con FakeStore mediante fetch
│   ├── utils/             # Utilidades auxiliares
│   │   └── utils.js       # Funciones compartidas
│   ├── index.js           # Punto de entrada (configuración de Express)
│   └── api.http           # Pruebas de endpoints (opcional)
├── .gitignore             # Archivos excluidos de Git (node_modules)
├── package.json           # Dependencias y scripts
└── package-lock.json      # Versiones exactas de dependencias
```

## 🛠️ Tecnologías
- **Java 17+**: Lenguaje de programación principal.  
- **Spring Boot**: Framework para la configuración y gestión de dependencias.  
- **Jackson**: Deserialización de JSON a objetos Java.  
- **JPA/Hibernate**: ORM para persistencia de datos.  
- **PostgreSQL**: Base de datos relacional.  
- **Maven**: Gestor de dependencias y contrucción del proyecto.

## 🌐 API Utilizada
Este proyecto utiliza [Gutendex](https://gutendex.com/) para obtener y manejar una lista de libros.
