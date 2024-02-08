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

            // JAR 파일이 위치하는 디렉토리 경로 가져오기
            String jarFilePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
            jarFilePath = URLDecoder.decode(jarFilePath, "UTF-8");
            File jarFile = new File(jarFilePath);
            String jarDirectory = jarFile.getParent();

            // 실제 파일 경로 구성
            String absoluteFilePath = jarDirectory + File.separator + filePath;

            File file = new File(absoluteFilePath);

            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + absoluteFilePath);
            }

            byte[] imageBytes = Files.readAllBytes(file.toPath());

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
