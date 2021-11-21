<%-- 
    Document   : datosProducto
    Created on : 15 oct. 2021, 14:55:27
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

        <title>Datos de Producto</title>
    </head>
    <body>
        <h1>Ver Producto</h1>

        <div class="container">




            <center>
                <div class="col-sm-4">

                    <div class="card bg-light">
                        <div class="card-header">
                            <h3 class="text-center">Producto</h3>
                        </div>
                        <div class="card-body">
                            <center>
                                <img src="./imagenes/prod.png" alt="" width="200" class="d-inline-block align-text-top">
                                <br/><br/>
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        Clave del Producto:
                                        <b><c:out value=" ${producto.entidad.idProducto}" /></b>
                                    </li>
                                    <li class="list-group-item">
                                        Nombre del Producto:
                                        <b><c:out value=" ${producto.entidad.nombreProducto}" /></b>
                                    </li>
                                    <li class="list-group-item">
                                        Descripción:
                                        <b><c:out value=" ${producto.entidad.descripcionProducto}" /></b>
                                    </li>
                                    <li class="list-group-item">
                                        Precio:
                                        <b><c:out value=" ${producto.entidad.precio}" /></b>
                                    </li>
                                    <li class="list-group-item">
                                        Existencia: 
                                        <b><c:out value=" ${producto.entidad.existencia}" /></b>
                                    </li>
                                    <li class="list-group-item">
                                        Stock Mínimo:
                                        <b><c:out value=" ${producto.entidad.stockMinimo}" /></b>
                                    </li>
                                    <li class="list-group-item">
                                        Categoría:
                                        <b><c:out value=" ${producto.entidad.claveCategoria.nombreCategoria}" /></b>
                                    </li>
                                </ul>
                            </center>
                        </div>
                    </div>

                </div>
            </center>
        </div>
    </body>
</html>
