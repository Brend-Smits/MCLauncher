package net.toastynetworks.mcladmin.tests.integration;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IModpackLogic;
import net.toastynetworks.MCLAdmin.Domain.Modpack;
import net.toastynetworks.MCLAdmin.Factory.ModpackFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ModpackIntegrationTest {
    IModpackLogic modpackLogic = ModpackFactory.CreateLogicTests();

    @Test
    public void addModpack() {
        Modpack modpack = new Modpack("TestModpack", "Modpack", "dsad.dasd.d");
        modpackLogic.AddModpack(modpack);
    }
    @Test
    public void getAllModpacks() {
        List<Modpack> modpackList = modpackLogic.GetAllModpacks();
        System.out.println(modpackList.size());
        if (modpackList.stream().filter(t -> t.getName() == "TestModpack").findAny().get() == null) {
            Assert.fail();
        }
    }

}
