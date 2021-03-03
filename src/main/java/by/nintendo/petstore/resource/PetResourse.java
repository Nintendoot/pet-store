package by.nintendo.petstore.resource;

import by.nintendo.petstore.model.Pet;
import by.nintendo.petstore.model.PetStatus;
import by.nintendo.petstore.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/pet")
public class PetResourse {
    @Autowired
    private PetService petService;

    @PostMapping
    public ResponseEntity<?> createPet(@Valid @RequestBody Pet pet) {
        petService.createPet(pet);
        return new ResponseEntity<>("Pet create", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Object> updatepet(@Valid @RequestBody Pet pet) {
        petService.updatePet(pet);
        return new ResponseEntity<>("Pet update.", HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Pet>> getAll() {
        List<Pet> pets = petService.allPet();
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @GetMapping(path = "/{petId}")
    public ResponseEntity<Pet> getById(@PathVariable("petId") long petId) {
        Pet pet = petService.getPetById(petId);
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PostMapping(path = "/{petId}")
    public ResponseEntity<?> updateByIdForm(@PathVariable("petId") long petId, @Valid @RequestBody Pet pet) {
        petService.updateByForm(petId, pet);
        return new ResponseEntity<>("Pet update", HttpStatus.OK);
    }

    @DeleteMapping(path = "/{petId}")
    public ResponseEntity<?> deleatePetById(@PathVariable("petId") long petId) {
        petService.deleatePet(petId);
        return new ResponseEntity<>("Pet delete.", HttpStatus.OK);
    }

    @GetMapping(path = "/findByStatus")
    public ResponseEntity<Map<PetStatus, List<Pet>>> statusPets() {
        Map<PetStatus, List<Pet>> patsStatus = petService.getAllPetsByStatus();
        return new ResponseEntity<>(patsStatus, HttpStatus.OK);
    }
}
