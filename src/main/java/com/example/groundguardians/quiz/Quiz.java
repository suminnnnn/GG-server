package com.example.groundguardians.quiz;

import com.example.groundguardians.animal.Animal;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quiz")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Quiz{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Builder
    public Quiz(String question, String answer, Animal animal) {
        this.question = question;
        this.answer = answer;
        this.animal = animal;
    }

}