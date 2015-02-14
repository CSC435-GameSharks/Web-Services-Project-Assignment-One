/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Mark
 */
@WebServlet(name = "CharServ", urlPatterns = {"/CharServ"})
public class WoWCharServ extends HttpServlet {

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
            //http://us.battle.net/api/wow/character/firetree/joepaterno
            //?name=name&realm=realm
            HttpSession s = request.getSession();
            String sCharName = "";
            String sCharRealm = "";
            
            //Check the session for a realm
            if(s.getAttribute("charRealm") != null){
                sCharRealm = s.getAttribute("charRealm").toString();
            }
            
            //Check the session for a name
            if(s.getAttribute("charName") != null){
                sCharName = s.getAttribute("charName").toString();
            }

            
            //Check if there are any quert string infromation
            //if we have these we want to use them instead of what
            //we got from the session.  We also want to overwrite the session
            if(request.getParameter("name") != null){
                sCharName = request.getParameter("name");
                s.setAttribute("charName", sCharName);
                
            }
            
            if(request.getParameter("realm") != null){
                sCharRealm = request.getParameter("realm");
                s.setAttribute("charRealm", sCharRealm );
            }
            
            
            
            //Start building the output
            String sOutput = "";
            WoWCharacter wowChar = null;
            sOutput = startHTML("WoW Character Overview", "WoWStyle.css");
            
            //Check to see if we have valid information to make a request
            if(sCharName == "" || sCharRealm == "" ){
                sOutput += "</br>ERROR: Bad User or Realm  ";
            }else{
                wowChar = makeServerAPIRequest(sCharName, sCharRealm);
                sOutput += createCharHTML(wowChar);
                //sOutput += wowChar.getName();
                //sOutput += wowChar.getThumbnail();
                //sOutput += "</br> " + s.getAttribute("charName");
            }
            
            sOutput += closeHTML();
            
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
    private String startHTML(String strTitle) {
        StringBuilder sbReturn = new StringBuilder();

        sbReturn.append("<!DOCTYPE html>");
        sbReturn.append("<html>");
        sbReturn.append("   <head>");
        sbReturn.append("       <title>");
        sbReturn.append("           " + strTitle);
        sbReturn.append("       </title>");
        sbReturn.append("   </head>");
        sbReturn.append(inputHTML());
        sbReturn.append("   <body>");
        
        return sbReturn.toString();
    }

    /**
     *
     * @param strTitle the title of the page
     * @param strCssSheet the location and name of the desired css
     * @return returns a string that is the start of an html page
     */
    private String startHTML(String strTitle, String strCssSheet) {
        StringBuilder sbReturn = new StringBuilder();

        sbReturn.append("<!DOCTYPE html>");
        sbReturn.append("<html>");
        sbReturn.append("   <head>");
        sbReturn.append("       <title>");
        sbReturn.append("           " + strTitle);
        sbReturn.append("       </title>");
        sbReturn.append("       <link rel=\"stylesheet\" type=\"text/css\" href=\"" + strCssSheet + "\">");
        sbReturn.append("   </head>");
        sbReturn.append(inputHTML());
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
        
        sbReturn.append("<script>");
        sbReturn.append("   function btnClick(){");
        sbReturn.append("       var txtRealm = document.getElementById(\"txtRealm\");");
        sbReturn.append("       var txtName = document.getElementById(\"txtName\");");
        sbReturn.append("       window.location.assign(\"http://localhost:8080/HelloWeb/CharServ?name=\" + txtName.value + \"&realm=\" + txtRealm.value);");
        sbReturn.append("   }");
        sbReturn.append("</script>");
        sbReturn.append("Character Name:<input id=\"txtName\" type=\"text\" name=\"txtName\"></br>");
        sbReturn.append("Realm Name:<input id=\"txtRealm\" type=\"text\" name=\"txtRealm\"></br>");
        sbReturn.append("<input id=\"clickMe\" type=\"button\" value=\"Submit\" onclick=\"btnClick();\"></br>");
        
        return sbReturn.toString();
    }
    
    private String createCharHTML(WoWCharacter wowChar){
        StringBuilder sbReturn = new StringBuilder();
        
        sbReturn.append("<div class=\"mainDiv\">");
        sbReturn.append("   <h2 class=\"characterTitle\">");
        sbReturn.append("       Character Profile");
        sbReturn.append("   </h2>");
        sbReturn.append("   <table>");
        sbReturn.append("       <tr>");
        sbReturn.append("           <td class=\"mainDivPhoto\" rowspan=\"5\">");
        sbReturn.append("               <img src=\"http://us.battle.net/static-render/us/" + wowChar.getThumbnail() + "\"/>");
        sbReturn.append("           </td>");
        sbReturn.append("       </tr>");
        sbReturn.append("       <tr>");
        sbReturn.append("           <td class=\"nameCell\">");
        sbReturn.append("               " + wowChar.getName());
        sbReturn.append("           </td>");
        sbReturn.append("       </tr>");
        sbReturn.append("       <tr>");
        sbReturn.append("           <td>");
        sbReturn.append("               Level " + wowChar.getLevel() + " " 
                + wowChar.getRace() + " " + wowChar.getCharClass());
        sbReturn.append("           </td>");
        sbReturn.append("       </tr>");
        sbReturn.append("       <tr>");
        sbReturn.append("           <td>");
        sbReturn.append("               Realm: " + wowChar.getRealm());
        sbReturn.append("           </td>");
        sbReturn.append("       </tr>");
        sbReturn.append("       <tr>");
        sbReturn.append("           <td>");
        sbReturn.append("               Battlegroup: " + wowChar.getBattleGroup());
        sbReturn.append("           </td>");
        sbReturn.append("       </tr>");
        sbReturn.append("       <tr colspan=\"2\">");
        sbReturn.append("           <td>");
        sbReturn.append("               Honourable Kills: " + wowChar.getHonorableKills());
        sbReturn.append("           </td>");
        sbReturn.append("       </tr>");
        sbReturn.append("       <tr colspan=\"2\">");
        sbReturn.append("           <td>");
        sbReturn.append("               Achievement Points: " + wowChar.getAchievementPoints());
        sbReturn.append("           </td>");
        sbReturn.append("       </tr>");
        sbReturn.append("   </table>");
        sbReturn.append("<div>");
        
        
        return sbReturn.toString();
    }
    
    /**
     * 
     * @return A WoWCharacter using the user supplied realm and character name
     */
    private WoWCharacter makeServerAPIRequest(String sCharName, String sCharRealm){
        WoWCharacter wowChar = null;
        InputStream is = null;
        
        try{
            is = new URL("http://us.battle.net/api/wow/character/" + sCharRealm + "/" + sCharName).openStream();
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            
            wowChar = new WoWCharacter(jsonObject);
            
            
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
        
        return wowChar;
    }
}
