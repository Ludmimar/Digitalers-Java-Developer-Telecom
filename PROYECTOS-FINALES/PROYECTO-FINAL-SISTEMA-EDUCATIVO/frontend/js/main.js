/**
 * SISTEMA DE GESTIÓN EDUCATIVA - JavaScript Principal
 * Autor: Ludmila Martos
 */

// ========================================
// NAVEGACIÓN ENTRE SECCIONES
// ========================================

document.addEventListener('DOMContentLoaded', function() {
    // Configurar navegación
    const navLinks = document.querySelectorAll('.nav-link');
    const sections = document.querySelectorAll('.section');

    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const targetId = this.getAttribute('href').substring(1);
            
            // Ocultar todas las secciones
            sections.forEach(section => section.classList.remove('active'));
            
            // Mostrar sección seleccionada
            document.getElementById(targetId).classList.add('active');
            
            // Actualizar links activos
            navLinks.forEach(l => l.classList.remove('active'));
            this.classList.add('active');
        });
    });

    // Cargar datos iniciales
    cargarEstadisticas();
    cargarEstudiantes();
});

// ========================================
// DATOS DE EJEMPLO (Simulación)
// ========================================

let estudiantes = [
    {
        id: 1,
        matricula: 'EST-2024-001',
        tipoDocumento: 'DNI',
        numeroDocumento: '12345678',
        nombre: 'Juan',
        apellido: 'Pérez',
        email: 'juan.perez@email.com',
        telefono: '1134567890',
        fechaNacimiento: '2000-05-15',
        fechaIngreso: '2024-03-01',
        promedio: 8.5,
        creditos: 24,
        estado: 'ACTIVO'
    },
    {
        id: 2,
        matricula: 'EST-2024-002',
        tipoDocumento: 'DNI',
        numeroDocumento: '23456789',
        nombre: 'María',
        apellido: 'González',
        email: 'maria.gonzalez@email.com',
        telefono: '1145678901',
        fechaNacimiento: '2001-08-20',
        fechaIngreso: '2024-03-01',
        promedio: 9.2,
        creditos: 18,
        estado: 'ACTIVO'
    },
    {
        id: 3,
        matricula: 'EST-2024-003',
        tipoDocumento: 'DNI',
        numeroDocumento: '34567890',
        nombre: 'Carlos',
        apellido: 'Rodríguez',
        email: 'carlos.rodriguez@email.com',
        telefono: '1156789012',
        fechaNacimiento: '1999-12-10',
        fechaIngreso: '2024-03-01',
        promedio: 7.8,
        creditos: 30,
        estado: 'ACTIVO'
    }
];

// ========================================
// FUNCIONES DE ESTADÍSTICAS
// ========================================

function cargarEstadisticas() {
    document.getElementById('totalEstudiantes').textContent = estudiantes.length;
    document.getElementById('totalProfesores').textContent = '4';
    document.getElementById('totalCursos').textContent = '8';
    document.getElementById('totalInscripciones').textContent = '15';
}

// ========================================
// FUNCIONES DE ESTUDIANTES
// ========================================

function cargarEstudiantes() {
    const tbody = document.querySelector('#tablaEstudiantes tbody');
    tbody.innerHTML = '';

    estudiantes.forEach(est => {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td>${est.id}</td>
            <td><strong>${est.matricula}</strong></td>
            <td>${est.nombre} ${est.apellido}</td>
            <td>${est.tipoDocumento} ${est.numeroDocumento}</td>
            <td>${est.email}</td>
            <td><span class="badge badge-${est.promedio >= 7 ? 'success' : 'warning'}">${est.promedio.toFixed(2)}</span></td>
            <td><span class="badge badge-success">${est.estado}</span></td>
            <td>
                <button class="btn-icon" onclick="verDetalleEstudiante(${est.id})" title="Ver detalle">👁️</button>
                <button class="btn-icon" onclick="editarEstudiante(${est.id})" title="Editar">✏️</button>
                <button class="btn-icon btn-danger" onclick="eliminarEstudianteConfirm(${est.id})" title="Eliminar">🗑️</button>
            </td>
        `;
        tbody.appendChild(tr);
    });
}

function mostrarFormularioEstudiante() {
    document.getElementById('formEstudiante').style.display = 'block';
    document.getElementById('formEstudiante').scrollIntoView({ behavior: 'smooth' });
}

function ocultarFormularioEstudiante() {
    document.getElementById('formEstudiante').style.display = 'none';
    document.getElementById('estudianteForm').reset();
}

function verDetalleEstudiante(id) {
    const estudiante = estudiantes.find(e => e.id === id);
    if (estudiante) {
        alert(`
📋 DETALLE DEL ESTUDIANTE

ID: ${estudiante.id}
Matrícula: ${estudiante.matricula}
Nombre: ${estudiante.nombre} ${estudiante.apellido}
Documento: ${estudiante.tipoDocumento} ${estudiante.numeroDocumento}
Email: ${estudiante.email}
Teléfono: ${estudiante.telefono}
Promedio: ${estudiante.promedio}
Créditos: ${estudiante.creditos}
Estado: ${estudiante.estado}
        `);
    }
}

function eliminarEstudianteConfirm(id) {
    const estudiante = estudiantes.find(e => e.id === id);
    if (estudiante && confirm(`¿Está seguro de eliminar al estudiante ${estudiante.nombre} ${estudiante.apellido}?`)) {
        estudiantes = estudiantes.filter(e => e.id !== id);
        cargarEstudiantes();
        cargarEstadisticas();
        alert('✅ Estudiante eliminado correctamente');
    }
}

// ========================================
// MANEJO DE FORMULARIOS
// ========================================

document.getElementById('estudianteForm')?.addEventListener('submit', function(e) {
    e.preventDefault();
    
    const nuevoEstudiante = {
        id: estudiantes.length + 1,
        matricula: document.getElementById('matricula').value,
        tipoDocumento: document.getElementById('tipoDoc').value,
        numeroDocumento: document.getElementById('numeroDoc').value,
        nombre: document.getElementById('nombre').value,
        apellido: document.getElementById('apellido').value,
        email: document.getElementById('email').value,
        telefono: document.getElementById('telefono').value,
        fechaNacimiento: document.getElementById('fechaNac').value,
        fechaIngreso: document.getElementById('fechaIngreso').value,
        direccion: document.getElementById('direccion').value,
        promedio: 0.00,
        creditos: 0,
        estado: 'ACTIVO'
    };

    estudiantes.push(nuevoEstudiante);
    cargarEstudiantes();
    cargarEstadisticas();
    ocultarFormularioEstudiante();
    
    alert('✅ ¡Estudiante registrado exitosamente!');
});

// ========================================
// BÚSQUEDA EN TIEMPO REAL
// ========================================

document.getElementById('buscarEstudiante')?.addEventListener('input', function(e) {
    const termino = e.target.value.toLowerCase();
    const filas = document.querySelectorAll('#tablaEstudiantes tbody tr');

    filas.forEach(fila => {
        const texto = fila.textContent.toLowerCase();
        fila.style.display = texto.includes(termino) ? '' : 'none';
    });
});

// ========================================
// UTILIDADES
// ========================================

function formatearFecha(fecha) {
    const opciones = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return new Date(fecha).toLocaleDateString('es-AR', opciones);
}

console.log('🎓 Sistema de Gestión Educativa cargado correctamente');
console.log('👨‍💻 Desarrollado por: Ludmila Martos');


