package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Enhancement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnhancementRepository extends CrudRepository<Enhancement, Integer> {
}
