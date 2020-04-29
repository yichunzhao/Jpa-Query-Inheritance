package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Application;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
}
