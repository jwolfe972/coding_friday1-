package jordan.quiz.quiz_generator.models;


import jordan.quiz.quiz_generator.enums.QuestionType;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Questions {


    private String question;

    private QuestionType questionType;

    private ArrayList<String> answerChoices;

    private String correctAnswer;






}
