package jordan.quiz.quiz_generator.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizAttemptRepo extends MongoRepository<jordan.quiz.quiz_generator.models.QuizAttempt, Long> {


    public QuizAttemptRepo findQuizAttemptByQuizAttemptID(long id);
}
