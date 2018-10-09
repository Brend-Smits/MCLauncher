package net.toastynetworks.MCLauncher.API.Service;

import net.toastynetworks.MCLauncher.API.Models.Modpack;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModpackService {

    List<Modpack> modpacks = new ArrayList<>(Arrays.asList(
            new Modpack("SF3", "Latest"),
            new Modpack("TL", "Latest"),
            new Modpack("IE", "Beta")
    )
    );

    public List<Modpack> getAllModpacks() {
        return modpacks;
    }

    public Modpack getModpack(String name) {
        return modpacks.stream().filter(t -> t.getName().equals(name)).findFirst().get();
    }

    public List<Modpack> getModpacksWithReleaseType(String releaseType) {
            return modpacks.stream().filter(t -> t.getVersionType().equals(releaseType)).collect(Collectors.toList());
    }

    public void addModpack(Modpack modpack) {
        modpacks.add(modpack);
    }

    public void updateModpack(Modpack modpack, String modpackName) {
        modpacks.stream().filter(t -> t.getName().equals(modpackName)).findFirst().ifPresent(i -> {i.setName(modpack.getName());i.setVersionType(modpack.getVersionType());});
    }

    public void deleteModpack(String modpackName) {
        modpacks.removeIf(t -> t.getName().equals(modpackName));
    }
}
