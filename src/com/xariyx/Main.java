package com.xariyx;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws URISyntaxException, IOException {

        File folder = new File(
                Main
                        .class
                        .getProtectionDomain()
                        .getCodeSource()
                        .getLocation()
                        .toURI()
        ).getParentFile();

        System.out.println(folder.getPath());
        ArrayList<File> files = FileLoader(folder);
        if (files == null) {
            return;

        }

        File random = getRandomFileFromArray(files);

        if(random == null){
            return;
        }
        Desktop.getDesktop().open(random);


    }

    public static ArrayList<File> FileLoader(File folder) {
        ArrayList<File> files = new ArrayList<>();
        if (folder == null) {
            return null;
        }

        File[] listedFiles = folder.listFiles();

        if (listedFiles == null) {
            return null;
        }

        for (File fileEntry : listedFiles) {
            if (fileEntry.isDirectory()) {
                files.addAll(FileLoader(fileEntry));
            } else {
                String[] parts = fileEntry.getName().split("\\.");
                if (parts[parts.length - 1].equals("jar")) {
                    continue;
                }
                files.add(fileEntry);
            }
        }

        return files;
    }

    public static File getRandomFileFromArray(ArrayList<File> arrayList) {
        if (arrayList.size() == 0 ){
            return null;
        }
        return arrayList.get((int) (Math.random() * (arrayList.size() - 1)));
    }


}
