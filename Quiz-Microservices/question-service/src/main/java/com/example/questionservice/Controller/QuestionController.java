package com.example.questionservice.Controller;


import com.example.questionservice.Services.QuestionService;
import com.example.questionservice.entity.Question;
import com.example.questionservice.entity.QuestionWrapper;
import com.example.questionservice.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    Environment environment;
    @GetMapping("/allquestions")
    public ResponseEntity<List <Question> > getAllQuestions(){

       return (ResponseEntity<List<Question>>) questionService.getAllQuestions();

    }

    @GetMapping("category/{category}")
    public ResponseEntity<List <Question>>  getAllQuestionsByCategory(@PathVariable String category){

        return questionService.getAllQuestionsByCategory(category);

    }

    @PostMapping("/add")
    public ResponseEntity<String> AddQuestion(@RequestBody Question question)
    {
        return questionService.AddQuestion(question);
    }
    @PutMapping("/update")

    public ResponseEntity<String> update(@RequestBody Question question)
    {
        return questionService.UpdateQuestion(question);
    }

   @DeleteMapping("/delete/{id}")

       public ResponseEntity<String> DeleteByid(@PathVariable Integer id)
       {
           return questionService.DeleteByCategory(id);
       }
       @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam Integer numQuestions)
       {
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
       }

       @PostMapping("getQuestions")
        public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionids)
       {
           System.out.println(environment.getProperty("local.server.port"));
           return questionService.getQuestionsFromId(questionids);
       }

       @PostMapping("getScore")
        public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
       {
           return questionService.getScore(responses);
       }



}
