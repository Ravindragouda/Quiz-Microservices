package com.example.quizservice.Services;


import com.example.quizservice.entity.QuestionWrapper;
import com.example.quizservice.entity.Quiz;
import com.example.quizservice.entity.Response;
import com.example.quizservice.feign.QuizInterface;
import com.example.quizservice.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizRepo quizRepo;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions=quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        Quiz quiz=new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("success",HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizquestions(Integer id) {
        Quiz quiz=quizRepo.findById(id).get();
        List<Integer> questionIds=quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions =quizInterface.getQuestionsFromId(questionIds);


        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score=quizInterface.getScore(responses);
        return score;
    }
}
