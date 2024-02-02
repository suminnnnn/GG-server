package com.example.groundguardians.quiz;

import com.example.groundguardians.animal.Animal;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizDto {

    private String question;

    private String answer;

    public Quiz toEntity(Animal animal) {
        return Quiz.builder()
                .question(this.question)
                .answer(this.answer)
                .animal(animal)
                .build();
    }
}