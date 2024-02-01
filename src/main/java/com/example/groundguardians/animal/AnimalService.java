package com.example.groundguardians.animal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public Animal postAnimalCard(AnimalDto animalDto) {

        Animal animal = animalDto.toEntity(animalDto);

        return animalRepository.save(animal);
    }

    public AnimalResponseDto getAnimalName(String result) {
        Animal animal = animalRepository.findByResult(result);

        return AnimalResponseDto.fromEntity(animal);
    }
}
