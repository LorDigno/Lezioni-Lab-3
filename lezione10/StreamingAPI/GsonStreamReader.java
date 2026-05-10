import com.google.gson.stream.JsonReader; 
import java.io.FileNotFoundException;
import java.io.FileReader; 
import java.io.IOException;

public class GsonStreamReader {
    public static void main(String... args){
        //classe per gestire il dile come stream
        JsonReader reader;

        try {
            //lo lego al file
            reader = new JsonReader(new FileReader("result.json"));

            //inizia l'oggetto
            reader.beginObject();

            //finchè ci sono token
            while (reader.hasNext()){
                //ottengo il nome della prossima proprietà
                String name = reader.nextName();

                //if else per decidere come  trattarla, le prime sono tutte stringhe
                if ("name".equals(name)){
                    System.out.println(reader.nextString());
                } else if ("surname".equals(name)){
                    System.out.println(reader.nextString());
                } else if ("birthyear".equals(name)){
                    System.out.println(reader.nextString());
                }else if ("skills".equals(name)){ 
                    //dobbiamo leggere l'array

                    //inizia l'array
                    reader.beginArray();

                    //scorro finché ci sono valori
                    while (reader.hasNext()){
                        System.out.println("\t" + reader.nextString());
                    }

                    //fine array
                    reader.endArray();
                }else {
                    //non so cosa sia, skip
                    reader.skipValue();
                }
            }

            //finsce l'oggetto
            reader.endObject();

            //chiudo il file e lo stream
            reader.close();

        } catch (FileNotFoundException e){ 
            System.err.print(e.getMessage());
        } catch (IOException e) { 
            System.err.print(e.getMessage());
        }
    }
}