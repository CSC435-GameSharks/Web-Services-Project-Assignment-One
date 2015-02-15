/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diablo;

import javax.json.JsonObject;
/**
 *
 * @author csaroff
 */
public class Kill {
    private int monsters;
    private int elites;
    private int hardcoreMonsters;
    public Kill(JsonObject objIn){
        monsters = objIn.getInt("monsters");
        elites = objIn.getInt("elites");
        hardcoreMonsters = objIn.getInt("hardcoreMonsters");
    }

    public int getMonsters() {
        return monsters;
    }

    public int getElites() {
        return elites;
    }

    public int getHardcoreMonsters() {
        return hardcoreMonsters;
    }
}
