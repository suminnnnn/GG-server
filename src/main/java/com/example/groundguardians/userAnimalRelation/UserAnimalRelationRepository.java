package com.example.groundguardians.userAnimalRelation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserAnimalRelationRepository extends JpaRepository<UserAnimalRelation, Long> {
    Set<UserAnimalRelation> findAllByUser_Id(long userId);
    Optional<UserAnimalRelation> findByUser_IdAndAnimal_Id(long userId, long animalId);
}
