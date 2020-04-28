package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Bug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepository extends CrudRepository<Bug, Integer> {
}
