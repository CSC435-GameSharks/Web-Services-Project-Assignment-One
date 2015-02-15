/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import Diablo.Career;
import Diablo.Follower;
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

/**
 *
 * @author csaroff
 */
@WebServlet(name = "DiabloFollower", urlPatterns = {"/DiabloFollower"})
public class DiabloFollowerServlet extends HttpServlet {
private static final Logger logger = Logger.getLogger(DiabloPlayerServlet.class.getName());
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
            String strFollower = "";
            if(request.getParameter("follower") != null){
                 strFollower = request.getParameter("follower");
            }
            //Start building the output
            String sOutput = "";
            Career diabloFollower = null;
            sOutput+= startHTML("Diablo Follower Overview");
            sOutput+=inputHTML();
            
            if(strFollower.equals("enchantress")||strFollower.equals("scoundrel")||strFollower.equals("templar")){
                Follower follower = this.makeServerAPIRequest(strFollower);
                sOutput+=follower.toHtmlString();
            }
            logger.log(Level.SEVERE, "Made it to line 60", new Exception());
            sOutput+=closeHTML();
            try{
                out.println(sOutput);
            } catch (Exception ex) {
                System.out.println(ex);
                out.println(ex);
                out.println("</body>");
                out.println("</html>");
            }
            

        }
    }
    /*
     * @param strTitle the title of the page
     * @return returns a string that is the start of an html page
     */
    private String startHTML(String strTitle) {
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
     * @return returns the closing of the html page.
     */
    private String closeHTML() {
        StringBuilder sbReturn = new StringBuilder();

        sbReturn.append("   </body>");
        sbReturn.append("</html>");

        return sbReturn.toString();

    }
    
    private String inputHTML(){
        StringBuilder sbReturn = new StringBuilder();
        
        sbReturn.append("<html>\n" +
        "    <head>\n" +
        "        <title>\n" +   
        "            Diablo Follower Information\n" +
        "        </title>\n" +
        "    </head>\n" +
        "    <body>\n" +
        "        <h1> Diablo Follower Information </h1>\n" +
        "<select onchange=\"this.options[this.selectedIndex].value && (window.location.assign(this.options[this.selectedIndex].value));\">\n" +
        "    <option value=\"\">Select...</option>\n" +
        "    <option value=\"DiabloFollower?follower=enchantress\">Enchantress</option>\n" +
        "    <option value=\"DiabloFollower?follower=templar\">Templar</option>\n" +
        "    <option value=\"DiabloFollower?follower=scoundrel\">Scoundrel</option>\n" +
        "</select>\n" +
        "    </body>\n" +
        "</html>");
        
        return sbReturn.toString();
    }
    private Follower makeServerAPIRequest(String strFollower){
        Follower diabloFollower = null;
        InputStream is = null;
        
        try{
            is = new URL("http://us.battle.net/api/d3/data/follower/" + strFollower).openStream();
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            
            diabloFollower = new Follower(jsonObject);
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(WoWServServ.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (IOException ioe){
            Logger.getLogger(WoWCharServ.class.getName()).log(Level.SEVERE, null, ioe);
        
        } catch(Exception e){
            Logger.getLogger(WoWCharServ.class.getName()).log(Level.SEVERE, null, e);
            
        }finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(WoWCharServ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return diabloFollower;
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
