package net.toastynetworks.MCLRestAPI.Repository;

import net.toastynetworks.MCLRestAPI.Models.Modpack;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModpackRepository extends CrudRepository<Modpack, Integer> {
    List<Modpack> findByModpackVersionType(String modpackVersionType);
}
