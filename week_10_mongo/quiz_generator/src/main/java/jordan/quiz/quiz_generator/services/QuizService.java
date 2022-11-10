package jordan.quiz.quiz_generator.services;


import jordan.quiz.quiz_generator.models.Quiz;
import jordan.quiz.quiz_generator.repos.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {


    private final QuizRepo quizRepo;
    private final NextSequenceService nextSequenceService;

    @Autowired
    public QuizService(QuizRepo quizRepo, NextSequenceService nextSequenceService) {
        this.quizRepo = quizRepo;
        this.nextSequenceService = nextSequenceService;
    }



    public Quiz addQuiz(Quiz q){
        q.setQuizId(nextSequenceService.getNextSequence("quiz_sequence"));
        return quizRepo.save(q);
    }

    public Quiz addQuiz(Quiz q, boolean go){

        return quizRepo.save(q);
    }

    public List<Quiz> getAllQuizzes(){


        return quizRepo.findAll();

    }

    public Quiz findByID(long id){


        return quizRepo.findQuizByQuizId(id);
    }



}
