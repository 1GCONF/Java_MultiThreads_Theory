/*
De class die de thread voorstelt:
● Je maakt een class (bijvoorbeeld MyThread) die erft van de class Thread.
● Je override de method run (die je erft van Thread).
● Je schrijft in deze method de code die je in een thread wil uitvoeren.
De thread uitvoeren:
● Je maakt een object van je thread class:
var myThread = new MyThread();
● Je voert op dit object de method start uit.
Deze method vraagt aan het besturingssysteem een nieuwe thread en voert met die thread de
code uit in de method run (van de class MyThread).
 */
package be.vdab.CursusVoorbeeldThreadErft;

import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class InsectenLezer extends Thread {
    /*
    Bij de cursus horen de bestanden insecten1.csv en insecten2.csv. Beide bestanden bevatten informatie
    over insecten.
    Één regel bevat de naam en de prijs (gescheiden door een ;) van één insect.
    Je plaatst deze bestanden in een directory \data.
    Je toont in een applicatie de regels met een prijs tot en met 3.
    Je zoekt met één thread in insecten1.csv. Je zoekt tegelijk met een tweede thread in insecten2.csv. Om
    de output van de threads te onderscheiden, stuurt de 1° thread zijn output naar System.out. De IDE
    toont deze output met zwarte letters. De 2° thread stuurt zijn output naar System.err (waar je normaal
    foutberichten naar stuurt).
     */
    private static final BigDecimal MAXIMUM = BigDecimal.valueOf(3);
    private final Path pad; // zal naar insecten1.csv of insecten2.csv wijzen
    private final PrintStream stream; // staat voor System.out of System.err

    public InsectenLezer(String bestand, PrintStream stream) {
        this.pad = Path.of(bestand);
        this.stream = stream;
    }

    @Override
    public void run() {
        if (Files.exists(pad)) {
            try (var reader = Files.newBufferedReader(pad)) {
                //Je toont in een applicatie de regels met een prijs tot en met 3.
                for (String regel; (regel = reader.readLine()) != null; ) {
                    var regel_onderdelen = regel.split(";");
                    var prijs = new BigDecimal(regel_onderdelen[1]);
                    if(prijs.compareTo(MAXIMUM)<=0){
                        stream.println(pad.getFileName() + " - " + regel);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
