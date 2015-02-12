/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mark
 */
@WebServlet(name = "ServServ", urlPatterns = {"/ServServ"})
public class ServServ extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            
            //out.println("<h1>Servlet ServServ at " + request.getContextPath() + "</h1>");
            
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

    /**
     * 
     * @param strTitle the title of the page
     * @return returns a string that is the start of an html page
     */
    private String startHTML(String strTitle){
        StringBuilder sbReturn = new StringBuilder();
        
        sbReturn.append("<!DOCTYPE html>");
        sbReturn.append("<html>");
        sbReturn.append("   <head>");
        sbReturn.append("       <title>");
        sbReturn.append("           " + strTitle);
        sbReturn.append("       </title>");
        sbReturn.append("   </head>");
        sbReturn.append("   <body>");
        
        return sbReturn.toString();
    }
    
    /**
     * 
     * @param strTitle the title of the page
     * @param strCssSheet the location and name of the desired css
     * @return returns a string that is the start of an html page
     */
    private String startHTML(String strTitle, String strCssSheet){
        StringBuilder sbReturn = new StringBuilder();
        
        sbReturn.append("<!DOCTYPE html>");
        sbReturn.append("<html>");
        sbReturn.append("   <head>");
        sbReturn.append("       <title>");
        sbReturn.append("           " + strTitle);
        sbReturn.append("       </title>");
        sbReturn.append("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + strCssSheet + "\">");       
        sbReturn.append("   </head>");
        sbReturn.append("   <body>");
        
        return sbReturn.toString();
    }
    
    /**
     * 
     * @return returns the closing of the html page.
     */
    private String closeHTML(){
        StringBuilder sbReturn = new StringBuilder();
        
        sbReturn.append("   </body>");
        sbReturn.append("</html>");
        
        return sbReturn.toString();
        
    }
    
    /**
     * 
     * @param aryServer, the list of servers
     * @return a HTML table with the servers and statuses in it
     */
    private String createServerTable(WoWServer[] aryServer){
        StringBuilder sbReturn = new StringBuilder();
        
        //Open the table
        sbReturn.append("<table>");
        sbReturn.append("   <caption>");
        sbReturn.append("       WoW Server Status");
        sbReturn.append("   </caption>");
        sbReturn.append("   <tr>");
        sbReturn.append("       <th>");
        sbReturn.append("           Name");
        sbReturn.append("       </th>");
        sbReturn.append("       <th>");
        sbReturn.append("           Status");
        sbReturn.append("       </th>");
        sbReturn.append("   </tr>");

        for(int i = 0; i < aryServer.length; i++){
            sbReturn.append("<tr>");
            sbReturn.append("   <td>");
            sbReturn.append("       " + aryServer[i].getName());
            sbReturn.append("   </td>");
            sbReturn.append("   <td>");
            sbReturn.append("       " + aryServer[i].getStatus());
            sbReturn.append("   </td>");
            sbReturn.append("</tr>");
        }
        
        sbReturn.append("</table>");
       
        return sbReturn.toString();
    }
    
    
    
}
