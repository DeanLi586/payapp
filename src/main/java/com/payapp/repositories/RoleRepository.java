package com.payapp.repositories;

import com.payapp.shared.Role;
import com.payapp.shared.RoleType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by nexus on 12/17/17.
 */
public interface RoleRepository extends CrudRepository<Role,Long>{

    Role findByName(RoleType roleType);
}
