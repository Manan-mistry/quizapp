package com.example.quizapp.controller;

import com.example.quizapp.DAO.MasterQuizDAO;
import com.example.quizapp.DAO.QuizDAO;
import com.example.quizapp.model.MasterQuiz;
import com.example.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ApiController {

    @Autowired
    MasterQuizDAO masterQuizDAO;

    @Autowired
    QuizDAO quizDAO;

    @GetMapping(path = "/quizzes")
    ResponseEntity<?> getQuizzes(){
        List<MasterQuiz> quizzes = masterQuizDAO.findAll();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping(path = "/quizzes/{id}")
    ResponseEntity<?> getQuizById(@PathVariable long id) {
        Optional<MasterQuiz> quiz= masterQuizDAO.findById(id);

        if(quiz.isEmpty())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(quiz.get());
    }

    @PostMapping(path = "/quizzes")
    ResponseEntity<?> addQuiz(@RequestBody @Valid MasterQuiz quiz){
        Long id =quiz.getId();
        if(id!=null){
            Optional<MasterQuiz> existinfQuiz = masterQuizDAO.findById(id);
            if(existinfQuiz.isPresent()) return ResponseEntity.badRequest().build();
        }

        List<Quiz> newQuizzes = quiz.getQuizzes();

        for(Quiz quiz1 : newQuizzes){
            quiz1.setId(null);
        }
        List<Quiz> savedQuizzes = quizDAO.saveAllAndFlush(newQuizzes);

        quiz.setQuizzes(savedQuizzes);

        MasterQuiz saved = masterQuizDAO.save(quiz);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping(path = "/quizzes/{id}")
    ResponseEntity<?> verifyAnswer(@PathVariable long id , @RequestParam List<Integer> answer){
        Optional<MasterQuiz> quiz = masterQuizDAO.findById(id);

        if(answer == null || quiz.isEmpty() || answer.size() != quiz.get().getQuizzes().size())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        Map<String, Integer> result = new HashMap<>();

        int passed = 0;

        List<Quiz> quizRecord = quiz.get().getQuizzes();

        int i = 0;
        while (i< quizRecord.size()){
            if(quizRecord.get(i).getAnswer() == answer.get(i))passed++;
            i++;
        }

        result.put("result",passed);
        result.put("total",quizRecord.size());

        return ResponseEntity.ok(result);
    }
}
