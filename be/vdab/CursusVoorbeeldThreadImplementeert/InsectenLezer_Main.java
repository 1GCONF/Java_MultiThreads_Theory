package be.vdab.CursusVoorbeeldThreadImplementeert;

public class InsectenLezer_Main {
    public static void main(String[] args) {
        var lezer1 = new InsectenLezer("/data/insecten1.csv",System.out);
        var thread1 = new Thread(lezer1);
        var lezer2 = new InsectenLezer("/data/insecten2.csv",System.err);
        var thread2 = new Thread(lezer2);
        thread1.start();
        thread2.start();
    }
}
