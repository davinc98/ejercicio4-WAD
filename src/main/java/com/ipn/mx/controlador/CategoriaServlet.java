/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
//import com.ipn.mx.modelo.dao.GraficaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.ProductoDTO;
//import com.ipn.mx.modelo.dto.GraficaDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author leoj_
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})
public class CategoriaServlet extends HttpServlet {

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

        if (accion.equals("listaDeCategorias")) {
            listaDeCategorias(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarCategoria(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarCategoria(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarCategoria(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarCategoria(request, response);
                        } else {
                            if (accion.equals("ver")) {
                                mostrarCategoria(request, response);
                            } else {
                                if (accion.equals("verReporte")) {
                                    mostrarReporte(request, response);
                                } else {
                                    if (accion.equals("graficar")) {
                                        mostrarGrafica(request, response);
                                    }
                                }
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

    private void listaDeCategorias(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeCategorias", lista);

            RequestDispatcher rd = request.getRequestDispatcher("/categorias/listaCategorias.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("/categorias/categoriasForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));

        dto = dao.delete(dto);
        
        if(dto!= null){
            request.setAttribute("mensaje", "<b>"+dto.getEntidad().getNombreCategoria()+"</b> eliminada correctamente.");
            request.setAttribute("alert", "alert-warning");
        }else{
            request.setAttribute("mensaje", "Ocurrio un error al eliminar <b>"+dto.getEntidad().getNombreCategoria()+"</b>.");
            request.setAttribute("alert", "alert-danger");
        }      
        
        listaDeCategorias(request, response);
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("/categorias/categoriasForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("categoria", dto);
            vista.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("/categorias/datosCategoria.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("categoria", dto);
            vista.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        
        if(!request.getParameter("txtIdCategoria").equals(""))
            dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtIdCategoria")));
        
        dto.getEntidad().setNombreCategoria(request.getParameter("txtNombreCategoria"));
        dto.getEntidad().setDescripcionCategoria(request.getParameter("txtDescripcionCategoria"));
        
        if(!request.getParameter("txtIdCategoria").equals("")){//CREAR
            dao.update(dto);
            request.setAttribute("mensaje", "<b>"+dto.getEntidad().getNombreCategoria()+"</b> actualizada con exito.");
            request.setAttribute("alert", "alert-warning");
        }else{
            dao.create(dto);
            request.setAttribute("mensaje", "<b>"+dto.getEntidad().getNombreCategoria()+"</b> almacenada con exito.");
            request.setAttribute("alert", "alert-success");
        }        
        listaDeCategorias(request, response);
        
    }

    private void mostrarReporte(HttpServletRequest request, HttpServletResponse response) {
//        CategoriaDAO dao = new CategoriaDAO();
//        try {
//            ServletOutputStream sos = response.getOutputStream();
//            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/ReporteGeneral.jasper"));
//            byte[] b = JasperRunManager.runReportToPdf(reporte.getPath(), null, dao.conectar());
//            response.setContentType("application/pdf");
//            response.setContentLength(b.length);
//            
//            sos.write(b,0,b.length);
//            sos.flush();
//            sos.close();
//            
//        } catch (IOException ex) {
//            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void mostrarGrafica(HttpServletRequest request, HttpServletResponse response) {
        JFreeChart grafica = ChartFactory.createPieChart("Productos por Categoria", 
                obtenerGraficaProductosPorCategoria(), true, true, Locale.getDefault());        
        String archivo = getServletConfig().getServletContext().getRealPath("/graficaCategorias.png");
        try {
            ChartUtils.saveChartAsPNG(new File(archivo), grafica, 500, 500);
            RequestDispatcher vista = request.getRequestDispatcher("graficaCategorias.jsp");
            vista.forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private PieDataset obtenerGraficaProductosPorCategoria(){
        DefaultPieDataset dsPie = new DefaultPieDataset();
        CategoriaDAO dao = new CategoriaDAO();
        ProductoDAO daoProd = new ProductoDAO();
        
        List cat = dao.readAll();
        List prod = daoProd.readAll();
        
        int cont = 0;
        for (int i = 0; i < cat.size(); i++) {
            cont = 0;
            
            CategoriaDTO dto = (CategoriaDTO) cat.get(i);
            for(int j = 0; j<prod.size(); j++){
                ProductoDTO dtoProd = (ProductoDTO)prod.get(j);
                if(dto.getEntidad().getIdCategoria()==dtoProd.getEntidad().getClaveCategoria().getIdCategoria())
                    cont++;
            }
            if(cont>0)
                dsPie.setValue("("+dto.getEntidad().getIdCategoria()+") "+dto.getEntidad().getNombreCategoria(), cont);
        }
        return dsPie;
    }

}
