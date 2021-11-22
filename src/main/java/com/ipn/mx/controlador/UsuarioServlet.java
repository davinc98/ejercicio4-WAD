/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

//import com.ipn.mx.modelo.dao.GraficaDAO;
//import com.ipn.mx.modelo.dto.GraficaDTO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author leoj_
 */
@MultipartConfig
@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(UsuarioServlet.class.getName());

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        if (accion.equals("listaDeUsuarios")) {
            listaDeUsuarios(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarUsuario(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarUsuario(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarUsuario(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarUsuario(request, response);
                        } else {
                            if (accion.equals("ver")) {
                                mostrarUsuario(request, response);
                            }
                        }
                    }
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaDeUsuarios(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeUsuarios", lista);

            RequestDispatcher rd = request.getRequestDispatcher("/usuarios/listaUsuarios.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("/usuarios/usuariosForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        dto = dao.delete(dto);

        if (dto != null) {
            request.setAttribute("mensaje", "Usuario <b>" + dto.getEntidad().getNombreUsuario() + "</b> eliminado correctamente.");
            request.setAttribute("alert", "alert-warning");
        } else {
            request.setAttribute("mensaje", "Ocurrio un error al eliminar Usuario: <b>" + dto.getEntidad().getNombreUsuario() + "</b>.");
            request.setAttribute("alert", "alert-danger");
        }
        listaDeUsuarios(request, response);
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("/usuarios/usuariosForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("usuario", dto);
            vista.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("/usuarios/datosUsuario.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("usuario", dto);
            vista.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        
        String pathFiles = request.getRealPath("/");
        File uploads = new File(pathFiles);
        
        //Recuperar la url de la app: https://www.it-swarm-es.com/es/jsp/como-obtener-la-url-del-dominio-y-el-nombre-de-la-aplicacion/968427622/
        String pathConexion = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";
        logger.log(Level.INFO, pathConexion);
        
        String msg = "";

        if (!request.getParameter("txtIdUsuario").equals("")) {
            dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("txtIdUsuario")));
        }

        dto.getEntidad().setNombreUsuario(request.getParameter("txtNombreUsuario"));
        dto.getEntidad().setClaveUsuario(request.getParameter("txtClaveUsuario"));
        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
        dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
        dto.getEntidad().setEmail(request.getParameter("txtEmail"));
        dto.getEntidad().setTipoUsuario(request.getParameter("txtTipoUsuario"));

        //RECUPERAR Y ALMACENAR LA IMAGEN=======================================
        dto.getEntidad().setImagen(request.getParameter("txtImgAnterior"));
        try {
            Part part = request.getPart("txtImagen");
            if (part != null) {
                if (esExtensionImagen(part.getSubmittedFileName())) {//Verifica ext
                    
                    //Almacenar imagen en el sistema de archivos
                    String fileName = guardarArchivo(part, uploads, request.getParameter("txtNombreUsuario"));
             
                    dto.getEntidad().setImagen(pathConexion+fileName);
                }else{
                    msg+=":: Imagen no cargada.";
                }
            }else{
                msg+=":: Sin cambio de imagen.";
            }
        } catch (IOException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        //======================================================================

//        String txtFecha = request.getParameter("txtFecha");
        String txtFecha = "2021-11-20";
        try {
            dto.getEntidad().setCreatedAt(new SimpleDateFormat("yyyy-MM-dd").parse(txtFecha));
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!request.getParameter("txtIdUsuario").equals("")) {//CREAR
            dao.update(dto);
            
            request.setAttribute("mensaje", "Usuario <b>"+dto.getEntidad().getNombreUsuario()+"</b> actualizado."+msg);
            request.setAttribute("alert", "alert-warning");
        } else {
            dao.create(dto);
            request.setAttribute("mensaje", "Usuario <b>"+dto.getEntidad().getNombreUsuario()+"</b> creado."+msg);
            request.setAttribute("alert", "alert-success");
        }
        listaDeUsuarios(request, response);

    }

    private String guardarArchivo(Part part, File pathUploads, String nombreUsuario) {
        String pathAbsolute = "";
        String fileName = "";

        try { 
            Path path = Paths.get(part.getSubmittedFileName());
            String fileNameExt = path.getFileName().toString();
            int i = fileNameExt.lastIndexOf('.');

            String ext = "";
            ext = fileNameExt.substring(i);

            fileName = "img-usr" + nombreUsuario + ext;
            InputStream input = part.getInputStream();

            if (input != null) {
                File file = new File(pathUploads, fileName);
                pathAbsolute = file.getAbsolutePath();
                Files.copy(input, file.toPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return pathAbsolute;
        return fileName;
    }

    private boolean esExtensionImagen(String fileName) {
        String[] extenciones = {".ico", ".png", ".jpg", ".jpeg"};
        for (String ext : extenciones) 
            if (fileName.toLowerCase().endsWith(ext)) 
                return true;
                   
        return false;
    }

}
