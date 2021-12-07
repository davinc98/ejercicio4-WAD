/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author leoj_
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

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

        if (accion.equals("listaDeProductos")) {
            listaDeProductos(request, response);
        } else {
            if (accion.equals("nuevo")) {
                agregarProducto(request, response);
            } else {
                if (accion.equals("eliminar")) {
                    eliminarProducto(request, response);
                } else {
                    if (accion.equals("actualizar")) {
                        actualizarProducto(request, response);
                    } else {
                        if (accion.equals("guardar")) {
                            almacenarProducto(request, response);
                        } else {
                            if (accion.equals("ver")) {
                                mostrarProducto(request, response);
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

    private void listaDeProductos(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        try {
            Collection lista = dao.readAll();
            request.setAttribute("listaDeProductos", lista);

            RequestDispatcher rd = request.getRequestDispatcher("/productos/listaProductos.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher vista = request.getRequestDispatcher("/productos/productosForm.jsp");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        dto = dao.delete(dto);
        
        if(dto!= null){
            request.setAttribute("mensaje", "<b>"+dto.getEntidad().getNombreProducto()+"</b> eliminado correctamente.");
            request.setAttribute("alert", "alert-warning");
        }else{
            request.setAttribute("mensaje", "Ocurrio un error al eliminar <b>"+dto.getEntidad().getNombreProducto()+"</b>.");
            request.setAttribute("alert", "alert-danger");
        }    
        
        
        listaDeProductos(request, response);
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("/productos/productosForm.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            vista.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("id")));

        RequestDispatcher vista = request.getRequestDispatcher("/productos/datosProducto.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("producto", dto);
            vista.forward(request, response);

        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarProducto(HttpServletRequest request, HttpServletResponse response) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        
        CategoriaDAO daoC = new CategoriaDAO();
        CategoriaDTO dtoC = new CategoriaDTO();

        if (!request.getParameter("txtIdProducto").equals("")) {
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
        }

        dto.getEntidad().setNombreProducto(request.getParameter("txtNombre"));
        dto.getEntidad().setDescripcionProducto(request.getParameter("txtDescripcion"));
        dto.getEntidad().setPrecio(Float.parseFloat(request.getParameter("txtPrecio")));
        dto.getEntidad().setExistencia(Integer.parseInt(request.getParameter("txtExistencia")));
        dto.getEntidad().setStockMinimo(Integer.parseInt(request.getParameter("txtStock")));
        
        //Recuerar la entidad de Categoria
        dtoC.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("txtClaveCategoria")));
        dtoC = daoC.read(dtoC);
        
        dto.getEntidad().setClaveCategoria(dtoC.getEntidad());

        if (!request.getParameter("txtIdProducto").equals("")) {//CREAR
            dao.update(dto);
            request.setAttribute("mensaje", "<b>"+dto.getEntidad().getNombreProducto()+"</b> actualizado con exito.");
            request.setAttribute("alert", "alert-warning");
        } else {
            dao.create(dto);
            request.setAttribute("mensaje", "<b>"+dto.getEntidad().getNombreProducto()+"</b> almacenado con exito.");
            request.setAttribute("alert", "alert-success");
        }
        listaDeProductos(request, response);

    }

    private void mostrarReporte(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void mostrarGrafica(HttpServletRequest request, HttpServletResponse response) {
        
        JFreeChart graficaProductos = ChartFactory.createBarChart(
         "Productos",           
         "",            
         "Existencia",            
         obtenerExistenciaProductos(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
        
        String archivo = getServletConfig().getServletContext().getRealPath("/graficaProductos.png");
        try {
            ChartUtils.saveChartAsPNG(new File(archivo), graficaProductos, 1000, 500);
            RequestDispatcher vista = request.getRequestDispatcher("graficaProductos.jsp");
            vista.forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private CategoryDataset obtenerExistenciaProductos(){
        DefaultCategoryDataset dataset  = new DefaultCategoryDataset();
        ProductoDAO dao = new ProductoDAO();
        List datos = dao.readAll();
        
        for (int i = 0; i < datos.size(); i++) {
            ProductoDTO dto = (ProductoDTO) datos.get(i);
            dataset.addValue(dto.getEntidad().getExistencia(), "("+dto.getEntidad().getIdProducto()+") "+dto.getEntidad().getNombreProducto(), "Producto");
            //dataset.addValue(dto.getEntidad().getExistencia(),"Producto", "("+dto.getEntidad().getIdProducto()+") "+dto.getEntidad().getNombreProducto());
        }
        return dataset;
    }

}
