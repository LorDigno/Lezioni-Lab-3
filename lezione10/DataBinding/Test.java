import java.util.*;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException{ 
        Test t= new Test();
        Class myClass = t.getClass();
        System.out.println("Class represented by myClass: " + myClass.toString());
        System.out.println("Fields of myClass: "+
        Arrays.toString(myClass.getFields())); 
    }
}