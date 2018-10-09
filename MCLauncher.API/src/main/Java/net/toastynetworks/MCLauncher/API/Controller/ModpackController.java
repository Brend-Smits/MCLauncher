package net.toastynetworks.MCLauncher.API.Controller;

import net.toastynetworks.MCLauncher.API.Models.Modpack;
import net.toastynetworks.MCLauncher.API.Service.ModpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModpackController {

    @Autowired
    private ModpackService modpackService;

    @RequestMapping("/modpack")
    public List<Modpack> getAllModpacks() {
        return modpackService.getAllModpacks();
    }

    @RequestMapping("/modpack/{name}")
    public Modpack getModpack(@PathVariable String name) {
        return modpackService.getModpack(name);
    }

    @RequestMapping("/modpack/release/{releaseType}")
    public List<Modpack> getModpacksWithReleaseType(@PathVariable String releaseType) {
        return modpackService.getModpacksWithReleaseType(releaseType);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/modpack")
    public void addModpack(@RequestBody Modpack modpack) {
        modpackService.addModpack(modpack);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/modpack/{name}")
    public void updateModpack(@RequestBody Modpack modpack, @PathVariable String name) {
        modpackService.updateModpack(modpack, name);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "modpack/{name}")
    public void deleteModpack(@PathVariable String name) {
        modpackService.deleteModpack(name);
    }
}
