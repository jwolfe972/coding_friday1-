package jordan.quiz.quiz_generator.models;

import jordan.quiz.quiz_generator.enums.QUIZ_TOPICS;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "Quiz")
@Getter
@Setter
@NoArgsConstructor
public class Quiz {


    @Transient
    public static final String SEQUENCE_NAME = "quiz_sequence";



    @Id
    private long quizId;


    private String creatorName;


    private int numQuestions;

    private ArrayList<String> quiz_topics;


    private ArrayList<Questions> questions;


    private String shareLink;


    private Map<String, String> answerKey;


    public Quiz(String creatorName, ArrayList<Questions> questions, ArrayList<String> topics ){

        this.creatorName = creatorName;
        this.questions = questions;
        this.quiz_topics = topics;
        this.numQuestions = questions.size();
        this.shareLink = " ";
        genAnswerKey();



    }

    public void genAnswerKey(){

        Map<String, String> answerKey = new HashMap<>();
        for(Questions q: this.questions){
            answerKey.put(q.getQuestion(), q.getCorrectAnswer());





        }




        this.answerKey = answerKey;


    }

    public Map<String, String> genAnswerKeyRT(){


        Map<String, String> answerKey = new HashMap<>();
        for(Questions q: this.questions){
            answerKey.put(q.getQuestion(), q.getCorrectAnswer());





        }




        return answerKey;
    }


    @Override
    public String toString() {
        return "Quiz{" +
                "quizId=" + quizId +
                ", creatorName='" + creatorName + '\'' +
                ", numQuestions=" + numQuestions +
                ", quiz_topics=" + quiz_topics +
                ", questions=" + questions +
                ", shareLink='" + shareLink + '\'' +
                ", answerKey=" + answerKey +
                '}';
    }
}
