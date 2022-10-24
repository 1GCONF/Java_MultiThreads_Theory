package be.vdab.CursusVoorbeeldSleep;

import java.time.LocalTime;

public class Klok_Interrupt implements Runnable{
    @Override
    public void run() {
        var verderDoen =true;
        while(verderDoen){
            System.out.println(LocalTime.now());
            if (Thread.interrupted()){
                verderDoen=false;
            }
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                verderDoen=false;
            }
            //Je kan aan een thread aangeven dat je zijn uitvoering wil stoppen door de method interrupt uit te voeren.
            /*
            Als de thread op pauze staat (tijdens het uitvoeren van Thread.sleep() of het uitvoeren van de
            join opdracht op een andere thread), krijgt de thread een InterruptedException.
            ● Anders komt de thread in de “interrupted” status. In die status blijft de thread lopen. De thread
            kan opvragen of het zich in de interrupted status bevindt, via de static method interrupted van
            de class Thread. Deze method geeft true terug als de huidige thread zich in de interrupted
            status bevindt. De thread kan dan eventueel gestopt worden.
             */
        }
    }
}
