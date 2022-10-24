package be.vdab.CursusVoorbeeldThreadJoin;

import be.vdab.CursusVoorbeeldThreadJoin.InsectenLezerJoin;

import java.io.IOException;

/*
    In Java als je een programma uitvoert wordt dit gedaan in de main thread. Als je naast de main thread
    ook nog andere threads wilt aanmaken dan maak je hiervoor objecten aan van de klasse Thread.
    Wanneer je in een thread a de method join uitvoert op een thread object b, pauzeert Java de uitvoering
    van de thread a tot de method run van het object b helemaal uitgevoerd is.

    Dit is noodzakelijk als thread a het eindresultaat van het werk van het thread object b nodig heeft.
    Thread a mag het resultaat van het thread object b maar opvragen nadat het thread object b zijn
    resultaat volledig aangemaakt heeft. Thread a mag het resultaat van het thread object b nog niet
    opvragen als het thread object b zijn resultaat nog aan het opbouwen is.
 */
public class InsectenLezerJoin_Main {
    public static void main(String[] args) {
        var lezer_1 = new InsectenLezerJoin("/data/insecten1.csv",System.out);
        var lezer_2 = new InsectenLezerJoin("/data/insecten2.csv",System.err);
        var thread_1= new Thread(lezer_1);
        var thread_2= new Thread(lezer_2);

        thread_1.start();
        thread_2.start();
        //Je vraagt in de class Main het eindresultaat aan beide threads, zonder te wachten tot ze hun werk
        //gedaan hebben, om te zien dat je dan een verkeerd resultaat krijgt.
        System.out.println(lezer_1.getAantalRegels()+lezer_2.getAantalRegels()+" regels (verkeerd result)");
        /*
        Je voegt na de regel thread2.start(); volgende regel toe:
        System.out.println(lezer1.getAantalRegels()+lezer2.getAantalRegels()+" regels");
        Je voert het programma uit. Je krijgt een verkeerd resultaat. Het juiste resultaat is 6121.
        Je lost het probleem nu op. Je voegt na de regel thread2.start(); volgende regels toe:
        */
        try{
            thread_1.join();
            thread_2.join();
            System.out.println(lezer_1.getAantalRegels()+lezer_2.getAantalRegels()+" regels (juist result)");
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        /*
        Je lost het probleem nu op. Je voegt na de regel thread2.start(); volgende regels toe:
            try {
            thread1.join();
            thread2.join();
            } catch (InterruptedException ex) {
            // Het uitvoeren van de join method kan een InterruptedException werpen
            // Je ziet hierover meer later in de cursus
            System.err.println(ex);
            }
            Je voert het programma uit en je krijgt wel het juiste resultaat (6121).
         */
    }
}
