package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Enhancement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnhancementRepository extends CrudRepository<Enhancement, Integer> {

    @Query("select e from Enhancement e order by e.title")
    List<Enhancement> getAllEnhancementOrderedByTitle();

}
