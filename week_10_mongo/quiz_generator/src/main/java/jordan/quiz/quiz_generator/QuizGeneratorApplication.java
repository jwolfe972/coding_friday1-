package jordan.quiz.quiz_generator;

import jordan.quiz.quiz_generator.enums.QuestionType;
import jordan.quiz.quiz_generator.models.Questions;
import jordan.quiz.quiz_generator.models.Quiz;
import jordan.quiz.quiz_generator.models.QuizAttempt;
import jordan.quiz.quiz_generator.jsonFileHandlers.ParseQuizJSON;
import jordan.quiz.quiz_generator.services.QuizAttemptService;
import jordan.quiz.quiz_generator.services.QuizService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class QuizGeneratorApplication implements CommandLineRunner {

    public QuizGeneratorApplication(QuizService quizService, QuizAttemptService quizAttempt, ParseQuizJSON parseQuizJSON) {
        this.quizService = quizService;
        this.quizAttempt = quizAttempt;
        this.parseQuizJSON = parseQuizJSON;
    }

    public static void main(String[] args) {
        SpringApplication.run(QuizGeneratorApplication.class, args);
    }



    private final QuizService quizService;
    private final QuizAttemptService quizAttempt;
    private final ParseQuizJSON parseQuizJSON;


    @Override
    public void run(String... args) throws Exception {

        // and Initialize an ArrayList with asList()
        ArrayList<String> gfg = new ArrayList<String>(
                Arrays.asList("9",
                        "11",
                        "7", "6", "4", "Non of the above"));






        ArrayList<Questions> sampleQs = new ArrayList<Questions>(


                Collections.singletonList(new Questions("What is 4 + 5", QuestionType.MULTIPLE_CHOICE, gfg, "9")));












        Quiz quiz = new Quiz("jordan wolfe", sampleQs, null);

        quizService.addQuiz(quiz);



        Quiz ex = parseQuizJSON.parseQuizToDB("src/main/resources/json_files/quiz_example.json");



    }
}
