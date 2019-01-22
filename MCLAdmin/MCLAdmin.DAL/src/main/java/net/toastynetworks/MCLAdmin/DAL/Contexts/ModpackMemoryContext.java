package net.toastynetworks.MCLAdmin.DAL.Contexts;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import net.toastynetworks.MCLAdmin.DAL.Contexts.Interfaces.IModpackContext;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Domain.UploadedFile;
import net.toastynetworks.unirest.UnirestObjectMapperUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModpackMemoryContext implements IModpackContext {
    List<Modpack> modpackList = new ArrayList<>();

    public List<Modpack> GetAllModpacks() {
        return GetModpackList();
    }

    public void AddModpack(Modpack modpack) {
        try {
            modpackList.add(modpack);

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }

    public List<Modpack> GetModpackList() {
        try {
            return modpackList;
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void EditModpack(Modpack modpack, int nonEditModpackId) {
        try {
            Modpack modpackInList = modpackList.stream().filter(o -> o.getId() == modpack.getId()).findAny().get();
            if (modpackList.contains(modpackInList)) {
                modpackList.remove(modpackInList);
                modpackList.add(modpack);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void EditModpack(Modpack modpack, UploadedFile file, int nonEditModpackId) {
        try {
            Modpack modpackInList = modpackList.stream().filter(o -> o.getId() == modpack.getId()).findAny().get();
            if (modpackList.contains(modpackInList)) {
                modpackList.remove(modpackInList);
                modpackList.add(modpack);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DeleteModpack(int id) {
        try {
            Modpack modpackInList = modpackList.stream().filter(o -> o.getId() == id).findAny().get();
            modpackList.remove(modpackInList);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
