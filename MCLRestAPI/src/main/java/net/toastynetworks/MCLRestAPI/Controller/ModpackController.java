package net.toastynetworks.MCLRestAPI.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.toastynetworks.MCLRestAPI.Models.Modpack;
import net.toastynetworks.MCLRestAPI.Service.ModpackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/v1/modpack")
@Api(value = "Modpack Resource", description = "Modpack data")
public class ModpackController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

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
            logger.info("No modpack can be found");
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
        try {
            modpackService.addModpack(new Modpack(modpack.getName(), modpack.getVersionType()));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Modpack could not be added: " + e);
        }
    }

    @ApiOperation("Update a modpack")
    @RequestMapping(method = RequestMethod.PUT)
    public void updateModpack(@RequestBody Modpack modpack) {
        try {
            modpackService.updateModpack(modpack);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Modpack could not be updated: " + e);
        }
    }

    @ApiOperation("Delete a modpack by ID")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteModpack(@PathVariable int id) {
        try {
            modpackService.deleteModpack(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Modpack could not be deleted " + e);
        }
    }
}