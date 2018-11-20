package net.toastynetworks.MCLRestAPI.Service;

import net.toastynetworks.MCLRestAPI.Models.Modpack;
import net.toastynetworks.MCLRestAPI.Repository.ModpackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModpackService {

    @Autowired
    private ModpackRepository modpackRepository;

    public List<Modpack> getAllModpacks() {
        List<Modpack> modpacks = new ArrayList<>();
        modpackRepository.findAll().forEach(modpacks::add);
        return modpacks;
    }

    public Modpack getModpack(int id) {
        return modpackRepository.findById(id).orElse(null);
    }

    public List<Modpack> getModpacksWithReleaseType(String releaseType) {
        return modpackRepository.findByModpackVersionType(releaseType);
    }

    public void addModpack(Modpack modpack) {
        modpackRepository.save(modpack);
    }

    public void updateModpack(Modpack modpack) {
        modpackRepository.save(modpack);
    }

    public void deleteModpack(int modpackId) {
        modpackRepository.deleteById(modpackId);
    }
}