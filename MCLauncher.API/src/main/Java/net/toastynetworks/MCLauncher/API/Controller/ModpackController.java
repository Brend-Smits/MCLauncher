package net.toastynetworks.MCLauncher.API.Controller;

import net.toastynetworks.MCLauncher.API.Models.Modpack;
import net.toastynetworks.MCLauncher.API.Service.ModpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ModpackController {

    @Autowired
    private ModpackService modpackService;

    @RequestMapping("/modpack")
    public List<Modpack> getAllModpacks() {
        return modpackService.getAllModpacks();
    }
}
