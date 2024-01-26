package com.example.questionservice.Services;


import com.example.questionservice.entity.Question;
import com.example.questionservice.entity.QuestionWrapper;
import com.example.questionservice.entity.Response;
import com.example.questionservice.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {

    @Autowired
    QuestionRepo Qrepo;
    public ResponseEntity<List<Question>> getAllQuestions() {

        try {
            return new ResponseEntity<>(Qrepo.findAll(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<List<Question>> getAllQuestionsByCategory(String category){
        try
        {
            return new ResponseEntity<>( Qrepo.getAllQuestionsByCategory(category),HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> AddQuestion(Question question) {
        try {
            Qrepo.save(question);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> UpdateQuestion(Question question) {
        try
        {
            Qrepo.save(question);
            return new ResponseEntity<>("Updated",HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> DeleteByCategory(Integer id) {
        try {

                Qrepo.deleteById(id);
                return new ResponseEntity<>(" Question is Deleted",HttpStatus.ACCEPTED);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Failed to Delete the question",HttpStatus.BAD_REQUEST);

    }



    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionids) {
        List<QuestionWrapper> wrappers=new ArrayList<>();
        List<Question> questions=new ArrayList<>();

        for (Integer id:questionids)
        {
            questions.add(Qrepo.findById(id).get());
        }
        for (Question question:questions)
        {
            QuestionWrapper Wrapper=new QuestionWrapper();
            Wrapper.setId(question.getId());
            Wrapper.setQuestionTitle(question.getQuestionTitle());
            Wrapper.setOption1(question.getOption1());
            Wrapper.setOption2(question.getOption2());
            Wrapper.setOption3(question.getOption3());
            Wrapper.setOption4(question.getOption4());
            wrappers.add(Wrapper);

        }

        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {


        int right = 0;


        for(Response response : responses){
            Question question=Qrepo.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer()))
                right++;


        }
        return new ResponseEntity<>(right, HttpStatus.OK);

    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions=Qrepo.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }
}
