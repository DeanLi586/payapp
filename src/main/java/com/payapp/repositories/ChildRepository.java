package com.payapp.repositories;

import com.payapp.models.Child;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by nexus on 12/17/17.
 */
public interface ChildRepository extends JpaRepository<Child, Long> {
    Collection<Child> findChildrenByParentEmail(String parentEmail);
    Child findByLastNameAndParentLastName(String childLastName, String parentLastName);
    Child findByLastNameAndParentId(String childLastName, Long parentId);
    Collection<Child> findAllByParentId(Long id);
}
