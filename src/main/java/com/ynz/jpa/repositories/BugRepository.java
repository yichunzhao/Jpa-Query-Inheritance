package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Bug;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends CrudRepository<Bug, Integer> {

    @Query("select b from Bug b where b.severity = 1")
    List<Bug> findSevereBugs();

}
