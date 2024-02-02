package com.example.groundguardians.quiz;

import com.example.groundguardians.animal.Animal;
import com.example.groundguardians.animal.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    private final AnimalRepository animalRepository;

    public Quiz postQuiz(Long animal_id, QuizDto quizDto) {

        Optional<Animal> animal = animalRepository.findById(animal_id);

        Quiz quiz = quizDto.toEntity(animal.orElseThrow());

        return quizRepository.save(quiz);
    }

    public QuizResponseDto getQuiz(Long animal_id) {
        List<Quiz> quizList = quizRepository.findAllByAnimalId(animal_id);
        Optional<Animal> animal = animalRepository.findById(animal_id);

        Random random = new Random();
        int randomIndex = random.nextInt(quizList.size());
        Quiz quiz = quizList.get(randomIndex);

        return QuizResponseDto.fromEntity(quiz, animal.orElseThrow());
    }

}