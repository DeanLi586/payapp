package com.payapp.repositories;

import com.payapp.models.ParentAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by nexus on 12/17/17.
 */
public interface ParentAccountRepository extends JpaRepository<ParentAccount, Long> {
    Collection<ParentAccount> findAllByParentId(Long Id);
    ParentAccount findByAccountId(Long Id);
}
