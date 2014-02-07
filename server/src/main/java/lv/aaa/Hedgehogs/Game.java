package lv.aaa.Hedgehogs;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Game {
    //STATIC PART

    static private Map<String, Game> allGames = new HashMap<>();

    {

    }

    public static Game getFieldByID(String id){
        Game rez = allGames.get(id);
        if (rez == null){
            //TODO: find in base
        }

        return rez;
    }

    private String id;
    private HedgehogData rightPlayer;
    private HedgehogData leftPlayer;

    private FieldData field;

    public Game(HedgehogData rightPlayer, HedgehogData leftPlayer, int sizeX, int sizeY) {
        this.rightPlayer = rightPlayer;
        this.leftPlayer = leftPlayer;
        this.field = new FieldData(sizeX, sizeY);
        this.id = UUID.randomUUID().toString(); //TODO: can take from base record id

        allGames.put(id, this);
    }

    public String getId() {
        return id;
    }
}
