package com.example.groundguardians.quiz;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "퀴즈 API")
@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @PostMapping("/{animal_id}")
    public ResponseEntity<?> postQuiz(@PathVariable("animal_id") Long animal_id,
                                      @RequestBody @Valid QuizDto quizDto) {

        Quiz quiz = quizService.postQuiz(animal_id, quizDto);

        if (quiz != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{animal_id}")
    public ResponseEntity<?> getQuiz(@PathVariable("animal_id") Long animal_id) {

        return ResponseEntity.ok().body(quizService.getQuiz(animal_id));
    }
}