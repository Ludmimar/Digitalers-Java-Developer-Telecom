# Proyecto Integrador Etapa 1 - MOD 30

## 📖 Descripción

Este directorio contiene el **Proyecto Integrador Etapa 1** del Módulo 30, que consiste en desarrollar una API REST completa con Spring Boot para gestionar Personas y Productos, incluyendo el consumo de una API externa.

---

## 📂 Contenido

### 📄 Enunciado
- **Etapa 1.pdf**: Descripción completa del proyecto, requisitos y objetivos
- **Analisis de las capas del proyecto.md**: Análisis de la arquitectura en capas
- **cursor_analiza_y_comenta_el_proyecto.md**: Guía de análisis del proyecto

### ✅ Resuelto
- **demo/**: Proyecto Spring Boot completo con solución implementada
  - API REST para Personas y Productos
  - Consumo de API externa (RandomUser.me)
  - Arquitectura en capas completa
  - Documentación detallada en `README.md`

---

## 🎯 Objetivos del Proyecto

1. **Implementar API REST completa** con operaciones CRUD para Personas y Productos
2. **Aplicar arquitectura en capas** (Controller/Service/Repository)
3. **Consumir API externa** para obtener datos de usuarios aleatorios
4. **Documentar el proyecto** con README completo y ejemplos de uso
5. **Probar los endpoints** con Postman o cURL

---

## 📁 Estructura del Proyecto

```
Proyecto Integrador Etapa 1/
├── 📄 README.md                           # Este archivo
├── 📄 Etapa 1.pdf                         # Enunciado del proyecto
├── 📄 Analisis de las capas del proyecto.md
├── 📄 cursor_analiza_y_comenta_el_proyecto.md
└── 📁 resuelto/                           # Solución implementada
    └── 📁 demo/                           # Proyecto Spring Boot
        ├── 📄 README.md                   # Documentación detallada
        ├── 📄 DOCUMENTACION_PROYECTO.md   # Análisis del proyecto
        ├── 📄 pom.xml                     # Configuración Maven
        └── 📁 src/                        # Código fuente
            └── main/java/com/example/demo/
                ├── controller/            # Controladores REST
                │   ├── PersonaController.java
                │   ├── ProductoController.java
                │   └── RandomUserController.java  # API externa
                ├── service/               # Lógica de negocio
                │   ├── PersonaService.java
                │   ├── ProductoService.java
                │   └── RandomUserService.java     # Servicio API externa
                ├── repository/            # Acceso a datos
                │   ├── PersonaRepository.java
                │   └── ProductoRepository.java
                ├── model/                 # Entidades del dominio
                │   ├── Persona.java
                │   ├── Producto.java
                │   ├── Direccion.java
                │   └── RandomUserPhone.java       # DTO para API
                └── DemoApplication.java   # Clase principal
```

---

## 🚀 Cómo Comenzar

### Paso 1: Leer el Enunciado
1. Abrir y leer `Etapa 1.pdf` para entender los requisitos
2. Revisar `Analisis de las capas del proyecto.md` para entender la arquitectura
3. Consultar `cursor_analiza_y_comenta_el_proyecto.md` si es necesario

### Paso 2: Implementar la Solución
1. Crear un nuevo proyecto Spring Boot
2. Implementar las capas (Controller/Service/Repository)
3. Crear los modelos de datos (Persona, Producto, Direccion)
4. Implementar los endpoints REST
5. Integrar consumo de API externa (RandomUser.me)

### Paso 3: Probar la Solución
1. Ejecutar la aplicación: `mvn spring-boot:run`
2. Probar los endpoints con Postman o cURL
3. Verificar que todas las operaciones CRUD funcionen correctamente

### Paso 4: Revisar la Solución (Opcional)
Si necesitas ver una implementación de referencia, puedes revisar la carpeta `resuelto/demo/`.

---

## 📋 Requisitos del Proyecto

### Funcionalidades Mínimas
- ✅ API REST para gestionar Personas (CRUD completo)
- ✅ API REST para gestionar Productos (CRUD completo)
- ✅ Arquitectura en capas (Controller/Service/Repository)
- ✅ Almacenamiento en memoria (ArrayList)
- ✅ Endpoints REST con códigos de estado HTTP apropiados

### Funcionalidades Adicionales (Bonus)
- ✅ Consumo de API externa (RandomUser.me)
- ✅ Endpoint adicional para buscar producto por nombre
- ✅ Documentación completa del proyecto
- ✅ Ejemplos de uso con cURL

---

## 🎯 Entregables

1. **Código fuente** del proyecto Spring Boot
2. **README.md** con documentación completa del proyecto
3. **Ejemplos de uso** (cURL o Postman collection)
4. **Explicación de la arquitectura** en capas

---

## 📚 Conceptos Aplicados

- **Spring Boot:** Framework y auto-configuración
- **REST APIs:** Principios RESTful y métodos HTTP
- **Arquitectura en Capas:** Separación de responsabilidades
- **Anotaciones Spring:** `@RestController`, `@GetMapping`, `@PostMapping`, etc.
- **JSON:** Serialización/deserialización automática
- **ResponseEntity:** Manejo de códigos de estado HTTP
- **RestTemplate:** Consumo de APIs externas
- **Jackson:** Parseo de JSON con ObjectMapper

---

## 🔗 Enlaces Útiles

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [REST API Tutorial](https://restfulapi.net/)
- [RandomUser.me API](https://randomuser.me/api/)
- [Postman Documentation](https://learning.postman.com/)
- [cURL Documentation](https://curl.se/docs/)

---

## 📝 Notas Importantes

### ⚠️ Almacenamiento en Memoria
- Los datos se almacenan en memoria (ArrayList)
- **Los datos se pierden al reiniciar la aplicación**
- Esta es una limitación intencional para esta etapa del proyecto

### 💡 Próximas Mejoras
En etapas futuras se implementará:
- Persistencia con base de datos (JPA/H2 o PostgreSQL)
- Validaciones con Bean Validation
- Manejo robusto de excepciones
- Autenticación y autorización
- Documentación con Swagger/OpenAPI

---

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---

