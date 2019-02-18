package ua.nure.koshova;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.regex.Pattern;

public class FileCollector implements Runnable{

    public static volatile Map<String, BasicFileAttributes> files = new ConcurrentSkipListMap<String,BasicFileAttributes>();
    public static String path = "E:\\4alice\\Обои";
    private boolean finish = false;
    private static String ext = ".pdf";

    public int getSize() {
        return files.size();
    }


    @Override
    public void run() {
        try {
            iterateFilesFromFolder(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        finish = true;
    }

    public boolean isFinish() {
        return finish;
    }

    private  void iterateFilesFromFolder(File folder) throws IOException {
        File[] folderEntries = folder.listFiles(new MyFileNameFilter(ext));
        try {
            for (File entry : folderEntries) {
                if (entry.isDirectory())
                    iterateFilesFromFolder(entry);
                else {
                    BasicFileAttributes attr = null;
                    Path path2 = Paths.get(path, entry.getName());
                    attr = Files.readAttributes(path2, BasicFileAttributes.class);
                    files.put(entry.getName(), attr);
                    System.out.println(entry.getName());
                    Thread.sleep(100);
                }
            }
        } catch(InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, BasicFileAttributes> entry : files.entrySet() ) {
            sb.append(String.format("%s : %d %n", entry.getKey(), entry.getValue()));
        }

        return sb.toString();
    }
    public static class MyFileNameFilter implements FilenameFilter {

        private String ext;

        public MyFileNameFilter(String ext){
            this.ext = ext.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().endsWith(ext);
        }
    }
}

