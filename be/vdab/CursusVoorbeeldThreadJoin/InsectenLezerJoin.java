package be.vdab.CursusVoorbeeldThreadJoin;

import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

/*
De method join van een Thread object
    In Java als je een programma uitvoert wordt dit gedaan in de main thread. Als je naast de main thread
    ook nog andere threads wilt aanmaken dan maak je hiervoor objecten aan van de klasse Thread.
    Wanneer je in een thread a de method join uitvoert op een thread object b, pauzeert Java de uitvoering
    van de thread a tot de method run van het object b helemaal uitgevoerd is.

    Dit is noodzakelijk als thread a het eindresultaat van het werk van het thread object b nodig heeft.
    Thread a mag het resultaat van het thread object b maar opvragen nadat het thread object b zijn
    resultaat volledig aangemaakt heeft. Thread a mag het resultaat van het thread object b nog niet
    opvragen als het thread object b zijn resultaat nog aan het opbouwen is.


    Je vraagt in de class Main het eindresultaat aan beide threads, zonder te wachten tot ze hun werk
    gedaan hebben, om te zien dat je dan een verkeerd resultaat krijgt.
    Je voegt na de regel thread2.start(); volgende regel toe:
    System.out.println(lezer1.getAantalRegels()+lezer2.getAantalRegels()+" regels");
    Je voert het programma uit. Je krijgt een verkeerd resultaat. Het juiste resultaat is 6121.

 */
public class InsectenLezerJoin implements Runnable {

    private int aantalRegels;
    private static final BigDecimal MAXIMUM = BigDecimal.valueOf(3);
    private final Path pad;
    private final PrintStream stream;

    public InsectenLezerJoin(String bestandLoatie, PrintStream stream) {
        this.pad = Path.of(bestandLoatie);
        this.stream = stream;
    }
    /*
        Je past de applicatie aan. De threads tonen niet de regels met een maximum prijs 3,
        maar tellen deze regels. De class Main toont de som van deze twee tellers.
        Je voegt aan de class InsectenLezer een private variabele toe: private int aantalRegels;
        Je vervangt in de method run de regel stream.println(pad.getFileName() + ":" + regel);
        door ++aantalRegels;
        Je voegt een method toe:
        public int getAantalRegels() {return aantalRegels;}
     */

    @Override
    public void run() {
        if (Files.exists(pad)) {
            try (var reader = Files.newBufferedReader(pad)) {
                //Je toont in een applicatie de regels met een prijs tot en met 3.
                for (String regel; (regel = reader.readLine()) != null; ) {
                    var regel_onderdelen = regel.split(";");
                    var prijs = new BigDecimal(regel_onderdelen[1]);
                    if(prijs.compareTo(MAXIMUM)<=0){
                        ++aantalRegels;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public int getAantalRegels() {
        return aantalRegels;
    }
}
