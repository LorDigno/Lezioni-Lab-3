import com.google.gson.*;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.*;
public class JHMap {
    public static void main(String args[]){
        //creo l'oggeto da deserializzare
        JsonArray ja= new JsonArray();
        JsonObject jsonObject1 = new JsonObject();

        //addProperty per aggiungere (key, val) ad un JsonOnject
        jsonObject1.addProperty("name", "Mario Rossi");
        jsonObject1.addProperty("age", 35);

        //aggiungo all'array
        ja.add(jsonObject1);

        //uguale ma per il secondo
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("name", "Giovanni Bianchi");
        jsonObject2.addProperty("age", 41);
        ja.add(jsonObject2);

        //Map<String, Integer> hashMap=JHMap.data_binding(ja);
        Map<String, Integer> hashMap=JHMap.tree_model(ja);

        System.out.println(hashMap.get("Mario Rossi"));
        System.out.println(hashMap.get("Giovanni Bianchi"));
    }

    static Map<String,Integer> data_binding(JsonArray jsonArray ) {
        //preparo l'hasMap da rendere
        Map<String, Integer> hashMap = new HashMap<>();

        //creo il Gson
        Gson gson = new Gson();

        //Ottengo il TypeToken relativo all'array che leggo
        Type listType = new TypeToken< List<Map<String, Object> >>() {}.getType();

        //uso il TypeToken per ricavare una lista di coppie
        //uso Object perché alcuni valori sono stringhe mentre altri int
        List<Map<String, Object>> list = gson.fromJson(jsonArray, listType);

        //scorro le coppie
        for (Map<String, Object> entry : list) {
            String type = (String) entry.get("name");
            Integer amount = ((Double) entry.get("age")).intValue();
            // Gson parses numbers as Double
            //quindi è necessario perendere il double e poi castarlo ad int
            hashMap.put(type, amount);
        }

        return hashMap;
    }

    static Map<String, Integer> tree_model(JsonArray jsonArray){
        //preparo l'HashMap
        Map<String, Integer> hashMap = new HashMap<>();

        //scorro il jsonArray di jsonElements
        for (JsonElement element : jsonArray) {
            //ottengo il jsonObject
            JsonObject jsonObject = element.getAsJsonObject();

            //prendo prima la stringa e poi l'int
            String type = jsonObject.get("name").getAsString();
            Integer amount = jsonObject.get("age").getAsInt();
            hashMap.put(type, amount);
        }
        return hashMap;
    }
}
