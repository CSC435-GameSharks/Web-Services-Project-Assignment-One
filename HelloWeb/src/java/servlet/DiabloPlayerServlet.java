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
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Diablo.Career;

/**
 *
 * @author csaroff
 */
@WebServlet(name = "DiabloPlayer", urlPatterns = {"/DiabloPlayer"})
public class DiabloPlayerServlet extends HttpServlet {

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
            String battleTagName = "";
            String battleTagCode = "";
            
            //Check the session for a realm
            if(s.getAttribute("battleTagName") != null){
                battleTagName = s.getAttribute("battleTagName").toString();
            }
            System.out.println("Battle Tag Name: " + battleTagName);
            //Check the session for a name
            if(s.getAttribute("battleTagCode") != null){
                battleTagCode = s.getAttribute("battleTagCode").toString();
            }
            System.out.println("Battle Tag Code:" + battleTagCode);

            
            //Check if there are any quert string infromation
            //if we have these we want to use them instead of what
            //we got from the session.  We also want to overwrite the session
            if(request.getParameter("battleTagName") != null){
                battleTagName = request.getParameter("battleTagName");
                s.setAttribute("battleTagName", battleTagName);
                
            }
            
            if(request.getParameter("battleTagCode") != null){
                battleTagCode = request.getParameter("battleTagCode");
                s.setAttribute("battleTagCode", battleTagCode );
            }
            
            
            
            //Start building the output
            String sOutput = "";
            Career diabloPlayer = null;
            sOutput = startHTML("Diablo Characters Overview", "WoWStyle.css");
            
            //Check to see if we have valid information to make a request
            if(battleTagName .equals("") || battleTagCode.equals("")){
                sOutput += "</br>ERROR: Bad User or Realm  ";
            }else{
                diabloPlayer = makeServerAPIRequest(battleTagName, battleTagCode);
                sOutput += createCareerHTML(diabloPlayer);
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
    }     /**
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
        sbReturn.append("       var txtBTName = document.getElementById(\"txtBTName\");");
        sbReturn.append("       var txtBTCode = document.getElementById(\"txtBTCode\");");
        sbReturn.append("       window.location.assign(\"http://localhost:8080/HelloWeb/DiabloPlayer?battleTagName=\" + txtBTName.value + \"&battleTagCode=\" + txtBTCode.value);");
        sbReturn.append("   }");
        sbReturn.append("</script>");
        sbReturn.append("Battle Tag Name:<input id=\"txtBTName\" type=\"text\" name=\"txtBTName\"></br>");
        sbReturn.append("Battle Tag Code:<input id=\"txtBTCode\" type=\"text\" name=\"txtBTCode\"></br>");
        sbReturn.append("<input id=\"clickMe\" type=\"button\" value=\"Submit\" onclick=\"btnClick();\"></br>");
        
        return sbReturn.toString();
    }
    
    
        /**
     * 
     * @param diabloPlayer
     * @return an HTML table containing the player data
     */
    private String createCareerHTML(Career diabloPlayer){
        StringBuilder sbReturn = new StringBuilder();
        
    //<body>
    //    </br> 
    //  Summoner Name: Chaz </br>
    //    ID: 8453</br>
    //    Level: 30 
    //</body>


//        sbReturn.append("               <img src=\"http://us.battle.net/static-render/us/" + diabloPlayer.getThumbnail() + "\"/>");
//        sbReturn.append("<img src=\"http://i7.minus.com/iDiaMmIM6QE9R.jpg\">");
        sbReturn.append("           </br>\n");
        sbReturn.append("Battle Tag:    " + diabloPlayer.getBattleTag()+ "</br>\n");
        sbReturn.append("Paragon Level " + diabloPlayer.getParagonLevel() + "</br>\n");
//        sbReturn.append("               Last Hero Played: " + diabloPlayer.getLastHero());
        sbReturn.append("Number of Kills: " + "</br>\n");
        sbReturn.append("Monsters: " + diabloPlayer.getKills().getMonsters() + "</br>\n");
        sbReturn.append("Elites: " + diabloPlayer.getKills().getElites() + "</br>\n");
        sbReturn.append("Hardcore Monsters: " + diabloPlayer.getKills().getHardcoreMonsters());
    
        
        return sbReturn.toString();
    }
    /**
     * 
     * @return A WoWCharacter using the user supplied realm and character name
     */
    private Career makeServerAPIRequest(String  battleTagName, String battleTagCode){
        Career diabloPlayer = null;
        InputStream is = null;
        
        try{
            is = new URL("http://us.battle.net/api/d3/profile/" + battleTagName + "-" + battleTagCode + "/").openStream();
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            jsonReader.close();
            
            diabloPlayer = new Career(jsonObject);
            
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
        
        return diabloPlayer;
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
