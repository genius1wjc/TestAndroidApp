package com.example.myapplication.module;

/**
 * Created by jiechao on 7/12/16.
 */
public class Team {
    private String teamName;
    private int teamWins;
    public  Team(String name, int wins)
    {
        teamName = name;
        teamWins = wins;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getTeamWins() {
        return teamWins;
    }
}
