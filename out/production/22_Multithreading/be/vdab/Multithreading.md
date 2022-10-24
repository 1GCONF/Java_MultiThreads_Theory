Proces
    Een proces is een programma in uitvoering.
    Een tekstverwerker en een rekenbladprogramma die je startte, zijn dus 2 processen.
    Elk proces heeft zijn eigen interne geheugenruimte.
    Een proces kan niet lezen of schrijven in de geheugenruimte van een ander proces.
Thread
    Een thread is het uitvoeren van code binnen een proces.
    Ieder proces heeft minstens één thread.
    Een proces kan meerdere threads hebben. Dit heet multithreading: het tegelijk uitvoeren van
    verschillende code binnen een proces. Een browser is bijvoorbeeld een multithreaded programma: je
    kan een groot bestand downloaden en tegelijk surfen naar andere pagina’s. De browser voert het
    downloaden uit met een thread, en het surfen met een andere thread. Je kan zelfs meerdere bestanden
    tegelijk downloaden. De browser voert deze extra taken uit met extra threads.
    Alle threads binnen een proces delen de geheugenruimte van dat proces.
    Naast multithreading bestaat ook het woord multiprocessing.
    Dit betekent het gelijktijdig uitvoeren van meerdere processen (applicaties).
    Multithreading en multiprocessing vormen één groot geheel: de computer voert meerdere gelijktijdige
    processen uit, die zelf één of meerdere gelijktijdige threads uitvoeren.
Het verdelen van threads over processoren
    Als een computer minstens evenveel processoren bevat als het aantal uit te voeren threads, verdeelt de
    computer de threads over de processoren. Iedere processor voert een thread uit. Gelijktijdig voeren de
    andere processoren een andere thread uit.
    Dit leidt tot een optimale performantie.
    Voorbeeld: een computer met vier processoren moet twee processen uitvoeren, die elk twee threads
    uitvoeren. De computer moet dus in totaal vier threads uitvoeren. Iedere processor voert één van deze
    threads uit.
    Opmerking: deze threads hoeven niet op hetzelfde moment te starten, en de uitvoeringstijd hoeft niet
    even lang te zijn.
    Als de computer minder processoren bevat dan het aantal uit te voeren threads, 
    verdeelt het besturingssysteem de threads over de processoren.
    Als er twee processoren zijn en vier threads, voert de ene processor twee threads uit en de andere
    processor de overige twee threads.
            Een processor kan echter op een bepaald moment maar één thread uitvoeren. 
            Om dit probleem op te lossen gebruikt het besturingssysteem timeslicing:
            de processor voert een aantal milliseconden code uit van de eerste thread en zet dan deze thread op
            pauze. De processor voert daarna een aantal milliseconden code uit van de tweede thread en zet dan
            deze thread op pauze. De processor voert terug een aantal milliseconden code uit van de eerste thread
            en zet dan deze thread op pauze, ...
                Een Java applicatie heeft minstens één thread: de thread die begint bij
                public static void main(String[] args) {
Threads in Java
    Een thread is een object.
    Je kan de class, die een thread object voorstelt, op twee manieren maken:
    ● Als een class die erft van de class Thread.
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
    ● Als een class die de interface Runnable implementeert.
            De class die de thread voorstelt:
            ● Je implementeert in de class (bijvoorbeeld MyRunner) de interface Runnable.
            ● Je implementeert de method run (gedeclareerd in Runnable).
            ● Je schrijft in deze method de code die je in een thread wil uitvoeren.
            De thread uitvoeren:
            ● Je maakt een object van je eigen class: MyRunner myRunner = new MyRunner();
            ● Je maakt een Thread object. Je geef het object van je eigen class mee aan de Thread constructor:
            Thread thread = new Thread(myRunner);
            ● Je voert op dit Thread object de method start uit. Deze method vraagt aan het
            besturingssysteem een nieuwe thread en voert met die thread de code uit in de method run
            (van het MyRunner object).

                        De tweede manier wordt aangeraden. Bij de tweede manier kan je nog vrij kiezen om de class te laten
                        erven van om het even welke class. Bij de eerste manier kan de class niet meer erven van om het even
                        welke class, want de class erft al van Thread. (Class cannot extend multiple classes)
                        We zien eerst de (wat eenvoudiger) eerste manier, daarna de tweede manier.
                        Bij de cursus horen de bestanden insecten1.csv en insecten2.csv. Beide bestanden bevatten informatie
                        over insecten. Één regel bevat de naam en de prijs (gescheiden door een ;) van één insect.
                        Je plaatst deze bestanden in een directory \data.
                        Je toont in een applicatie de regels met een prijs tot en met 3.
                        Je zoekt met één thread in insecten1.csv. Je zoekt tegelijk met een tweede thread in insecten2.csv. Om
                        de output van de threads te onderscheiden, stuurt de 1° thread zijn output naar System.out. De IDE
                        toont deze output met zwarte letters. De 2° thread stuurt zijn output naar System.err (waar je normaal
                        foutberichten naar stuurt).
System.err vs System.out
    Aangezien we voor het eerst in aanraking komen hier met een nieuwe vorm van output naar console is
    het aangewezen om hier ook even kort bij stil te staan. Beide vormen zullen standaard hun output
    plaatsen op het systeem waar het gebruikt wordt. In een standaard java applicatie zal dit dus altijd de
    console zijn. Het enige verschil dat we in onze IDE zien is dat System.out gewoon standaard zwart
    gekleurd is en System.err wordt rood gekleurd.
    Wat vaak gebeurt is dat we de errormeldingen niet wegschrijven in console, maar naar een bestand.
    Voorbeeld:
    System.setErr(new PrintStream("/data/fouten.txt"));
    System.err.println("Deze error komt in de file fouten.txt terecht");
    Op deze manier kan je ook je standaard output wijzigen naar een bestand. Dit doe je dan als volgt:
    System.setOut(new PrintStream("/data/output.txt"));
De method join van een Thread object
    In Java als je een programma uitvoert wordt dit gedaan in de main thread. Als je naast de main thread
    ook nog andere threads wilt aanmaken dan maak je hiervoor objecten aan van de klasse Thread.
    Wanneer je in een thread a de method join uitvoert op een thread object b, pauzeert Java de uitvoering
    van de thread a tot de method run van het object b helemaal uitgevoerd is.
    Dit is noodzakelijk als thread a het eindresultaat van het werk van het thread object b nodig heeft.
    Thread a mag het resultaat van het thread object b maar opvragen nadat het thread object b zijn
    resultaat volledig aangemaakt heeft. Thread a mag het resultaat van het thread object b nog niet
    opvragen als het thread object b zijn resultaat nog aan het opbouwen is.
    Je past de applicatie aan. De threads tonen niet de regels met een maximum prijs 3,
    maar tellen deze regels. De class Main toont de som van deze twee tellers.
    Je voegt aan de class InsectenLezer een private variabele toe: private int aantalRegels;
    Je vervangt in de method run de regel stream.println(pad.getFileName() + ":" + regel);
    door ++aantalRegels;
    Je voegt een method toe:
    public int getAantalRegels() {
    return aantalRegels;
    }
    Je vraagt in de class Main het eindresultaat aan beide threads, zonder te wachten tot ze hun werk
    gedaan hebben, om te zien dat je dan een verkeerd resultaat krijgt.
    Je voegt na de regel thread2.start(); volgende regel toe:
    System.out.println(lezer1.getAantalRegels()+lezer2.getAantalRegels()+" regels");
    Je voert het programma uit. Je krijgt een verkeerd resultaat. Het juiste resultaat is 6121.
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
De static method sleep van de class Thread
    Je kan in om het even welke thread de static method sleep van de class Thread oproepen.
    Je geeft als parameter een aantal milliseconden mee.
    Java zet de thread waarin je deze method oproep doet, evenveel milliseconden op pauze.
    Je probeert dit uit in een class Klok. Je toont in die class één keer per seconde de systeemtijd.
De method interrupt van een Thread object
    Je kan aan een thread aangeven dat je zijn uitvoering wil stoppen door de method interrupt uit te
    voeren. Deze method stopt de thread niet, maar doet één van volgende handelingen:
    ● Als de thread op pauze staat (tijdens het uitvoeren van Thread.sleep() of het uitvoeren van de
    join opdracht op een andere thread), krijgt de thread een InterruptedException.
    ● Anders komt de thread in de “interrupted” status. In die status blijft de thread lopen. De thread
    kan opvragen of het zich in de interrupted status bevindt, via de static method interrupted van
    de class Thread. Deze method geeft true terug als de huidige thread zich in de interrupted
    status bevindt. De thread kan dan eventueel gestopt worden.
    Je wijzigt de applicatie. Wanneer de gebruiker op Enter drukt, stop je de applicatie.
    Je controleert het toetsenbord in de thread van de class Main.
    Wanneer de gebruiker op Enter drukt, voer je de method interrupt uit op het thread object.
    Je voegt in de class Main na de regel thread.start(); volgende regels toe:
