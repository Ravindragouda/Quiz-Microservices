package com.example.quizservice.Controller;


import com.example.quizservice.Services.QuizService;
import com.example.quizservice.entity.QuestionWrapper;
import com.example.quizservice.entity.QuizDto;
import com.example.quizservice.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizservice;
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
    {


        return  quizservice.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getquestions(@PathVariable Integer id)
    {
        return quizservice.getQuizquestions(id);
    }
    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizservice.calculateResult(id, responses);
    }

}
