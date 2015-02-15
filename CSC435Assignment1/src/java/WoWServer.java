/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author Mark Class to be used to make WoWServ Objects
 */
public class WoWServer {

    private String sType;
    private String sPopulation;
    private boolean bQueue;
    private boolean bStatus;
    private String sName;
    private String sSlug;
    private String sBattleGroup;
    private String sLocale;
    private String sTimeZone;
    private String[] aryConnectedRealms;

    /**
     * Class constructor
     *
     * @param objIn used to populate server information
     */
    WoWServer(JsonObject objIn) {
        //Get the easy ones
        sType = objIn.getString("type");
        sPopulation = objIn.getString("population");
        bQueue = objIn.getBoolean("queue");
        bStatus = objIn.getBoolean("status");
        sName = objIn.getString("name");
        sSlug = objIn.getString("slug");
        sBattleGroup = objIn.getString("battlegroup");
        sLocale = objIn.getString("locale");
        sTimeZone = objIn.getString("timezone");

        //Make a JsonArray for the connected Servers
        JsonArray jsonArray = objIn.getJsonArray("connected_realms");
        //now we know how many connected realms we have <3
        aryConnectedRealms = new String[jsonArray.size()];

        //for (int i = 0; i < jsonArray.size(); i++) {
        //    JsonObject obj = jsonArray.getJsonObject(i);
        //}

    }

    /**
     * 
     * @return aName, the name of the server
     */
    public String getName() {
        return sName;
    }

    /**
     * 
     * @return sType, what type of server is it. PVP, PVE
     */
    public String getType(){
        return sType;
    }
    
    /**
     * 
     * @return sPopulation, the current population level. Med, Low, High
     */
    public String getPopulation(){
        return sPopulation;
    }
    
    /**
     * 
     * @return bQuere, Ture/False if there is a login queue
     */
    public boolean getQueue(){
        return bQueue;
    }
    
    /**
     * 
     * @return bStatus, T/F is the server is up or not.
     */
    public boolean getStatus(){
        return bStatus;
    }
    
    /**
     * 
     * @return sSlug, the slang name of the server
     */
    public String getSlug(){
        return sSlug;
    }
    
    /**
     * 
     * @return the name of the battle group.
     */
    public String getBattleGroup(){
        return sBattleGroup;
    }
    
    /**
     * 
     * @return that language the server is
     */
    public String getLocale(){
        return sLocale;
    }
    
    /**
     * 
     * @return what time zone the server is in.
     */
    public String geTimeZone(){
        return sTimeZone;
    }
    
}
