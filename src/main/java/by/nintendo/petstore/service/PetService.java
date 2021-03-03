package by.nintendo.petstore.service;

import by.nintendo.petstore.exception.PetNotFoundException;
import by.nintendo.petstore.model.*;
import by.nintendo.petstore.storage.InMemoryPetStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PetService {

    @Autowired
    InMemoryPetStorage petStorage;

    @Autowired
    public PetService() {
    }

    public Pet getPetById(long id) {
        for (Pet pet : petStorage.getAllPets()) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        throw new PetNotFoundException("Pet not found.");
    }

    public void deleatePet(long id) {
        if (getPetById(id) != null) {
            Pet pe = getPetById(id);
            petStorage.getAllPets().removeIf(x -> x.getId() == pe.getId());
        } else {
            throw new PetNotFoundException("Pet not found.");
        }

    }

    public void createPet(Pet pet) {
        petStorage.addPet(pet);
    }

    public List<Pet> allPet() {
        if (petStorage.getAllPets().size() != 0) {
            return petStorage.getAllPets();
        } else {
            throw new PetNotFoundException("No pets");
        }
    }

    public Map<PetStatus, List<Pet>> getAllPetsByStatus() {
        if (petStorage.getAllPets().size() != 0) {
            for (PetStatus pe : PetStatus.values()) {
                switch (pe) {
                    case AVAILABLE:
                        List<Pet> available = petStorage.getAllPets().stream().
                                filter(x -> x.getStatus() == PetStatus.AVAILABLE).
                                collect(Collectors.toList());
                        if (available.size() != 0) {
                            petStorage.getPetStatusListMap().put(PetStatus.AVAILABLE, available);
                        }

                        break;

                    case SOLD:
                        List<Pet> sold = petStorage.getAllPets().stream().
                                filter(x -> x.getStatus() == PetStatus.SOLD).
                                collect(Collectors.toList());
                        if (sold.size() != 0) {
                            petStorage.getPetStatusListMap().put(PetStatus.SOLD, sold);
                        }
                        break;

                    case PENDING:
                        List<Pet> pending = petStorage.getAllPets().stream().
                                filter(x -> x.getStatus() == PetStatus.PENDING).
                                collect(Collectors.toList());
                        if (pending.size() != 0) {
                            petStorage.getPetStatusListMap().put(PetStatus.PENDING, pending);
                        }
                        break;
                }
            }
            return petStorage.getPetStatusListMap();
        } else {
            throw new PetNotFoundException("No pets.");
        }


    }

    public void updateByForm(long petId, Pet pet) {
        if (getPetById(petId) != null) {
            for (Pet pe : petStorage.getAllPets()) {
                if (pe.getId() == petId) {
                    pe.setName(pet.getName());
                    pe.setStatus(pet.getStatus());
                    break;
                }
            }
        } else {
            throw new PetNotFoundException("Pet not found.");
        }

    }

    public void updatePet(Pet pet) {
        if (petStorage.findById(pet.getId()) != null) {
            for (Pet pe : petStorage.getAllPets()) {
                if (pe.getId() == pet.getId()) {
                    petStorage.getAllPets().set(petStorage.getAllPets().indexOf(pe), pet);
                }
            }
        } else {
            throw new PetNotFoundException("Pet not found");
        }
    }

    public void deletePetByOrder(long count, long id) {
        List<Pet> pets = petStorage.getAllPets().stream().
                limit(count).
                filter(x -> x.getId() == id).
                collect(Collectors.toList());

        for (Pet pet : pets) {
            pet.setStatus(PetStatus.SOLD);
        }
    }

}
