import java.util.*;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder;

enum Degree_Type { TRIENNALE, MAGISTRALE}

public class Student {
    private String firstName;
    private String lastName;
    private int studentID;
    private String email;
    private List<String> courses;
    private Degree_Type Dg;

    public Student(String FName, String LName, int SID, String email,
        List<String> Clist, Degree_Type DG ){
        this.lastName=LName; this.lastName=LName; this.studentID=SID;
        this.email= email; this.courses=Clist; this.Dg=DG;
    }

    public String toString(){ 
        return "name:"+firstName+" surname:"+lastName+" ID:"+studentID+
            "email:"+email+" corsi:"+courses+" Degree:"+Dg;
    }

    public static void main (String args[]){
        List <String> ComputerScienceCourses = Arrays.asList("Reti", "Architetture");
        List <String> MathCourses = Arrays.asList("Analisi", "Statistica");

        // Instantiating students
        Student max = new Student("Mario", "Rossi", 1254, "mario.rossi@uni1.it",
            ComputerScienceCourses, Degree_Type.TRIENNALE);
        Student amy = new Student("Anna", "Bianchi", 1328, "anna.bainchi@uni1.it",
            MathCourses, Degree_Type.MAGISTRALE);

        // Instantiating Gson
        Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();

        // Converting JAVA to JSON
        String marioJson = gson.toJson(max);
        String annaJson = gson.toJson(amy);
        System.out.println(marioJson);
        System.out.println(annaJson);
    }
}