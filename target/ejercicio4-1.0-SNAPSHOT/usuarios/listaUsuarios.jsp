<%-- 
    Document   : listaCategorias
    Created on : 14 oct. 2021, 10:55:42
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

        <title>Usuarios</title>
    </head>
    <body>
        <div class="container">





            <br/>
            <h1>Usuarios</h1>
            <br/>
            <br/>

            <div class="row">
                <div class="col col-lg-2">
                    <h4 class="card-title">
                        <a href="UsuarioServlet?accion=nuevo" class="btn btn-outline-success">Crear Usuario</a>
                    </h4>
                </div>
                <div class="col">

                </div>
                <div class="col col-lg-2">
                    <h4 class="card-title">
                        <!--<a href="UsuarioServlet?accion=graficar" class="btn btn-outline-primary" target="_blank">Mostrar Grafica</a>-->
                    </h4>
                </div>                 
                <div class="col col-lg-2">
                    <h4 class="card-title">
                        <!--<a href="UsuarioServlet?accion=verReporte" class="btn btn-outline-danger" target="_blank">Mostrar Reporte</a>-->
                    </h4>
                </div>
            </div>

            <br/>
            <c:if test="${mensaje != null}">
                <div class="alert ${alert} alert-dismissible fade show" role="alert">
                    <p>${mensaje}</p>
                    <button class="btn-close" data-bs-dismiss="alert" aria-lbel="Close"></button>
                </div>
            </c:if>

            <div class="card bg-light">
                <div class="card-header text-center">
                    Usuarios
                </div>
                <div class="card-body">



                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Usuario</th>
                                <!--<th>Contrasenia</th>-->
                                <th>Nombre (s)</th>
                                <th>Paterno</th>
                                <th>Materno</th>
                                <th>Correo</th>
                                <th>Tipo</th>
                                <th>Imagen</th>
                                <th>Creado</th>

                                <th>Eliminar</th>
                                <th>Actualizar</th>
                            </tr>
                        </thead>
                        <c:forEach items="${listaDeUsuarios}" var="dto" >
                            <tbody>
                                <tr>
                                    <td>
                                        <a href="UsuarioServlet?accion=ver&id=<c:out value="${ dto.entidad.idUsuario }"/>" class="btn btn-outline-warning">
                                            <c:out value="${ dto.entidad.idUsuario }"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.nombreUsuario }"/>
                                    </td>
<!--                                    <td>
                                        <c:out value="${ dto.entidad.claveUsuario }"/>
                                    </td>-->
                                    <td>
                                        <c:out value="${ dto.entidad.nombre }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.paterno }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.materno }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.email }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.tipoUsuario }"/>
                                    </td>
                                    <td>
                                        <%--<c:out value="${dto.entidad.imagen}"/>--%>
                                        <img src="http://localhost:8080/ejercicio4/${dto.entidad.imagen}" width="40">
                                        
                                        <!--<img src="https://ejercicio4-wad.herokuapp.com/${dto.entidad.imagen}" width="30">-->
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.createdAt }"/>
                                    </td>                                    
                                    <td>
                                        <a href="UsuarioServlet?accion=eliminar&id=<c:out value="${ dto.entidad.idUsuario }"/>" class="btn btn-outline-danger">Eliminar</a>
                                    </td>
                                    <td>
                                        <a href="UsuarioServlet?accion=actualizar&id=<c:out value="${ dto.entidad.idUsuario }"/>" class="btn btn-outline-success">Actualizar</a>
                                    </td>
                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
