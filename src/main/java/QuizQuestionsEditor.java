import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class QuizQuestionsEditor {

    public static void main(String[] args) throws IOException, ParseException {
        addQuizQuestion();
    }

    public static void addQuizQuestion() throws IOException, ParseException {
        String filePath = "src/main/resources/quiz.json";
        JSONParser jsonParser = new JSONParser();
        JSONArray questionsArray;

        File file = new File(filePath);
        if (file.exists() && !file.isDirectory()) {
            questionsArray = (JSONArray) jsonParser.parse(new FileReader(filePath));
        } else {
            questionsArray = new JSONArray();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome Admin! You can add multiple SQA-related MCQs.");
        System.out.println("Press 'q' and Enter to quit adding questions.");

        while (true) {
            System.out.println("Input your question:");
            String question = scanner.nextLine().trim();

            if (question.equalsIgnoreCase("q")) {
                break;
            }

            JSONObject questionObj = new JSONObject();
            questionObj.put("question", question);

            for (int i = 1; i <= 4; i++) {
                System.out.println("Input option " + i + ":");
                String option = scanner.nextLine().trim();
                questionObj.put("option " + i, option);
            }

            System.out.println("What is the answer key? (Enter option number 1-4)");
            int answerKey = Integer.parseInt(scanner.nextLine().trim());
            questionObj.put("answerkey", answerKey);

            questionsArray.add(questionObj);
            System.out.println("Question saved successfully!");

            System.out.println("Do you want to add another question? (press any key to continue, or 'q' to quit)");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equalsIgnoreCase("q")) {
                break;
            }
        }

        scanner.close();
        writeQuestionsToFile(questionsArray, filePath);
    }

    public static void writeQuestionsToFile(JSONArray questionsArray, String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(questionsArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
        System.out.println("Questions saved to quiz.json successfully!");
    }
}
