package be.vdab.CursusVoorbeeldThreadErft;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InsectenLezer_Main {
    //Je toont in een applicatie de regels met een prijs tot en met 3.
    //Je zoekt met één thread in insecten1.csv.
    //Je zoekt tegelijk met een tweede thread in insecten2.csv.
    public static void main(String[] args) {
        var thread1 = new InsectenLezer("/data/insecten1.csv",System.out);
        var thread2 = new InsectenLezer("/data/insecten2.csv",System.err);
        thread1.start();
        thread2.start();
    }
}
