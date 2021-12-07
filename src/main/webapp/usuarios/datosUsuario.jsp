<%-- 
    Document   : datosCategoria
    Created on : 14 oct. 2021, 11:51:37
    Author     : leoj_
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>

        <title>Datos de Usuario</title>
    </head>
    <body>
        <!--<h1>Usuario</h1>-->

        <div class="container">

            <br/><br/>

            <center>
                <div class="col-sm-6">
                    <div class="card bg-light">
                        <div class="card-header">
                            <h3 class="text-center">Datos de Usuario</h3>
                        </div>
                        <div class="card-body">
                            <img src="${usuario.entidad.imagen}" 
                                 alt="Usuario" width="200" class="d-inline-block align-text-top">
                            <br/><br/>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    ID:
                                    <b><c:out value="${usuario.entidad.idUsuario}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Usuario:
                                    <b><c:out value="${usuario.entidad.nombreUsuario}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Contrasena:
                                    <b><c:out value="${usuario.entidad.claveUsuario}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Nombre(s):
                                    <b><c:out value="${usuario.entidad.nombre}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Paterno:
                                    <b><c:out value="${usuario.entidad.paterno}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Materno:
                                    <b><c:out value="${usuario.entidad.materno}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Correo:
                                    <b><c:out value="${usuario.entidad.email}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Tipo: 
                                    <b><c:out value="${usuario.entidad.tipoUsuario}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Creado: 
                                    <b><c:out value="${usuario.entidad.createdAt}" /></b>
                                </li>
                                <li class="list-group-item">
                                    <a href="UsuarioServlet?accion=listaDeUsuarios" class="btn btn-outline-success">Regresar</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </center>

        </div>
    </body>
</html>
