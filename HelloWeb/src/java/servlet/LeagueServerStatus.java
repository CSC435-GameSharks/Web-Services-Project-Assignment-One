/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 *
 * @author kellymaestri
 */
@WebServlet(name = "LServServ", urlPatterns = {"/LServServ"})
public class LeagueServerStatus extends HttpServlet {

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
            String output = "";
            output = startingHTML("League Server Status");
            output += makeAPIRequest();
            output += endHTML();

            try {
                out.println(output);
            } catch (Exception ex) {
                System.out.println(ex);
                out.println(ex);
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

    private String startingHTML(String strTitle) {
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html>");
        sb.append("   <head>");
        sb.append("       <title>");
        sb.append("           ").append(strTitle);
        sb.append("       </title>");
        sb.append("   </head>");
        sb.append("   <body>");

        return sb.toString();
    }

    private String endHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("   </body>");
        sb.append("</html>");

        return sb.toString();

    }

    private String makeAPIRequest() {
        InputStream is = null;
        int numServs;
        StringBuilder sb = new StringBuilder();
        ArrayList<String> regions = new ArrayList<String>();
        try {
            is = new URL("http://status.leagueoflegends.com/shards").openStream();

            JsonReader jsonReader = Json.createReader(is);

            JsonArray json = jsonReader.readArray();
            //sbReturn.append(json.toString());

            numServs = json.size();
            for (int i = 0; i < numServs; i++) {
                JsonObject temp = json.getJsonObject(i);
                String blah = temp.get("slug").toString();
                blah = blah.replace("\"", "");
                regions.add(blah);
                //sbReturn.append(blah);

            }
            //out.println(json.get("kmae26").toString());
            for (int i = 0; i < regions.size(); i++) {

                is = new URL("http://status.leagueoflegends.com/shards/" + regions.get(i)).openStream();
                jsonReader = Json.createReader(is);
                JsonObject jsonO = jsonReader.readObject();
                sb.append("Server name: ").append(jsonO.get("name").toString().replace("\"", ""));
                sb.append("</br>");

                JsonArray serv = jsonO.getJsonArray("services");
                int size = serv.size();
                for (int j = 0; j < size; j++) {
                    JsonObject tmp = serv.getJsonObject(j);
                    String name = tmp.getString("name");
                    String status = tmp.getString("status");
                    sb.append("&nbsp&nbsp&nbsp").append(name).append(" : ").append(status);

                    sb.append("</br>");
                }

            //JsonValue serv = jsonO.get("services");
                //sbReturn.append(serv.toString());
                //sbReturn.append("</br>");sbReturn.append("</br>");
                // sbReturn.append("Server status: " + serv.get("status"));
                sb.append("</br>");
                sb.append("</br>");
            //sbReturn.append(jsonO.toString());

            }

            jsonReader.close();

        } catch (MalformedURLException ex) {
            Logger.getLogger(LeagueServerStatus.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ioe) {
            Logger.getLogger(LeagueServerStatus.class.getName()).log(Level.SEVERE, null, ioe);

        } catch (Exception e) {
            Logger.getLogger(LeagueServerStatus.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(LeagueServerStatus.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sb.toString();
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
