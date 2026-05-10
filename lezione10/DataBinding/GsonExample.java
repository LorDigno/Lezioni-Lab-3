import com.google.gson.*;

//comando per compilare col gson
//javac -cp "lib/gson-2.11.0.jar" *.java

//per eseguire
//java -cp "lib/gson-2.11.0.jar:." GsonExample

public class GsonExample{ 
    public static void main(String[] args){ 
        Person p = new Person("Alice", 42, "A");

        //versione default
        //Gson gson = new Gson();

        //versione custom
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        //p.getclass() viene chiamato in automatico
        String json = gson.toJson(p);
        System.out.println(json);
    }
}