import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class FailSafeItr {
    public static void main(String[] args){
        //hashMap ha iteratore weakly consistant
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();
        map.put("ONE", 1);
        map.put("TWO", 2);
        map.put("THREE", 3);
        map.put("FOUR", 4);

        //ottengo l'iteratore delle chiavi
        Iterator <String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = (String)it.next();
            System.out.println(key + " : " + map.get(key));
            
            //nonostante la modifica l'iteratore rimane valido e potrebbe stampare 7
            map.put("SEVEN", 7); 
        }
    }
}

// the program prints ONE : 1 FOUR : 4 TWO : 2 THREE : 3 SEVEN : 7