package com.example.groundguardians.quiz;

import com.example.groundguardians.animal.Animal;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuizResponseDto {

    private String question;

    private String answer;

    private String url;


    public static QuizResponseDto fromEntity (Quiz quiz, Animal animal) {
        return QuizResponseDto.builder()
                .question(quiz.getQuestion())
                .answer(quiz.getAnswer())
                .url(animal.getUrl())
                .build();
    }
}