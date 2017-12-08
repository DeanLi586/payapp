package com.payapp.repositories;

import com.payapp.models.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by nexus on 12/8/17.
 */
public interface ParentRepository extends JpaRepository<Parent,String> {

    Parent findParentByParentEmail(String parentEmail);
}
