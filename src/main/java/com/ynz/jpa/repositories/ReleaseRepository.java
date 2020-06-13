package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Release;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends CrudRepository<Release, Integer> {
}
