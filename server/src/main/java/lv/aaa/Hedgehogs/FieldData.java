package lv.aaa.Hedgehogs;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FieldData {
    //STATIC PART

    static private Map<String, FieldData> allFields = new HashMap<>();

    {

    }

    public static FieldData getFieldByID(String id){
        FieldData rez = allFields.get(id);
        if (rez == null){
            //TODO: find in base
        }

        return rez;
    }


    //NORMAL PART
    private String id;
    private Integer sizeX;
    private Integer sizeY;

    String[] data[][];

    public FieldData(Integer sizeX, Integer sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.id = UUID.randomUUID().toString(); //TODO: can take from base record id

        allFields.put(id, this);

        //TODO: generate field
    }

    public String getId() {
        return id;
    }
}
