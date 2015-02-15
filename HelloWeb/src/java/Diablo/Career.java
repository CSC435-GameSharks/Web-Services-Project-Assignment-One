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
/**
 *
 * @author csaroff
 */
public class Career {
    String battleTag;
    int paragonLevel;
    int paragonLevelHardcore;
    int paragonLevelSeason;
    int paragonLevelSeasonHardcore;
    ArrayList<Hero> heroes = new ArrayList<Hero>();
    int lastHeroPlayed;
    int lastUpdated;
    Kill kills;
    public Career(JsonObject objIn){
        
    }
    
}
