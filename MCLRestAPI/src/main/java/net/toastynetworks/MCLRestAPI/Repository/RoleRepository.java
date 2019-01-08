package net.toastynetworks.MCLRestAPI.Repository;

import net.toastynetworks.MCLRestAPI.Models.Role;
import net.toastynetworks.MCLRestAPI.Models.RoleName;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
