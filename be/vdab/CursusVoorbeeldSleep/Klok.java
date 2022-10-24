package be.vdab.CursusVoorbeeldSleep;

import java.time.LocalTime;

public class Klok implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println(LocalTime.now());
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                // Het uitvoeren van de sleep method kan een
                // InterruptedException werpen.
                // Je ziet hierover meer later in de cursus
                System.out.println(e);
            }
        }
    }
}
