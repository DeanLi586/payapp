package com.payapp.repositories;

import com.payapp.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nexus on 12/17/17.
 */
public interface SchoolRepository extends JpaRepository<School, Long>{
    School findByEmail(String email);
    School findByName(String name);
}
