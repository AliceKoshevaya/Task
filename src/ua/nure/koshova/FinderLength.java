package ua.nure.koshova;

import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class FinderLength implements Runnable{

    private long findLenght = 500;
    private FileCollector fc;
    private boolean finish = false;

    public FinderLength(FileCollector fc) {
        this.fc = fc;
    }

    public void finish() {
        this.finish = true;
    }

    @Override
    public void run() {

        while(true) {
            Iterator<Map.Entry<String, BasicFileAttributes>> entry = FileCollector.files.entrySet().iterator();
            while (entry.hasNext())
            {
                Map.Entry entryMap = entry.next();
                BasicFileAttributes bs = (BasicFileAttributes)entryMap.getValue();
                if(bs.size() > findLenght){
                    System.out.println(entryMap.getKey());
                }
            }
            break;

        }
    }
}
