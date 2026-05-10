public class Person{ 
    private String name;
    private int age;

    //i campi transient non sono mantenuti nella serializzazione
    private transient String fiscalID;

    Person(String name, int age, String fiscalID){ 
        this.name = name;
        this.age = age;
        this.fiscalID=fiscalID;
    }
}
