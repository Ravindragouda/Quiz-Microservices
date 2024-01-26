package com.example.quizservice.feign;

import com.example.quizservice.entity.QuestionWrapper;
import com.example.quizservice.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("questions/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions);

    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionids);


    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);



}
