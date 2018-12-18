package net.toastynetworks.MCLRestAPI.Service;

import net.toastynetworks.MCLRestAPI.Models.Modpack;
import net.toastynetworks.MCLRestAPI.Repository.ModpackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return modpackRepository.findByVersionType(releaseType);
    }

    public void addModpack(Modpack modpack) {
        modpackRepository.save(modpack);
    }

    public void updateModpack(Modpack modpack, int id) {
//        modpackRepository.save(modpack, id);
        modpackRepository.findById(id)
                .map(mod -> {
                    mod.setName(modpack.getName());
                    mod.setVersionType(modpack.getVersionType());
                    mod.setDownloadUrl(modpack.getDownloadUrl());
                    return modpackRepository.save(mod);
                })
                .orElseGet(() -> {
                    modpack.setId(id);
                    return modpackRepository.save(modpack);
                });
    }

    public void deleteModpack(int modpackId) {
        modpackRepository.deleteById(modpackId);
    }
}