package lv.aaa.Hedgehogs;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HedgehogData {
    //STATIC PART
    private static Map<String, HedgehogData> allPlayers = new HashMap<>();

    {
        /*STATIC INIT
         TODO: get from base all players
                or load when player open the game
                and unload when inactive for ~15min
        */
    }

    public static HedgehogData getPlayerByID(String id){
        HedgehogData rez = allPlayers.get(id);
        if (rez == null){
            //TODO: find in base
        }

        return rez;
    }


    //NORMAL PART
    private String id;
    private String name;
    private int winCount;
    private int loseCount;
    private int allGameCount;
    private long lastVisit;

    public HedgehogData(String name) {
        this.name = name;
        this.winCount = 0;
        this.loseCount = 0;
        this.allGameCount = 0;
        this.lastVisit = new Date().getTime(); //now

        this.id = UUID.randomUUID().toString(); //TODO: can take from base record id

        allPlayers.put(this.id, this);
        /*TODO:
                constructor = registration -> create db record
                gameStart reaction -> db too
                gameEnd reaction -> db too
        */
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getAllGameCount() {
        return allGameCount;
    }

    public long getLastVisit() {
        return lastVisit;
    }


}
