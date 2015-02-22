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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kellymaestri
 */
@WebServlet(name = "LSumServ", urlPatterns = {"/LSumServ"})
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

            HttpSession s = request.getSession();
            String sName = "";

            if (s.getAttribute("sumName") != null) {
                sName = s.getAttribute("sumName").toString();
            }

            if (request.getParameter("name") != null) {
                sName = request.getParameter("name");
                s.setAttribute("sumName", sName);

            }

            String strOutput = "";
            strOutput = startingHTML("League Summoner Look Up");

            if (sName == "") {
                 strOutput += "</br>Invalid Summoner Name ";
            } else {
                strOutput += makeAPIRequest(sName);
            }

            strOutput += endHTML();

            try {
                out.println(strOutput);
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
        sb.append(makeInputHTML());
        sb.append("   <body>");

        return sb.toString();
    }

    private String makeInputHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("<script>");
        sb.append("   function click1(){");
        sb.append("       var name = document.getElementById(\"name\");");
        sb.append("       window.location.assign(\"http://localhost:8080/CSC435Assignment1/LSumServ?name=\" + name.value);");
        sb.append("   }");
        sb.append("</script>");
        sb.append("Summoner Name:<input id=\"name\" type=\"text\" name=\"name\"></br>");
        sb.append("<input id=\"Submit\" type=\"button\" value=\"Submit\" onclick=\"click1();\">");
        return sb.toString();
    }

    private String endHTML() {
        StringBuilder sb = new StringBuilder();

        sb.append("   </body>");
        sb.append("</html>");

        return sb.toString();

    }

    private String makeAPIRequest(String n) {
        InputStream is = null;
        StringBuilder sb = new StringBuilder();
        try {

            is = new URL("https://na.api.pvp.net/api/lol/na/v1.4/summoner/by-name/" + n + "?api_key=cc288bed-bfa3-4158-9642-6b276a1381d7").openStream();

            JsonReader jsonReader = Json.createReader(is);

            JsonObject json = jsonReader.readObject();
            // out.println(json.toString());
            //out.println(json.get("kmae26").toString());
            JsonObject values = json.getJsonObject(n);
            sb.append("</br>");
            sb.append("Summoner Name: ").append(values.get("name").toString().replace("\"", ""));
            sb.append("</br>");
            sb.append("ID: ").append(values.get("id").toString());
            sb.append("</br>");
            sb.append("Level: ").append(values.get("summonerLevel").toString());

            jsonReader.close();

        } catch (MalformedURLException ex) {
            Logger.getLogger(LeagueNames.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ioe) {
            Logger.getLogger(LeagueNames.class.getName()).log(Level.SEVERE, null, ioe);

        } catch (Exception e) {
            Logger.getLogger(LeagueNames.class.getName()).log(Level.SEVERE, null, e);

        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(LeagueNames.class.getName()).log(Level.SEVERE, null, ex);
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
