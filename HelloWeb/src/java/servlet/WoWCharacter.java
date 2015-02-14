/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.util.Date;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author Mark
 * Class to be used to make a WoWCharacter object
 */
public class WoWCharacter {
    private int intLastModified;
    private Date dLastModified;
    private String sName;
    private String sRealm;
    private String sBattleGroup;
    private int intClass;
    private int intRace;
    private int intGender;
    private int intLevel;
    private int intAchievementPoints;
    private String sThumbnail;
    private String sCalcClass;
    private int intHonorableKills;
    
    
    WoWCharacter(JsonObject objIn){
        //intLastModified = objIn.getInt("lastModified");
        sName = objIn.getString("name");
        sRealm = objIn.getString("realm");
        sBattleGroup = objIn.getString("battlegroup");
        intClass = objIn.getInt("class");
        intRace = objIn.getInt("race");
        intGender = objIn.getInt("gender");
        intLevel = objIn.getInt("level");
        intAchievementPoints = objIn.getInt("achievementPoints");
        sThumbnail = objIn.getString("thumbnail");
        sCalcClass = objIn.getString("calcClass");
        intHonorableKills = objIn.getInt("totalHonorableKills");
        
    }
    
    /**
     * 
     * @return the characters name
     */
    public String getName(){
        return sName;
    }
    
    /**
     * 
     * @return the characters realm
     */
    public String getRealm(){
        return sRealm;
    }
    
    /**
     * 
     * @return the characters battle group
     */
    public String getBattleGroup(){
        return sBattleGroup;
    }
    
    /**
     * 
     * @return the characters class
     */
    public int getCharClass(){
        return intClass;
    }
    
    /**
     * 
     * @return the characters race
     */
    public int getRace(){
        return intRace;
    }
    
    /**
     * 
     * @return the characters gender
     */
    public int getGender(){
        return intGender;
    }
    
    /**
     * 
     * @return the characters level
     */
    public int getLevel(){
        return intLevel;
    }
    
    /**
     * 
     * @return The character's achievement points
     */
    public int getAchievementPoints(){
        return intAchievementPoints;
    }
    
    /**
     * 
     * @return The thumbnail?
     */
    public String getThumbnail(){
        return sThumbnail;
    }
    
    /**
     * 
     * @return the ClacClass. Not sure what this is
     */
    public String getCalcClass(){
        return sCalcClass;
    }
    
    /**
     * 
     * @return the number of honorable kills the character has.
     */
    public int getHonorableKills(){
        return intHonorableKills;
    }
    
    
    
    
    
}
