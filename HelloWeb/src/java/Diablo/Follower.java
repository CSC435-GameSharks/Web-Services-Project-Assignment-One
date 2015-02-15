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
public class Follower {
    private String slug;
    private String name;
    private String realName;
    private String portrait;
    private List<ActiveSkill> activeSkills;
    //Followers have no passive skills.
    
    public Follower(JsonObject objIn){
        slug=objIn.getString("slug");
        name=objIn.getString("name");
        realName=objIn.getString("realName");
        portrait=objIn.getString("portrait");
        
        //Convert the json array of active skills into a java list of active skills.
        JsonArray jsonActiveSkills = objIn.getJsonObject("skills").getJsonArray("active");
        ArrayList<ActiveSkill> activeSkills = new ArrayList<ActiveSkill>();
        for(JsonValue jsonActiveSkill : jsonActiveSkills){
            activeSkills.add(new ActiveSkill((JsonObject)(jsonActiveSkill)));
        }
        this.activeSkills=activeSkills;
    }

    public String getSlug() {
        return slug;
    }

    public String getName() {
        return name;
    }

    public String getRealName() {
        return realName;
    }

    public String getPortrait() {
        return portrait;
    }

    public List<ActiveSkill> getActiveSkills() {
        return activeSkills;
    }
    
    public String toHtmlString(){
        StringBuilder sbReturn = new StringBuilder();
        
        sbReturn.append("           </br>\n");
        sbReturn.append("Name:    " + this.getName()+ "</br>\n");
        for(ActiveSkill skill : activeSkills){
            sbReturn.append(skill.toHtmlString() + "</br>");
        }
        return sbReturn.toString();
    }
}