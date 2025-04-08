package ma.artisanat.user_service.repository;


import ma.artisanat.user_service.entities.Role;
import ma.artisanat.user_service.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}