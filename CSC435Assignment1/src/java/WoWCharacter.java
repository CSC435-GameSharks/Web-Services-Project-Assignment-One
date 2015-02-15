/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

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
    private String sClass;
    private int intRace;
    private String sRace;
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
        
        sClass = getClassString();
        sRace = getRaceString();
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
    public int getCharClassID(){
        return intClass;
    }
    
    
    public String getCharClass(){
        return sClass;
    }
    
    /**
     * 
     * @return the characters race
     */
    public int getRaceID(){
        return intRace;
    }
    
    public String getRace(){
        return sRace;
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
    
    
    private String getClassString(){
        String sReturn = null;
        
        InputStream is = null;
        
        try{
            is = new URL("http://us.battle.net/api/wow/data/character/classes").openStream();
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            JsonArray jsonArray = jsonObject.getJsonArray("classes");
            jsonReader.close();
            
            for(int i = 0; i < jsonArray.size(); i++){
                JsonObject obj = jsonArray.getJsonObject(i);
                if(obj.getInt("id") == intClass){
                    return obj.getString("name");
                }
                //aryServer[i] = new WoWServer(obj);                
            }
            
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
        return sReturn;
    }
    
    
    private String getRaceString(){
        String sReturn = null;
        
        InputStream is = null;
        
        try{
            is = new URL("http://us.battle.net/api/wow/data/character/races").openStream();
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            JsonArray jsonArray = jsonObject.getJsonArray("races");
            jsonReader.close();
            
            for(int i = 0; i < jsonArray.size(); i++){
                JsonObject obj = jsonArray.getJsonObject(i);
                if(obj.getInt("id") == intRace){
                    return obj.getString("name");
                }
            }
            
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
        return sReturn;
    }
    
    
}
