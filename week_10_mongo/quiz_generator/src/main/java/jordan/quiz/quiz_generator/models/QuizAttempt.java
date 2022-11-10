package jordan.quiz.quiz_generator.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "QuizAttempt")
@Getter
@Setter
@NoArgsConstructor
public class QuizAttempt {

    @Transient
    public static final String SEQUENCE_NAME = "quiz_attempt_sequence";

    @Id
    private long quizAttemptID;

    private Quiz quiz;

    private String quizTaker;

    private int numCorrect;

    private int numIncorrect;

    private double score;

    public QuizAttempt(Quiz quiz, String quizTaker){

        this.quiz = quiz;
        this.quizTaker = quizTaker;
        this.numCorrect = 0;
        this.numIncorrect = 0;
        this.score = 0.0;




    }








}
