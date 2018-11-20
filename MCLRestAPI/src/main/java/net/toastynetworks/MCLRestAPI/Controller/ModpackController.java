package net.toastynetworks.MCLRestAPI.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.toastynetworks.MCLRestAPI.Models.Modpack;
import net.toastynetworks.MCLRestAPI.Service.ModpackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public Modpack getModpack(@PathVariable int id, HttpServletResponse response) {
        Modpack modpack = modpackService.getModpack(id);
        if (modpack == null) {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return modpack;
    }

    @ApiOperation("Get all modpacks with a specific release type")
    @RequestMapping(method = RequestMethod.GET, value = "/release/{versionType}")
    public List<Modpack> getModpacksWithReleaseType(@PathVariable String versionType) {
        return modpackService.getModpacksWithReleaseType(versionType);
    }

    @ApiOperation("Add a new modpack")
    @RequestMapping(method = RequestMethod.POST, value = "/addModpack", produces = "application/json")
    public void addModpack(@RequestBody Modpack modpack) {
        modpackService.addModpack(new Modpack(modpack.getName(), modpack.getVersionType()));
    }

    @ApiOperation("Update a modpack")
    @RequestMapping(method = RequestMethod.PUT)
    public void updateModpack(@RequestBody Modpack modpack) {
        modpackService.updateModpack(modpack);
    }

    @ApiOperation("Delete a modpack by ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteModpack(@PathVariable int id) {
        modpackService.deleteModpack(id);
    }
}