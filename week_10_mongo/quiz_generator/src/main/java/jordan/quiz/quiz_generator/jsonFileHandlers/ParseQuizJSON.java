package jordan.quiz.quiz_generator.jsonFileHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jordan.quiz.quiz_generator.models.Quiz;
import jordan.quiz.quiz_generator.services.QuizService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
@Service
public class ParseQuizJSON {

    private final QuizService quizService;

    public ParseQuizJSON(QuizService quizService) {
        this.quizService = quizService;
    }


    public Quiz parseQuizToDB(String fileName) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Quiz quiz = objectMapper.readValue(new File(fileName), Quiz.class);
        quiz.genAnswerKey();



        return quizService.addQuiz(quiz);

    }


}
