package com.jackbourner.reactthymeleaf.repository;

import com.jackbourner.reactthymeleaf.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}
