package com.example.springtest;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface DummyRepository extends CrudRepository<DummyEntity, Integer> {

    @Query("select d from DummyEntity d where d.name = :name")
    public DummyEntity findEntryByName(@RequestParam String name);

    @Query("select d from DummyEntity d")
    public List<DummyEntity> retrieveAllEntries();

    @Query("select d from DummyEntity d where d.name like :wildcard or d.description like :wildcard ")
    public List<DummyEntity> retrieveWildCardNames(@RequestParam String wildcard);
}
