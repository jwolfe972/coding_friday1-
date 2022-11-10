package jordan.quiz.quiz_generator.services;

import jordan.quiz.quiz_generator.models.QuizAttempt;
import jordan.quiz.quiz_generator.repos.QuizAttemptRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizAttemptService {


    private final QuizAttemptRepo quizAttemptRepo;
    private final NextSequenceService nextSequenceService;
    public QuizAttemptService(QuizAttemptRepo quizAttemptRepo, NextSequenceService nextSequenceService) {
        this.quizAttemptRepo = quizAttemptRepo;
        this.nextSequenceService = nextSequenceService;
    }


    public QuizAttempt addAttempt(QuizAttempt qA){

        qA.setQuizAttemptID(nextSequenceService.getNextSequence("quiz_attempt_sequence"));
        return this.quizAttemptRepo.save(qA);
    }


    public List<QuizAttempt> getAllQuizAttempts(){

        return this.quizAttemptRepo.findAll();
    }
}
