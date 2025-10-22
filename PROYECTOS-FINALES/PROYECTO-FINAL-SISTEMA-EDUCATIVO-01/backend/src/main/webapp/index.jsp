<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Gestión Educativa - Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css?v=2.0">
</head>
<body class="login-page">
    <div class="login-container">
        <div class="login-card">
            <div class="login-header">
                <h1><i class="fas fa-graduation-cap"></i> Sistema de Gestión Educativa</h1>
                <p><i class="fas fa-sign-in-alt"></i> Iniciar Sesión</p>
            </div>
            
            <% 
            String error = request.getParameter("error");
            if (error != null) { 
            %>
                <div class="alert alert-danger">
                    ❌ Usuario o contraseña incorrectos
                </div>
            <% } %>
            
            <form action="login" method="post" class="login-form">
                <div class="form-group">
                    <label for="usuario"><i class="fas fa-user"></i> Usuario</label>
                    <input type="text" id="usuario" name="usuario" 
                           placeholder="Ingrese su usuario" required autofocus>
                </div>
                
                <div class="form-group">
                    <label for="password"><i class="fas fa-lock"></i> Contraseña</label>
                    <input type="password" id="password" name="password" 
                           placeholder="Ingrese su contraseña" required>
                </div>
                
                <button type="submit" class="btn btn-primary btn-block">
                    <i class="fas fa-sign-in-alt"></i> Ingresar
                </button>
            </form>
            
            <div class="login-footer">
                <p><i class="fas fa-info-circle"></i> <strong>Credenciales de prueba:</strong></p>
                <p>Usuario: <code>admin</code> | Contraseña: <code>admin123</code></p>
            </div>
        </div>
    </div>
</body>
</html>

<style>
.login-page {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 1rem;
    position: relative;
    overflow: hidden;
}

.login-page::before {
    content: '';
    position: absolute;
    width: 200%;
    height: 200%;
    background: radial-gradient(circle, rgba(255,255,255,0.1) 1px, transparent 1px);
    background-size: 50px 50px;
    animation: moveBackground 20s linear infinite;
}

@keyframes moveBackground {
    0% {
        transform: translate(0, 0);
    }
    100% {
        transform: translate(50px, 50px);
    }
}

.login-container {
    width: 100%;
    max-width: 480px;
    z-index: 1;
}

.login-card {
    background: white;
    border-radius: 20px;
    padding: 3rem 2.5rem;
    box-shadow: 0 25px 50px -12px rgba(0, 0, 0, 0.5);
    backdrop-filter: blur(10px);
    animation: slideUp 0.5s ease-out;
}

@keyframes slideUp {
    from {
        opacity: 0;
        transform: translateY(30px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

.login-header {
    text-align: center;
    margin-bottom: 2.5rem;
}

.login-header h1 {
    background: linear-gradient(135deg, #4f46e5 0%, #7c3aed 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    margin-bottom: 0.75rem;
    font-size: 2rem;
    font-weight: 800;
}

.login-header p {
    color: #6b7280;
    font-size: 1.1rem;
    font-weight: 500;
}

.login-form {
    margin-bottom: 2rem;
}

.login-form .form-group {
    margin-bottom: 1.75rem;
}

.login-form label {
    display: block;
    margin-bottom: 0.625rem;
    font-weight: 600;
    color: #1f2937;
    font-size: 0.95rem;
}

.login-form input {
    width: 100%;
    padding: 1rem 1.25rem;
    border: 2px solid #e5e7eb;
    border-radius: 12px;
    font-size: 1rem;
    transition: all 0.3s ease;
    background: white;
}

.login-form input:focus {
    outline: none;
    border-color: #4f46e5;
    box-shadow: 0 0 0 4px rgba(79, 70, 229, 0.1);
    transform: translateY(-2px);
}

.btn-block {
    width: 100%;
    padding: 1.125rem;
    font-size: 1.1rem;
    font-weight: 700;
    border-radius: 12px;
    background: linear-gradient(135deg, #4f46e5 0%, #7c3aed 100%);
    box-shadow: 0 10px 20px rgba(79, 70, 229, 0.3);
}

.btn-block:hover {
    box-shadow: 0 15px 30px rgba(79, 70, 229, 0.4);
}

.login-footer {
    text-align: center;
    padding-top: 2rem;
    border-top: 2px solid #f3f4f6;
    color: #6b7280;
    font-size: 0.9rem;
}

.login-footer p:first-child {
    font-weight: 600;
    margin-bottom: 0.75rem;
}

.login-footer code {
    background: linear-gradient(135deg, #f3f4f6 0%, #e5e7eb 100%);
    padding: 0.375rem 0.75rem;
    border-radius: 8px;
    color: #4f46e5;
    font-weight: 700;
    border: 1px solid #e5e7eb;
}
</style>

