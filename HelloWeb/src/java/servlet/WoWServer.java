/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author Mark
 * Class to be used to make WoWServ Objects
 */
public class WoWServer {
    private String sType;
    private String sPopulation;
    private boolean bQueue;
    private String sStatus;
    private String sName;
    private String sSlug;
    private String sBattleGroup;
    private String sLocale;
    private String sTimeZone;
    private String[] aryConnectedRealms;
    
    /**
     * Class constructor
     * @param objIn used to populate server information
     */
    WoWServer(JsonObject objIn){
        //Get the easy ones
        sType = objIn.getString("type");
        sPopulation = objIn.getString("population");
        bQueue = objIn.getBoolean("queue");
        sStatus = objIn.getString("status");
        sName = objIn.getString("name");
        sSlug = objIn.getString("slug");
        sBattleGroup = objIn.getString("battlegroup");
        sLocale = objIn.getString("locale");
        sTimeZone = objIn.getString("timezone");
        
        //Make a JsonArray for the connected Servers
        JsonArray jsonArray = objIn.getJsonArray("connected_realms");
        //now we know how many connected realms we have <3
        aryConnectedRealms = new String[jsonArray.size()];
        
        for(int i = 0; i < jsonArray.size(); i++){
            JsonObject obj;
        }
        
        
        
    }
}
