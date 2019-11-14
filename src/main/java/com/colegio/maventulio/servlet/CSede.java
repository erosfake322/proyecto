package com.colegio.maventulio.servlet;

import com.colegio.maventulio.interfaces.DAOSede;
import com.colegio.maventulio.modelo.Sede;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet(name = "CSede", urlPatterns = {"/CSede"})
public class CSede extends HttpServlet {
    
    DAOSede model = new DAOSede();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String operacion = request.getParameter("op");
            switch (operacion){
                case "listar":
                    Listar(request, response);
                    break;
                case "listarID":
                    ListarID(request, response);
                    break;
                case "insertar":
                    Insertar(request, response);
                    break;
                case "actualizar":
                    Actualizar(request, response);
                    break;
                case "eliminar":
                    Eliminar(request, response);
                    break;
            }
        }finally{
            out.close();
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

    private void Listar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        JSONArray array  = new JSONArray();
        model.ListarSede().forEach(elem ->{
            JSONObject json = new JSONObject();
            json.put("id", elem.getId_sede());
            json.put("nombre", elem.getNombre());
            json.put("logico", elem.isEstado());
            array.add(json);
        });
        out.print(array); 
    }

    private void ListarID(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        int id_sede = Integer.parseInt(request.getParameter("id"));
        JSONArray array  = new JSONArray();
        model.ListarSedeID(id_sede).forEach(elem ->{
            JSONObject json = new JSONObject();
            json.put("id", elem.getId_sede());
            json.put("nombre", elem.getNombre());
            json.put("logico", elem.isEstado());
            array.add(json);
        });
        out.print(array); 
    }

    private void Insertar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String nombre = request.getParameter("nombre");
        boolean logico = Boolean.parseBoolean(request.getParameter("logico"));
        Sede bean = new Sede();
        bean.setNombre(nombre);
        bean.setEstado(logico);
        int fila = model.InsertarSede(bean);
        if (fila != 0){
            out.print("EXITOI");
        } else {
            out.print("ERROR");
        }
    }

    private void Actualizar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
            PrintWriter out = response.getWriter();
            int id_Sede = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            boolean logico = Boolean.parseBoolean(request.getParameter("logico"));
            Sede bean = new Sede();
            bean.setId_sede(id_Sede);
            bean.setNombre(nombre);
            bean.setEstado(logico);
            
            int fila = model.ActualizarSede(bean);
            if (fila != 0){
                out.print("EXITOU");
            } else {
                out.print("ERROR");
            }
        } 
    

    private void Eliminar(HttpServletRequest request, HttpServletResponse response) throws IOException {
           PrintWriter out = response.getWriter();
            int id_Sede = Integer.parseInt(request.getParameter("id"));
            Sede bean = new Sede();
            bean.setId_sede(id_Sede);
            
            int fila = model.EliminarSede(bean);
            if (fila != 0){
                out.print("EXITOD");
            } else {
                out.print("ERROR");
            }
    }

}
