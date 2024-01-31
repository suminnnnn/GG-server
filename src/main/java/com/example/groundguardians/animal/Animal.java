package com.example.groundguardians.animal;

import com.example.groundguardians.quiz.Quiz;
import com.example.groundguardians.userAnimalRelation.UserAnimalRelation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animal")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Animal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(nullable = false)
    private String card;

    @Column(nullable = false)
    private String result;

    @Column(nullable = false)
    private String url;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY)
    private final Set<UserAnimalRelation> userAnimals = new HashSet<>();

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY)
    private final Set<Quiz> quizzes = new HashSet<>();
}
