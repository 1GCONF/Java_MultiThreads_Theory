package be.vdab.CursusVoorbeeldThreadImplementeert;

import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class InsectenLezer implements Runnable{
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
