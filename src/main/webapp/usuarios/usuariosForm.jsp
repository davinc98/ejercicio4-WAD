<%-- 
    Document   : categoriasForm
    Created on : 14 oct. 2021, 11:43:17
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

        <title>Formulario Usuario</title>
    </head>
    <body>
        <div class="container">






            <c:if test="${usuario.entidad.idUsuario != null}">
                <h1>Actualizar Usuario</h1>
            </c:if>
            <c:if test="${usuario.entidad.idUsuario == null}">
                <h1>Nuevo Usuario</h1>
            </c:if>



            <center>
                <div class="col-sm-6">
                    <div class="card bg-light">
                        <div class="card header">
                            <h1 class="text-center">Datos Usuario</h1>
                        </div>
                        <div class="card card-body">
                            <form method="post" action="UsuarioServlet?accion=guardar">
                                <div class="mb-3">
                                    <!--<label class="form-label">ID </label>-->
                                    <input type="text" 
                                           name="txtIdUsuario" 
                                           id="txtIdUsuario" 
                                           placeholder="Id del usuario"
                                           readonly value="<c:out value="${usuario.entidad.idUsuario}"/>"
                                           class="form-control" hidden="true" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Nombre Usuario: </label>
                                    <input type="text" 
                                           name="txtNombreUsuario" 
                                           id="txtNombreUsuario" 
                                           placeholder="Nombre de usuario"
                                           required
                                           maxlenght="50"
                                           value="<c:out value="${usuario.entidad.nombreUsuario}"/>"
                                           class="form-control" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Clave: </label>
                                    <input type="password" 
                                           name="txtClaveUsuario" 
                                           id="txtClaveUsuario" 
                                           placeholder="Clave de usuario"
                                           required
                                           maxlenght="100"
                                           value="<c:out value="${usuario.entidad.claveUsuario}"/>"
                                           class="form-control" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Nombre (s): </label>
                                    <input type="text" 
                                           name="txtNombre" 
                                           id="txtNombre" 
                                           placeholder="Nombre del usuario"
                                           required
                                           maxlenght="100"
                                           value="<c:out value="${usuario.entidad.nombre}"/>"
                                           class="form-control" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Apellido Paterno: </label>
                                    <input type="text" 
                                           name="txtPaterno" 
                                           id="txtPaterno" 
                                           placeholder="Apellido paterno"
                                           required
                                           maxlenght="100"
                                           value="<c:out value="${usuario.entidad.paterno}"/>"
                                           class="form-control" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Apellido Materno: </label>
                                    <input type="text" 
                                           name="txtMaterno" 
                                           id="txtMaterno" 
                                           placeholder="Apellido materno"
                                           required
                                           maxlenght="100"
                                           value="<c:out value="${usuario.entidad.materno}"/>"
                                           class="form-control" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Correo electrónico: </label>
                                    <input type="text" 
                                           name="txtEmail" 
                                           id="txtEmail" 
                                           placeholder="Correo electrónico"
                                           required
                                           maxlenght="100"
                                           value="<c:out value="${usuario.entidad.email}"/>"
                                           class="form-control" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Tipo de Usuario: </label>
                                    <input type="text" 
                                           name="txtTipoUsuario" 
                                           id="txtTipoUsuario" 
                                           placeholder="Tipo de usuario"
                                           required
                                           maxlenght="100"
                                           value="<c:out value="${usuario.entidad.tipoUsuario}"/>"
                                           class="form-control" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Selecciona una imagen: </label>
                                    <img src="<c:out value="${usuario.entidad.imagen}"/>" width="10">
                                    <input type="file" 
                                           name="txtImagen" 
                                           id="txtImagen" 
                                           placeholder="Imagen"
                                           maxlenght="100"
                                           value="<c:out value="${usuario.entidad.imagen}"/>"
                                           class="form-control" />
                                </div>
                                <button type="submit" class="btn btn-outline-primary">Guadar Cambios</button>     
                            </form>
                        </div>
                    </div>
                </div>
            </center>
        </div>
    </body>
</html>
