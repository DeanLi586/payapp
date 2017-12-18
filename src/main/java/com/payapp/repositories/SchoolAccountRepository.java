package com.payapp.repositories;

import com.payapp.models.SchoolAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by nexus on 12/17/17.
 */
public interface SchoolAccountRepository extends JpaRepository<SchoolAccount, Long> {
    Collection<SchoolAccount> findSchoolAccountsBySchool_Email(String email);
}
