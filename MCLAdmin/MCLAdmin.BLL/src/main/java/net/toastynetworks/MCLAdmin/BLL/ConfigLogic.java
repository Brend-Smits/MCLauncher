package net.toastynetworks.MCLAdmin.BLL;

import net.toastynetworks.MCLAdmin.BLL.Interfaces.IConfigLogic;

import java.io.*;
import java.util.Properties;

public class ConfigLogic implements IConfigLogic {
    public void CreateConfig() {
        try {
            File file = new File(System.getenv("APPDATA") + "/" + ".MCLauncher/admin/" + "config.properties");
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String GetWorkSpaceFromConfig(String modpackName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(System.getenv("APPDATA") + "/" + ".MCLauncher/admin/" + "config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(modpackName);
    }

//    public void FillConfig() {
//        try {
//            Properties prop = new Properties();
//            OutputStream output = new FileOutputStream(file);
//            // save properties to project root folder
//            prop.store(output, null);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void EditConfig(String modpackname, String newWorkspace) {
        try {
            Properties prop = new Properties();
            InputStream input = null;
            try {
                input = new FileInputStream(System.getenv("APPDATA") + "/" + ".MCLauncher/admin/" + "config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                prop.load(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            OutputStream output = new FileOutputStream(System.getenv("APPDATA") + "/" + ".MCLauncher/admin/" + "config.properties");
            if (prop.getProperty(modpackname) == null) {
                prop.setProperty(modpackname, newWorkspace);
            } else {
                prop.replace(modpackname, newWorkspace);
            }
            prop.store(output, null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void DeleteWorkSpaceFromConfig(String modpackName) {
        try {
            Properties prop = new Properties();
            InputStream input = null;
            try {
                input = new FileInputStream(System.getenv("APPDATA") + "/" + ".MCLauncher/admin/" + "config.properties");
                prop.load(input);
                prop.remove(modpackName);
                OutputStream output = new FileOutputStream(System.getenv("APPDATA") + "/" + ".MCLauncher/admin/" + "config.properties");
                prop.store(output, null);
            } catch (FileNotFoundException io) {
                System.out.println(io);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
