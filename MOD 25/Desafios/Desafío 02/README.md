# Desafío 02 - Sistema Bancario con JDBC

## Descripción

Sistema bancario que persiste clientes y productos financieros en base de datos utilizando JDBC básico. Este desafío evoluciona del MOD 24 agregando persistencia en MariaDB/MySQL.

## Funcionalidades

- Búsqueda de clientes por documento con HashMap
- Gestión de productos financieros (cuentas y tarjetas)
- Persistencia en base de datos MariaDB
- Validación de entrada de datos
- Manejo de excepciones personalizadas

## Conceptos Técnicos

- **JDBC**: Connection, Statement, ResultSet
- **HashMap**: Búsqueda eficiente de clientes
- **Herencia**: Producto → Cuenta/TarjetaCredito
- **Enumerados**: TipoDocumento, TipoCuenta
- **LocalDate**: Manejo moderno de fechas

## Estructura

```
Desafío 02/
├── src/main/java/com/educacionit/desafio02/
│   ├── App.java
│   ├── AppError.java
│   ├── entidades/
│   ├── enumerados/
│   ├── interfaces/
│   └── utilidades/
├── Script.sql
└── README.md
```

## Ejecución

```bash
mysql -u root -p < Script.sql
mvn compile
mvn exec:java -Dexec.mainClass="com.educacionit.desafio02.App"
```

## 👨‍💻 Autor

**Desarrollador**: Ludmila Martos

## 📞 Contacto

- **Email**: [ludmilamartos@gmail.com](mailto:ludmilamartos@gmail.com)
- **LinkedIn**: [ludmimar89](https://www.linkedin.com/in/ludmimar89/)
- **GitHub**: [Ludmimar](https://github.com/Ludmimar)

---


