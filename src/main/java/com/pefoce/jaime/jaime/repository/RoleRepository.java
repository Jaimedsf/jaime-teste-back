package com.pefoce.jaime.jaime.repository;


import com.pefoce.jaime.jaime.models.ERole;
import com.pefoce.jaime.jaime.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
