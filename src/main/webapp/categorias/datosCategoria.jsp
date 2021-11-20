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

        <title>Datos de Categoria</title>
    </head>
    <body>

        <div class="container">

            <h1>Ver Categoría</h1>

            
            
            


            <center>
                <div class="col-sm-4">
                    <div class="card bg-light">
                        <div class="card-header">
                            <h3 class="text-center">Categoría</h3>
                        </div>
                        <div class="card-body">
                            <img src="./imagenes/graycat.png" alt="" width="200" class="d-inline-block align-text-top">
                            <br/><br/>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    Id: 
                                    <b><c:out value=" ${categoria.entidad.idCategoria}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Nombre Categoría:
                                    <b><c:out value=" ${categoria.entidad.nombreCategoria}" /></b>
                                </li>
                                <li class="list-group-item">
                                    Descripción:
                                    <b><c:out value=" ${categoria.entidad.descripcionCategoria}" /></b>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </center>
        </div>
    </body>
</html>
