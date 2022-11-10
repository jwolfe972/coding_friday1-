package jordan.quiz.quiz_generator.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_sequences")
@Getter
@Setter
public class QuizSequence {


    @Id
    private String id;



    private long seq;

}
