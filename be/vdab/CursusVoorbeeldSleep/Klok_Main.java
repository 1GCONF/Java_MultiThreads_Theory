package be.vdab.CursusVoorbeeldSleep;


public class Klok_Main {
    public static void main(String[] args) {
        // Class Klok Start
        var klok = new Klok();
        var thread = new Thread(klok);
        thread.start();
    }
}
