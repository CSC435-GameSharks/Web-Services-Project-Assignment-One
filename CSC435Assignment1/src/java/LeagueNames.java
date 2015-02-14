/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.servlet.ServletException;
import static javax.servlet.SessionTrackingMode.URL;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kellymaestri
 */
public class LeagueNames extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet League Names</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet League Names Look Up </h1>");

            out.println("<br />Summoner Name\n");
            out.println("<br />\n" );
            out.println("<input id=\"userName\" />\n" );
            out.println("<input type=\"submit\" onclick=\"summonerLookUp(out);\" />");
//            try {
//
//                InputStream is = new URL("https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/kmae26?api_key=cc288bed-bfa3-4158-9642-6b276a1381d7").openStream();
//             
//                JsonReader jsonReader = Json.createReader(is);
//                
//                JsonObject json = jsonReader.readObject();
//               // out.println(json.toString());
//                //out.println(json.get("kmae26").toString());
//                JsonObject values = json.getJsonObject("kmae26");
//                out.print("</br>");
//                out.println("Name: "+values.get("name").toString());
//                out.print("</br>");
//                out.println("ID: "+values.get("id").toString());
//                out.print("</br>");
//                out.println("Level: "+values.get("summonerLevel").toString());
//                
//
//                jsonReader.close();
//                is.close();
//                
//
//            } catch (Exception ex) {
//                System.out.println(ex);
//                out.println(ex);
//                out.println("</body>");
//                out.println("</html>");
//            }

        }
     
    }
    
    private void summonerLookup(PrintWriter out, String n){
    String name = n.toLowerCase().trim();
        try {

                InputStream is = new URL("https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/"+name+"?api_key=cc288bed-bfa3-4158-9642-6b276a1381d7").openStream();
             
                JsonReader jsonReader = Json.createReader(is);
                
                JsonObject json = jsonReader.readObject();
               // out.println(json.toString());
                //out.println(json.get("kmae26").toString());
                JsonObject values = json.getJsonObject("kmae26");
                out.print("</br>");
                out.println("Name: "+values.get("name").toString());
                out.print("</br>");
                out.println("ID: "+values.get("id").toString());
                out.print("</br>");
                out.println("Level: "+values.get("summonerLevel").toString());
                

                jsonReader.close();
                is.close();
                

            } catch (Exception ex) {
                System.out.println(ex);
                out.println(ex);
                out.println("</body>");
                out.println("</html>");
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

}
