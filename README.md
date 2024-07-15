# Project Title: Java Quiz Application with JSON Integration

## Project Summary
This project is a simple Quiz System that incorporates JSON for straightforward data representation and advanced manipulation techniques, facilitating efficient storage, retrieval, and management of quiz content. It allows an admin to add multiple-choice questions (MCQs) to a quiz bank and a student to take a quiz with random questions from the quiz bank. The system supports user authentication for both admin and student roles, enabling admins to manage the quiz content and students to participate in the quizzes developing a user-friendly interface that integrates seamlessly with JSON data for a smoother user experience.

## Prerequisites
- Java (Latest version)
- IDE of your choice (e.g., IntelliJ IDEA, Eclipse)

## How to Run
1. Clone the repository.
2. Open the project in your preferred IDE.
3. Run the `UserLogin` class.

## Project Structure
- `src/main/java`
  - `UserLogin` - Main class to run the application.
  - `QuizQuestionsEditor` - Class representing the functionality for adding multiple-choice questions and managing the quiz bank.
  - `StudentQuizPlay` - Class for quiz play as a student.
- `src/resources`
  - `users.json` - File containing user information.
  - `quiz.json` - File containing quiz questions.

## Usage
### For Admin User
1. Enter your username and password.
2. If authenticated as an admin, the system prompts you to add new questions to the quiz bank.
3. Continue adding questions until you press 'q' to quit.

### For Student User
1. Enter your username and password.
2. If authenticated as a student, the system prompts you to start the quiz.
3. Answer 10 random questions from the quiz bank.
4. Receive feedback based on your score.


## Video Demonstration
Demonstrating the execution of the project for both admin and student.
### Admin roleplay in QuizApp


https://github.com/user-attachments/assets/4e1414b8-cbce-4d43-80fa-e05c20a87df3



### Student roleplay in QuizApp


https://github.com/user-attachments/assets/15dfcd70-1a03-463c-be6b-2e081c6a523d




## Conclusion
The Quiz System allows for efficient management and participation in quizzes. Admins can easily add questions to a centralized quiz bank, and students can take randomized quizzes, providing a robust platform for both quiz administration and participation.
