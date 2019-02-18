package ua.nure.koshova;

public class Test {
    public static void main(String[] args) throws InterruptedException {

        FileCollector fc = new FileCollector();
        Thread tfc = new Thread(fc);
        FinderLength finder = new FinderLength(fc);
        Thread f = new Thread(finder);
        FinderAtttr finderAtttr = new FinderAtttr(fc);
        Thread f2 = new Thread(finderAtttr);
        FinderText f3 = new FinderText(fc, "class");
        Thread t3 = new Thread(f3);

        System.out.println("Все файлы:");
        tfc.start();
        try {
            tfc.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Поиск по длинне > 500");
        tfc.join();
        f.start();
        f.join();
        System.out.println("Поиск по атрибуту");
        f2.start();
        f2.join();
         System.out.println("Поиск по тексту");
        t3.start();
        t3.join();


    }
}
