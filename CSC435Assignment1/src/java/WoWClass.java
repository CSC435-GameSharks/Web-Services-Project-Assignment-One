/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.json.JsonObject;

/**
 *
 * @author Mark
 */
public class WoWClass {
    private int intID;
    private String sMask;
    private String sPowerType;
    private String sName;
    
    /**
     * Creates an WoWClass object 
     * @param objIn, JsonObject
     */
    WoWClass(JsonObject objIn){
        intID = objIn.getInt("id");
        sMask = objIn.getString("mask");
        sPowerType = objIn.getString("powerType");
        sName = objIn.getString("name");
        
    }
    
    /**
     * 
     * @return the class id
     */
    public int getID(){
        return intID;
    }
    
    /**
     * 
     * @return the class mask
     */
    public String getMask(){
        return sMask;
    }

    /**
     * 
     * @return the class powertype
     */
    public String getPowerType(){
        return sPowerType;
    }
    
    /**
     * 
     * @return the class name
     */
    public String getName(){
        return sName;
    }
    
    
    
}
