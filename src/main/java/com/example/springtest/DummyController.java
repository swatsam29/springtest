package com.example.springtest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyController {

    private final DummyRepository repository;

    public DummyController(DummyRepository repository) {
        this.repository = repository;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<DummyEntity> create(@RequestBody DummyDTO inputValue) {
        DummyEntity existingEntity = repository.findEntryByName(inputValue.getName());
        if (existingEntity != null) {
            return new ResponseEntity<>(existingEntity, HttpStatus.CONFLICT);
        }
        System.out.println("The incoming value = " + inputValue);
        DummyEntity entity = new DummyEntity();
        entity.setId(null);
        entity.setDescription(inputValue.getDescription());
        entity.setName(inputValue.getName());

        System.out.println("The entity = " + entity);
        entity = repository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<DummyEntity> update(@RequestBody DummyDTO inputValue) {
        Optional<DummyEntity> existingEntity = repository.findById(inputValue.getId());
        if (!existingEntity.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        DummyEntity entity = existingEntity.get();
        if (entity.getName().equalsIgnoreCase(inputValue.getName())) {
              return new ResponseEntity<>(entity, HttpStatus.CONFLICT);
        }
        
        entity.setDescription(inputValue.getDescription());
        entity.setName(inputValue.getName());

        entity = repository.save(entity);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DummyEntity> deleteOneEntry(@NonNull @PathVariable Integer id) {
        Optional<DummyEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            DummyEntity entityToDelete = entity.get();
            repository.delete(entityToDelete);
            return new ResponseEntity<>(entityToDelete, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DummyEntity> getOneEntry(@NonNull @PathVariable Integer id) {
        Optional<DummyEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            return new ResponseEntity<>(entity.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DummyEntity>> getAllEntries() {
        List<DummyEntity> data = repository.retrieveAllEntries();
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<DummyEntity>>(data, HttpStatus.OK);
    }

    @GetMapping("/search/{wild-card}")
    public ResponseEntity<List<DummyEntity>> searchWildcardEntities(
            @NonNull @PathVariable("wild-card") String wildcard) {
        List<DummyEntity> data = repository.retrieveWildCardNames(
                String.format("%%%s%%", wildcard));
        if (data == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<DummyEntity>>(data, HttpStatus.OK);
    }

}
