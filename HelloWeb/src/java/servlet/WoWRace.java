/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import javax.json.JsonObject;

/**
 *
 * @author Mark
 */
public class WoWRace {
    private int intID;
    private int intMask;
    private String sSide;
    private String sName;
    
    /**
     * creates a race object from a JsonObject
     * @param objIn 
     */
    WoWRace(JsonObject objIn){
        intID = objIn.getInt("id");
        intMask = objIn.getInt("mask");
        sSide = objIn.getString("side");
        sName = objIn.getString("name");
    }
    
    /**
     * 
     * @return the races id
     */
    public int getID(){
        return intID;
    }
    
    /**
     * 
     * @return return the mask
     */
    public int getMask(){
        return intMask;
    }
    
    /**
     * 
     * @return the race's side
     */
    public String getSide(){
        return sSide;
    }
    
    /**
     * 
     * @return  the race's name
     */
    public String getName(){
        return sName;
    }
    
    
}
