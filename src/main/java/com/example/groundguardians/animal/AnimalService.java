package com.example.groundguardians.animal;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;

    public Animal postAnimalCard(AnimalRequestDto animalRequestDto) {

        try {
            String filePath = "src/main/resources/images/" + animalRequestDto.getCard();
            Resource resource = new ClassPathResource(filePath);
            byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

            AnimalDto animalDto = new AnimalDto();

            animalDto.setCard(imageBytes);
            animalDto.setName(animalRequestDto.getName());
            animalDto.setResult(animalRequestDto.getResult());
            animalDto.setUrl(animalRequestDto.getUrl());

            Animal animal = animalDto.toEntity(animalDto);

            return animalRepository.save(animal);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public AnimalResponseDto getAnimalName(String result) {

        Animal animal = animalRepository.findByResult(result);

        return AnimalResponseDto.fromEntity(animal);
    }
}
