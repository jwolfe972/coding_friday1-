package jordan.quiz.quiz_generator.repos;

import jordan.quiz.quiz_generator.models.Quiz;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizRepo extends MongoRepository<Quiz, Long> {



    public Quiz findQuizByQuizId(long id);
}
