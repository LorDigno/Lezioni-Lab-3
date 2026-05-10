import com.google.gson.Gson;

public class GsonFromJson {
    class User {
        private final String firstName;
        private final String lastName;

        public User(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName; 
        }

        public String toString() {
            return new StringBuilder().append("User{").append("First name: ")
                    .append(firstName).append(", Last name: ")
                    .append(lastName).append("}").toString(); 
        }
    }

    public static void main(String[] args) {
        String json_string = "{\"firstName\":\"Laura\", \"lastName\": \"Ricci\"}";
        Gson gson = new Gson();
        User user = gson.fromJson(json_string, User.class);
        System.out.println(user);
    } 
}