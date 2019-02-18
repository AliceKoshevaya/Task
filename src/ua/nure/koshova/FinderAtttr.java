package ua.nure.koshova;

import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

public class FinderAtttr implements Runnable {

    private String dateFind = "01/30/2019";
    private FileCollector fc;
    private boolean finish = false;

    public FinderAtttr(FileCollector fc) {
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
                FileTime date = bs.creationTime();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String dateCreated = df.format(date.toMillis());
                if(dateCreated.equals(dateFind)){
                    System.out.println(entryMap.getKey());
                }
            }
            break;

        }
    }
}
