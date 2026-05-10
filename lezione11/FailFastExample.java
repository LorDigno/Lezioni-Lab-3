import java.util.HashMap; 
import java.util.Iterator;
import java.util.Map; 

public class FailFastExample {
    public static void main(String[] args){ 
        Map<String, String> cityCode = new HashMap<String, String>();
        cityCode.put("Delhi", "India");
        cityCode.put("Moscow", "Russia");
        cityCode.put("New York", "USA");

        //prendo un iteratore
        Iterator iterator = cityCode.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(cityCode.get(iterator.next()));

            //dopo la modifica l'iteratore non è più valido
            cityCode.put("Istanbul", "Turkey"); 
        }
    }
}