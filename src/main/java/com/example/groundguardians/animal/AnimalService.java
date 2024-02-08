package com.example.groundguardians.animal;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
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
            String filePath = "images/" + animalRequestDto.getCard();

            // JAR 파일 내부의 리소스를 가져오기
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);

            // 파일이 존재하지 않으면 예외 발생
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            // InputStream에서 byte 배열로 변환
            byte[] imageBytes = inputStream.readAllBytes();

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
