package be.vdab.CursusVoorbeeldSleep;
import be.vdab.CursusVoorbeeldSleep.Klok_Interrupt_Main;

import java.util.Scanner;

public class Klok_Interrupt_Main {
    public static void main(String[] args) {
        var klok_interrupted = new Klok_Interrupt();
        var thread = new Thread(klok_interrupted);

        var scanner = new Scanner(System.in);
        scanner.nextLine();// deze method wacht tot de gebruiker Enter drukt
        thread.interrupt(); // er wordt dan een InterruptedException gethrowed

    }
}
