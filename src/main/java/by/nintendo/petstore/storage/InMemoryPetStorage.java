package by.nintendo.petstore.storage;

import by.nintendo.petstore.model.Pet;
import by.nintendo.petstore.model.PetStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InMemoryPetStorage {
    private final List<Pet> petList = new ArrayList<>();
    private final Map<PetStatus, List<Pet>> petStatusListMap = new HashMap<>();

    @Autowired
    public InMemoryPetStorage() {
    }

    public Pet findById(long id) {
        for (Pet pet : petList) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    public List<Pet> getAllPets() {
        return petList;
    }

    public void addPet(Pet pet) {
        petList.add(pet);
    }

    public Map<PetStatus, List<Pet>> getPetStatusListMap() {
        return petStatusListMap;
    }


}
