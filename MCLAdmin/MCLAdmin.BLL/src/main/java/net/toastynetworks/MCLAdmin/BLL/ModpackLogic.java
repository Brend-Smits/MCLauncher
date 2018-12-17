package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackUploadLogic;
import net.toastynetworks.MCLAdmin.DAL.Interfaces.IModpackRepository;
import net.toastynetworks.MCLAdmin.Domain.Modpack;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

public class ModpackLogic implements IModpackLogic {
    private IModpackRepository modpackRepository;

    public ModpackLogic(IModpackRepository modpackRepo) {
        modpackRepository = modpackRepo;
    }

    public List<Modpack> GetAllModpacks() {
        return modpackRepository.GetAllModpacks();
    }

    public void AddModpack(Modpack modpack) {
        modpackRepository.AddModpack(modpack);
    }

    public void EditModpack(Modpack modpack) {
        modpackRepository.EditModpack(modpack);
    }

    public void DeleteModpack(int id) {
        modpackRepository.DeleteModpack(id);
    }
}
