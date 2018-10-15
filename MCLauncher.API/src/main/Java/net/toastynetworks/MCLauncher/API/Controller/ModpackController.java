package net.toastynetworks.MCLauncher.API.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.toastynetworks.MCLauncher.API.Models.Modpack;
import net.toastynetworks.MCLauncher.API.Service.ModpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/modpack")
@Api(value = "Modpack Resource", description = "Modpack data")
public class ModpackController {

    @Autowired
    private ModpackService modpackService;

    @ApiOperation("Get all Modpacks")
    @RequestMapping(method = RequestMethod.GET)
    public List<Modpack> getAllModpacks() {
        return modpackService.getAllModpacks();
    }

    @ApiOperation("Get a single Modpack")
    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public Modpack getModpack(@PathVariable String name) {
        return modpackService.getModpack(name);
    }

    @ApiOperation("Get all modpacks with a specific release type")
    @RequestMapping(method = RequestMethod.GET, value = "/release/{releaseType}")
    public List<Modpack> getModpacksWithReleaseType(@PathVariable String releaseType) {
        return modpackService.getModpacksWithReleaseType(releaseType);
    }

    @ApiOperation("Add a new modpack")
    @RequestMapping(method = RequestMethod.POST, value = "/modpack")
    public void addModpack(@RequestBody Modpack modpack) {
        modpackService.addModpack(modpack);
    }

    @ApiOperation("Update a modpack")
    @RequestMapping(method = RequestMethod.PUT, value = "/{name}")
    public void updateModpack(@RequestBody Modpack modpack, @PathVariable String name) {
        modpackService.updateModpack(modpack, name);
    }

    @ApiOperation("Delete a modpack")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{name}")
    public void deleteModpack(@PathVariable String name) {
        modpackService.deleteModpack(name);
    }
}
