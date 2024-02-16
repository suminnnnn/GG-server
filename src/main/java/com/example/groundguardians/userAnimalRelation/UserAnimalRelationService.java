package com.example.groundguardians.userAnimalRelation;

import com.example.groundguardians.animal.Animal;
import com.example.groundguardians.animal.AnimalRepository;
import com.example.groundguardians.user.User;
import com.example.groundguardians.user.UserDto;
import com.example.groundguardians.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserAnimalRelationService {

    private final UserAnimalRelationRepository userAnimalRelationRepository;
    private final UserRepository userRepository;
    private final AnimalRepository animalRepository;

    public UserDto getMyPageInfo(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .cardImage(getMyAnimalCard(userId))
                .build();
    }
    public byte[] addAnimalCardToUser(long userId, long animalId) {

        Optional<UserAnimalRelation> userAnimalRelation = userAnimalRelationRepository
                .findByUser_IdAndAnimal_Id(userId, animalId);

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Animal animal = animalRepository.findById(animalId).orElseThrow(() -> new RuntimeException("Animal not found"));

        if(userAnimalRelation.isEmpty()){
            userAnimalRelationRepository.save(UserAnimalRelation.builder().user(user).animal(animal).build());
        }

        return animal.getCard();
    }

    public List<byte[]> getMyAnimalCard(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<byte[]> cardList = new ArrayList<>();

        Set<UserAnimalRelation> userAnimalRelationSet = userAnimalRelationRepository.findAllByUser_Id(userId);

        if(!userAnimalRelationSet.isEmpty()){
            for(UserAnimalRelation relation: userAnimalRelationSet){
                cardList.add(relation.getAnimal().getCard());
            }
        }

        return cardList;
    }
}
