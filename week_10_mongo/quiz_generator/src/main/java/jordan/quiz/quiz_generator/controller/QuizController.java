package jordan.quiz.quiz_generator.controller;


import jordan.quiz.quiz_generator.models.Questions;
import jordan.quiz.quiz_generator.models.Quiz;
import jordan.quiz.quiz_generator.services.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
@RequestMapping("/quiz/custom-v2")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public ResponseEntity<?> getQuizPage(@RequestParam("quizNumber") long quizNumber){


        Quiz foundQuiz = quizService.findByID(quizNumber);


        if(foundQuiz != null){

            return new ResponseEntity<>(foundQuiz, HttpStatus.OK);
        }


    return new ResponseEntity<>("Quiz Not found", HttpStatus.BAD_REQUEST);




    }



    @PostMapping("/add/question")
    public ResponseEntity<?> addQuestionToQuiz(@RequestParam("quizNumber") long quizNumber, @RequestBody Questions questions){



        Quiz quiz = quizService.findByID(quizNumber);


        if(quiz != null){


            ArrayList<Questions> quizQuestions = new ArrayList<>(quiz.getQuestions());


            quizQuestions.add(questions);


            quiz.setNumQuestions(quiz.getNumQuestions()+1);

            quiz.setQuestions(quizQuestions);
            quiz.setAnswerKey(quiz.genAnswerKeyRT());

            Quiz updatedQ = quizService.addQuiz(quiz, true);




            return new ResponseEntity<>(updatedQ, HttpStatus.OK);




        }

        return new ResponseEntity<>("invalid request", HttpStatus.BAD_REQUEST);



    }
}
