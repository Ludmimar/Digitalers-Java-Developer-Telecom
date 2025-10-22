# 📁 Proyectos Finales - Sistema de Gestión Educativa

**Curso:** Digitalers Java Developer Telecom  
**Autora:** Ludmila Martos  
**Año:** 2025

---

## 📂 Contenido de esta Carpeta

Esta carpeta contiene las **3 versiones** del Proyecto Final del curso, cada una con diferentes características y enfoques:

```
PROYECTOS-FINALES/
│
├── 📖 LEER-SOBRE-PROYECTO-FINAL.md  ← LEER PRIMERO
│
├── 📁 PROYECTO-FINAL-SISTEMA-EDUCATIVO/
│   └── Versión Base (Módulos 1-26)
│
├── 📁 PROYECTO-FINAL-SISTEMA-EDUCATIVO-01/
│   └── Versión Producción (Módulos 27-28)
│
└── 📁 PROYECTO-FINAL-SISTEMA-EDUCATIVO-02/
    └── Versión Académica (Comentada)
```

---

## 🚀 Inicio Rápido

### **1. LEE LA GUÍA COMPLETA:**
📖 **[LEER-SOBRE-PROYECTO-FINAL.md](LEER-SOBRE-PROYECTO-FINAL.md)**

Este documento explica:
- ✅ Diferencias entre las 3 versiones
- ✅ Qué proyecto usar según tu necesidad
- ✅ Comparación detallada
- ✅ Evolución del proyecto

### **2. Elige tu Proyecto:**

| **Proyecto** | **Usar si...** |
|-------------|----------------|
| **BASE** | Estás en módulos 1-26 |
| **01** | Necesitas deployar en producción |
| **02** | Lo entregas como proyecto académico |

---

## 📊 Resumen Rápido

### **🔹 PROYECTO BASE**
```
✅ Funcionalidades básicas
✅ CRUD completo
✅ MVC + DAO
⚠️  Sin correlatividades
⚠️  Sin validaciones avanzadas
```

### **🔹 PROYECTO-01 (Producción)**
```
✅ Todas las funcionalidades
✅ Correlatividades completas
✅ Límite de créditos
✅ Dashboard avanzado
✅ Docker + Render
✅ Listo para producción
```

### **🔹 PROYECTO-02 (Académico)**
```
✅ Todas las funcionalidades de 01
✅ Código exhaustivamente comentado
✅ Documentación académica
✅ Guías educativas
✅ Perfecto para evaluación
```

---

## 🎯 Recomendación

**Si tienes dudas, usa:**
- 💼 **PROYECTO-01** para portfolio profesional
- 📚 **PROYECTO-02** para entrega académica

---

## 📚 Documentación

Cada proyecto tiene su propia documentación completa en su carpeta `docs/`:

- **BASE:** 5 documentos
- **PROYECTO-01:** 9 documentos + Guía Deploy
- **PROYECTO-02:** 14 documentos + Guías educativas

---

## 🔗 Acceso Rápido

### **README de cada Proyecto:**
- [README - BASE](PROYECTO-FINAL-SISTEMA-EDUCATIVO/README.md)
- [README - PROYECTO-01](PROYECTO-FINAL-SISTEMA-EDUCATIVO-01/README.md)
- [README - PROYECTO-02](PROYECTO-FINAL-SISTEMA-EDUCATIVO-02/README.md)

### **Guías de Inicio:**
- [Inicio Rápido - BASE](PROYECTO-FINAL-SISTEMA-EDUCATIVO/INICIO-RAPIDO.md)
- [Como Ejecutar - PROYECTO-01](PROYECTO-FINAL-SISTEMA-EDUCATIVO-01/COMO-EJECUTAR.md)
- [Como Ejecutar - PROYECTO-02](PROYECTO-FINAL-SISTEMA-EDUCATIVO-02/COMO-EJECUTAR.md)

---

## ⚡ Ejecución Rápida

### **Para cualquier proyecto:**

```bash
# 1. Crear base de datos
mysql -u root -p < database/01_CREAR_BASE_DATOS_COMPLETA.sql
mysql -u root -p < database/02_CARGAR_DATOS_COMPLETOS.sql

# 2. Compilar y ejecutar
cd backend
mvn clean compile
mvn tomcat7:run

# 3. Acceder
http://localhost:8081/sistema-educativo/
```

---

## 📞 Contacto

**Autora:** Ludmila Martos  
**Email:** ludmilamartos@gmail.com  
**LinkedIn:** [ludmimar89](https://www.linkedin.com/in/ludmimar89/)  
**GitHub:** [Ludmimar](https://github.com/Ludmimar)

---

## ⚖️ Licencia

Proyecto Académico - Digitalers 2025

---

**🎓 ¡Elige el proyecto que mejor se adapte a tus necesidades!**

**📖 No olvides leer:** [LEER-SOBRE-PROYECTO-FINAL.md](LEER-SOBRE-PROYECTO-FINAL.md)

