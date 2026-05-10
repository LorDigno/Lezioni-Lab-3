import com.google.gson.*; 
import java.io.*; 
import java.util.*;

public class GSONComplexObj{
    public static void main(String[] args) {
        //leggo la stringa json come un file
        File input = new File("restaurant.json");
        try {
            //parso l'intero file ottenuto dal reader in albero
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));

            //ottendo il file come oggeto json
            JsonObject fileObject = fileElement.getAsJsonObject();

            //extracting basic fields
            //name come stringa
            String identifier = fileObject.get("name").getAsString();
            System.out.println("name is="+identifier);

            //menu come array
            JsonArray jsonArrayOfItems =fileObject.get("menu").getAsJsonArray();

            //creo una list<T> da riempire durante il parsing
            List <RestaurantMenuItem> menuitems = new ArrayList<RestaurantMenuItem>();

            //scorro la lista json, all'inizio sono tutti elements
            for (JsonElement menuElement: jsonArrayOfItems){
                //Get the JsonObject
                JsonObject itemJsonObject = menuElement.getAsJsonObject();

                //attributi del menuItem
                String desc= itemJsonObject.get("description").getAsString();
                float price = itemJsonObject.get("price").getAsFloat();

                //creo l'oggetto java ora che ho parsato tutto
                RestaurantMenuItem restaurantel = new RestaurantMenuItem(desc, price);

                //aggiungo alla mia lista
                menuitems.add(restaurantel);
            }

            //stampa
            System.out.println("Items are"+menuitems);
            
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}