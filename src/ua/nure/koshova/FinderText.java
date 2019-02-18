package ua.nure.koshova;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class FinderText implements Runnable {

    private FileCollector fc;
    private String text;
    public static String path = "E:\\4alice\\Обои";

    public FinderText(FileCollector fc, String text) {
        this.fc = fc;
        this.text = text;
    }

    @Override
    public void run() {

        while(true) {
            Scanner txtscan;
            String result = "";
            for (Map.Entry<String, BasicFileAttributes> entry : FileCollector.files.entrySet()) {
                try {
                    txtscan = new Scanner(new File(entry.getKey()));
                    while (txtscan.hasNextLine()) {
                        String str = txtscan.nextLine();
                        if (str.indexOf(text) != -1) {
                            result += entry.getKey() + "\n";
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.err.println(e);
                }
                fileCreator(path , result);
            }

        }
    }
    private static void fileCreator(String filename, String fileContent) {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e1) {
            System.err.println(e1);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.append(' ');
            writer.append(fileContent);
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
