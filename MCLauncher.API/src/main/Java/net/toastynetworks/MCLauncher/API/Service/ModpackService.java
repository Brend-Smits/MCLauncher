package net.toastynetworks.MCLauncher.API.Service;

import net.toastynetworks.MCLauncher.API.Models.Modpack;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ModpackService {

    List<Modpack> modpacks = Arrays.asList(
            new Modpack("SF3", "Latest"),
            new Modpack("IE", "Beta")
    );

    public List<Modpack> getAllModpacks() {
        return modpacks;
    }

}
