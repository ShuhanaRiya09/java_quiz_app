import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class StudentQuizPlay {

    public static int NUM_QUESTIONS = 10;

    public static void main(String[] args) throws IOException, ParseException {
        participateInQuiz();
    }

    public static void participateInQuiz() throws IOException, ParseException {

        String FILE_NAME = "src/main/resources/quiz.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray quizArray = (JSONArray) jsonParser.parse(new FileReader(FILE_NAME));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the quiz!");

        while (true) {
            System.out.println("Press 's' to start or 'q' to quit.");
            char choice = scanner.next().charAt(0);

            if (choice == 'q') {
                break;
            } else if (choice != 's') {
                System.out.println("Invalid input. Please enter 's' or 'q'.");
                continue;
            }

            List<JSONObject> questions = getRandomQuestions(quizArray);
            int totalScore = conductQuiz(scanner, questions);
            displayResult(totalScore);
        }

        System.out.println("Thank you for playing!");
        scanner.close();
    }


    public static List<JSONObject> getRandomQuestions(JSONArray quizArray) {


        Random random = new Random();
        List<JSONObject> randomQuestions = new ArrayList<>();

        int totalQuestions = quizArray.size();
        List<Integer> chosenIndexes = new ArrayList<>();

        for (int i = 0; i < NUM_QUESTIONS; i++) {
            int questionIndex;
            do {
                questionIndex = random.nextInt(totalQuestions);
            } while (chosenIndexes.contains(questionIndex));

            chosenIndexes.add(questionIndex);
            JSONObject questionSelected = (JSONObject) quizArray.get(questionIndex);
            randomQuestions.add(questionSelected);
        }

        return randomQuestions;
    }

    public static int conductQuiz(Scanner scanner, List<JSONObject> questions) {
        int totalScore = 0;

        for (int i = 0; i < questions.size(); i++) {
            JSONObject questionObj = questions.get(i);

            String question = (String) questionObj.get("question");
            String option1 = (String) questionObj.get("option 1");
            String option2 = (String) questionObj.get("option 2");
            String option3 = (String) questionObj.get("option 3");
            String option4 = (String) questionObj.get("option 4");
            int answerKey = ((Long) questionObj.get("answerkey")).intValue();

            List<String> options = Arrays.asList(option1, option2, option3, option4);

            System.out.println("\n[Question " + (i + 1) + "] " + question);
            for (int j = 0; j < options.size(); j++) {
                System.out.println((j + 1) + ". " + options.get(j));
            }

            int userAnswer = readUserAnswer(scanner, options.size());
            if (userAnswer == -1) {
                System.out.println("Invalid input. Skipping this question.");
            } else if (userAnswer == answerKey) {
                totalScore++;
            }
        }

        return totalScore;
    }

    public static int readUserAnswer(Scanner scanner, int numOptions) {
        System.out.print("Enter your answer (1-" + numOptions + "): ");
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
        }
        int answer = scanner.nextInt();
        if (answer < 1 || answer > numOptions) {
            return -1;
        }
        return answer;
    }

    public static void displayResult(int totalScore) {
        System.out.println("\nQuiz completed! Your score is: " + totalScore + " out of " + NUM_QUESTIONS);

        if (totalScore >= 8) {
            System.out.println("Excellent! You have got " + totalScore + " out of " + NUM_QUESTIONS);
        } else if (totalScore >= 5) {
            System.out.println("Good. You have got " + totalScore + " out of " + NUM_QUESTIONS);
        } else if (totalScore >= 2) {
            System.out.println("Very poor! You have got " + totalScore + " out of " + NUM_QUESTIONS);
        } else {
            System.out.println("Very sorry, you have failed. You have got " + totalScore + " out of " + NUM_QUESTIONS);
        }
    }
}
