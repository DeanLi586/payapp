package com.payapp.repositories;

import com.payapp.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by nexus on 12/8/17.
 */
public interface ParentRepository extends JpaRepository<Parent,Long> {

    Parent findParentByEmail(String email);
}
