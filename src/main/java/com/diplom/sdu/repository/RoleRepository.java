package com.diplom.sdu.repository;

import java.util.Optional;

import com.diplom.sdu.models.ERole;
import com.diplom.sdu.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);
}
