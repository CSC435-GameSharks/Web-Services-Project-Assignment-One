/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diablo;
import java.util.List;
import java.util.ArrayList;
import javax.json.*;
/**
 *
 * @author csaroff
 */
public class Hero {
    private String name;
    private int id;
    private int level;
    private boolean hardcore;
    private int gender;
    private int lastUpdated;
    private boolean dead;
    public Hero(JsonObject objIn){
        name = objIn.getString("name");
        id = objIn.getInt("id");
        level = objIn.getInt("level");
        hardcore = objIn.getBoolean("hardcore");
        gender = objIn.getInt("gender");
        lastUpdated = objIn.getInt("last-updated");
        dead = objIn.getBoolean("dead");
    }
    String toHTMLString(){
        StringBuilder sbReturn = new StringBuilder();
        
        sbReturn.append("           </br>\n");
        sbReturn.append("Name:    " + this.getName()+ "</br>\n");
        sbReturn.append("Level:    " + this.getLevel()+ "</br>\n");
        sbReturn.append("Dead:    " + (this.dead?"Yes":"No") + "</br>\n");
        
        return sbReturn.toString();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }

    public boolean isHardcore() {
        return hardcore;
    }

    public int getGender() {
        return gender;
    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public boolean isDead() {
        return dead;
    }
    
}
