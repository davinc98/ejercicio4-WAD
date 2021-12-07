<%-- 
    Document   : listaProductos
    Created on : 15 oct. 2021, 14:22:57
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

        <title>Productos</title>
    </head>
    <body>
        <div class="container">


<nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">
                        <img src="/imagenes/bootstrap-logo.svg" alt="" width="30" height="24" class="d-inline-block align-text-top">
                        J. Perez
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="index.jsp">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="CategoriaServlet?accion=listaDeCategorias">Categorias</a>
                            </li>
<!--                            <li class="nav-item">
                                <a class="nav-link" href="CategoriaServlet?accion=nuevo">Nueva Categoria</a>
                            </li>-->
                            <li class="nav-item">
                                <a class="nav-link" href="ProductoServlet?accion=listaDeProductos">Productos</a>
                            </li>
<!--                            <li class="nav-item">
                                <a class="nav-link" href="ProductoServlet?accion=nuevo">Nuevo Producto</a>
                            </li>-->
                            <li class="nav-item">
                                <a class="nav-link" href="UsuarioServlet?accion=listaDeUsuarios">Usuarios</a>
                            </li>
<!--                            <li class="nav-item">
                                <a class="nav-link" href="UsuarioServlet?accion=nuevo">Nuevo Usuario</a>
                            </li>-->
                        </ul>
                    </div>
                </div>
            </nav>


            
            <br/>
            <h1>Productos</h1>
            <br/>
            <br/>
            
            
            
            <div class="row">
                <div class="col col-lg-2">
                    <h4 class="card-title">
                        <a href="ProductoServlet?accion=nuevo" class="btn btn-outline-success">Crear Producto</a>
                    </h4>
                </div>
                <div class="col">
                </div>
                <div class="col col-lg-2">
                    <h4 class="card-title">
                    </h4>
                </div>                 
                <div class="col col-lg-2">
                    <h4 class="card-title">
                        <a href="ProductoServlet?accion=graficar" class="btn btn-outline-primary" target="_blank">Mostrar Grafica</a>
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
                    Productos
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th>Clave</th>
                                <th>Nombre</th>
                                <th>Descripcion</th>
                                <th>Precio</th>
                                <th>Existencia</th>
                                <th>Stock Minimo</th>
                                <th>Categoria</th>
                                <th>Eliminar</th>
                                <th>Actualizar</th>
                                <!--<th>Reporte</th>-->
                            </tr>
                        </thead>
                        <c:forEach var="dto" items="${listaDeProductos}">
                            <tbody>
                                <tr>
                                    <td>
                                        <a href="ProductoServlet?accion=ver&id=<c:out value="${ dto.entidad.idProducto }"/>" class="btn btn-outline-warning">
                                            <c:out value="${ dto.entidad.idProducto }"/>
                                        </a>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.nombreProducto }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.descripcionProducto }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.precio }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.existencia }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.stockMinimo }"/>
                                    </td>
                                    <td>
                                        <c:out value="${ dto.entidad.claveCategoria.nombreCategoria }"/>
                                    </td>
                                    <td>
                                        <a href="ProductoServlet?accion=eliminar&id=<c:out value="${ dto.entidad.idProducto }"/>" class="btn btn-outline-danger">Eliminar</a>
                                    </td>
                                    <td>
                                        <a href="ProductoServlet?accion=actualizar&id=<c:out value="${ dto.entidad.idProducto }"/>" class="btn btn-outline-success">Actualizar</a>
                                    </td>
<!--                                    <td>
                                        <a href="#" class="btn btn-outline-info">Reporte</a>
                                    </td>-->
                                </tr>
                            </tbody>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
