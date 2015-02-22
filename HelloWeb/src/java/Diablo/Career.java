/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diablo;

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
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonValue;
/**
 *
 * @author csaroff
 */
public class Career {
    private String battleTag;
    private int paragonLevel;
    private int paragonLevelHardcore;
    private int paragonLevelSeason;
    private int paragonLevelSeasonHardcore;
    List<Hero> heroes;
    private int lastHeroPlayed;
    private int lastUpdated;
    private Kill kills;

    public Career(JsonObject objIn){
        battleTag = objIn.getString("battleTag");
        paragonLevel = objIn.getInt("paragonLevel");
        paragonLevelHardcore = objIn.getInt("paragonLevelHardcore");
        paragonLevelSeason = objIn.getInt("paragonLevelSeason");
        paragonLevelSeasonHardcore = objIn.getInt("paragonLevelSeasonHardcore");
        
        JsonArray jsonHeroes = objIn.getJsonArray("heroes");
        ArrayList<Hero> heroes = new ArrayList<Hero>();
        for(JsonValue jsonHero : jsonHeroes){
            heroes.add(new Hero((JsonObject)(jsonHero)));
        }
        this.heroes=heroes;
        
        lastHeroPlayed = objIn.getInt("lastHeroPlayed");
        lastUpdated = objIn.getInt("lastUpdated");
        kills = new Kill(objIn.getJsonObject("kills"));
    }
        public String toHtmlString(){
        StringBuilder sbReturn = new StringBuilder();
        
//        sbReturn.append("               <img src=\"http://us.battle.net/static-render/us/" + diabloPlayer.getThumbnail() + "\"/>");
//        sbReturn.append("<img src=\"http://i7.minus.com/iDiaMmIM6QE9R.jpg\">");
        sbReturn.append("           </br>\n");
        sbReturn.append("Battle Tag:    " + this.getBattleTag()+ "</br>\n");
        sbReturn.append("Paragon Level " + this.getParagonLevel() + "</br>\n");
//        sbReturn.append("               Last Hero Played: " + diabloPlayer.getLastHero());
        sbReturn.append("Number of Kills: " + "</br>\n");
        sbReturn.append("Monsters: " + this.getKills().getMonsters() + "</br>\n");
        sbReturn.append("Elites: " + this.getKills().getElites() + "</br>\n");
        sbReturn.append("Hardcore Monsters: " + this.getKills().getHardcoreMonsters() + "</br>\n");
        sbReturn.append("Heroes:" + "</br>\n");
        for(Hero hero : heroes){
            sbReturn.append(hero.toHTMLString());
        }
        
        return sbReturn.toString();
    }
    public String getBattleTag() {
        return battleTag;
    }

    public int getParagonLevel() {
        return paragonLevel;
    }

    public int getParagonLevelHardcore() {
        return paragonLevelHardcore;
    }

    public int getParagonLevelSeason() {
        return paragonLevelSeason;
    }

    public int getParagonLevelSeasonHardcore() {
        return paragonLevelSeasonHardcore;
    }

//    public int getLastHeroPlayed() {
//        return lastHeroPlayed;
//    }

    public int getLastUpdated() {
        return lastUpdated;
    }

    public Kill getKills() {
        return kills;
    }
    
}
