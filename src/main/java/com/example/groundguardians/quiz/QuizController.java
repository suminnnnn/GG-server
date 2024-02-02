package com.example.groundguardians.quiz;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/{animal_id}")
    public ResponseEntity<?> postQuiz(@RequestParam("animal_id") Long animal_id,
                                      @RequestBody @Valid QuizDto quizDto) {

        Quiz quiz = quizService.postQuiz(animal_id, quizDto);

        if (quiz != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}