import com.google.gson.stream.JsonWriter; 
import java.io.FileWriter; 
import java.io.IOException;

public class GsonStreamWriter {
    public static void main(String... args){
        //classe che permette di scrivere su json come stream
        JsonWriter writer;
        try { 
            //lo inizializzo col nome del file
            writer = new JsonWriter(new FileWriter("result.json"));

            //inizia l'oggetto con beginOnject
            writer.beginObject(); // {

            //aggiunge una coppia: name value
            writer.name("name").value("Steve"); // "name": "Steve"
            writer.name("surname").value("Jobs"); // "surname": "Job"
            writer.name("birthyear").value(1955); // "birthyear": 1955

            //aggiunge una coppia: name, array
            writer.name("skills"); // "skills":

            //l'array opera come se fosse un oggetto da iniziare e finire
            writer.beginArray(); // [

            //qua aggiungi solo i valori nell'array senza nomi
            writer.value("JAVA"); // "JAVA"
            writer.value("Python"); // "Python"
            writer.value("Rust"); // "Rust"

            //chiudi l'array
            writer.endArray(); // ]

            //chiudi l'oggetto
            writer.endObject(); // }

            //chiudi lo stream ed il file
            writer.close();
        } catch (IOException e) { 
            System.err.print(e.getMessage());
        }
    }
}
