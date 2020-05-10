package com.ynz.jpa.repositories;

import com.ynz.jpa.entities.Application;
import com.ynz.jpa.entities.Enhancement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends CrudRepository<Application, Integer> {

    //JPQL query with dynamic parameters.
    @Query("select a.enhancements from Application a where a.id = :applicationId")
    List<Enhancement> getEnhancementsWithApps(@Param("applicationId") int applicationId);

}
