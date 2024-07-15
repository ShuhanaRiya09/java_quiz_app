import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UserLogin {

    public static void main(String[] args) throws IOException, ParseException {
            // Path to the users.json file
            String USERS_FILE_PATH = "src/main/resources/users.json";

            // Write users to the JSON file first (if it doesn't exist)
            writeInitialUsersToFile(USERS_FILE_PATH);

            JSONParser parser = new JSONParser();

            // Read users from the JSON file
            JSONArray users = (JSONArray) parser.parse(new FileReader(USERS_FILE_PATH));
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter your username: ");
            String userName = scanner.next();

            System.out.print("Enter your password: ");
            String userPassword = scanner.next();

            JSONObject loggedInUser = validateLogin(users, userName, userPassword);

            if (loggedInUser != null) {
                handleUserRole(loggedInUser);
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }

            scanner.close();

    }

    public static void handleUserRole(JSONObject user) throws IOException, ParseException {
        String role = (String) user.get("role");
        switch (role.toLowerCase()) {
            case "admin":
                System.out.println("Welcome admin! Please create new questions in the question bank.");
                QuizQuestionsEditor.addQuizQuestion();
                break;
            case "student":
                System.out.println("Welcome student! Please answer the quiz.");
                StudentQuizPlay.participateInQuiz();
                break;
            default:
                System.out.println("Unknown role: " + role);
        }
    }



    public static JSONObject validateLogin(JSONArray users, String username, String password) {
        for (Object obj : users) {
            JSONObject user = (JSONObject) obj;
            String userUsername = (String) user.get("username");
            String userPassword = (String) user.get("password");

            if (userUsername.equals(username) && userPassword.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static void writeInitialUsersToFile(String filePath) throws IOException {
        // Check if the file exists; if it does, skip writing initial users
        if (!new java.io.File(filePath).exists()) {
            JSONArray users = new JSONArray();

            // Create admin user
            JSONObject adminUser = new JSONObject();
            adminUser.put("username", "admin");
            adminUser.put("password", "1234");
            adminUser.put("role", "admin");

            // Create student user
            JSONObject studentUser = new JSONObject();
            studentUser.put("username", "salman");
            studentUser.put("password", "1234");
            studentUser.put("role", "student");

            // Add users to the JSON array
            users.add(adminUser);
            users.add(studentUser);

            // Write JSON file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(users.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        }
    }
}
