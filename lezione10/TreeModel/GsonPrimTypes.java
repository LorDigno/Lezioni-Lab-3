import com.google.gson.*;

public class GsonPrimTypes {
    public static void main (String args[]){
        //da deserializzare
        String jsonString ="{\"name\":\"Mario Rossi\", \"age\":21,\"verified\":false,\"marks\":[30,28,25]}";

        //crea l’albero da JSON
        //server la classe apposita JsonParser
        //root
        JsonElement rootNode = JsonParser.parseString(jsonString);

        //ricava l'oggetto relativo al nodo root
        JsonObject details = rootNode.getAsJsonObject();

        //prende il campo "name" e lo stampa come se fosse una stringa
        JsonElement nameNode = details.get("name");
        System.out.println("Name: " +nameNode.getAsString());

        //come prima ma con int "age" 
        JsonElement ageNode = details.get("age");
        System.out.println("Age: " + ageNode.getAsInt());

        //bool "verified"
        JsonElement verifiedNode = details.get("verified");
        System.out.println("Verified: " + (verifiedNode.getAsBoolean() ? "Yes":"No"));

        //per ricavare l'array c'è il metodo apposito getAsJsonArray("nome_campo")
        JsonArray marks = details.getAsJsonArray("marks");

        //per la stampa si scorre fino a marks.size()
        for (int i = 0; i < marks.size(); i++){
            //lo prende specificatamente come un tipo primitivo
            JsonPrimitive value = marks.get(i).getAsJsonPrimitive();
            //stampa come se fosse un int
            System.out.print(value.getAsInt() + " "); 
        }
    }
}