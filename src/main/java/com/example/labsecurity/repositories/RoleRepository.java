package com.example.labsecurity.repositories;

import com.example.labsecurity.models.ERole;
import com.example.labsecurity.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
     Optional<Role> findByName(ERole name);
}
