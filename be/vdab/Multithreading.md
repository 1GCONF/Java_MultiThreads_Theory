1. Proces
2.     Een proces is een programma in uitvoering.
3.     Een tekstverwerker en een rekenbladprogramma die je startte, zijn dus 2 processen.
4.     Elk proces heeft zijn eigen interne geheugenruimte.
5.     Een proces kan niet lezen of schrijven in de geheugenruimte van een ander proces.
6. Thread
7.     Een thread is het uitvoeren van code binnen een proces.
8.     Ieder proces heeft minstens één thread.
9.     Een proces kan meerdere threads hebben. Dit heet multithreading: het tegelijk uitvoeren van
10.     verschillende code binnen een proces. Een browser is bijvoorbeeld een multithreaded programma: je
11.     kan een groot bestand downloaden en tegelijk surfen naar andere pagina’s. De browser voert het
12.     downloaden uit met een thread, en het surfen met een andere thread. Je kan zelfs meerdere bestanden
13.     tegelijk downloaden. De browser voert deze extra taken uit met extra threads.
14.     Alle threads binnen een proces delen de geheugenruimte van dat proces.
15.     Naast multithreading bestaat ook het woord multiprocessing.
16.     Dit betekent het gelijktijdig uitvoeren van meerdere processen (applicaties).
17.     Multithreading en multiprocessing vormen één groot geheel: de computer voert meerdere gelijktijdige
18.     processen uit, die zelf één of meerdere gelijktijdige threads uitvoeren.
19. Het verdelen van threads over processoren
20.     Als een computer minstens evenveel processoren bevat als het aantal uit te voeren threads, verdeelt de
21.     computer de threads over de processoren. Iedere processor voert een thread uit. Gelijktijdig voeren de
22.     andere processoren een andere thread uit.
23.     Dit leidt tot een optimale performantie.
24.     Voorbeeld: een computer met vier processoren moet twee processen uitvoeren, die elk twee threads
25.     uitvoeren. De computer moet dus in totaal vier threads uitvoeren. Iedere processor voert één van deze
26.     threads uit.
27.     Opmerking: deze threads hoeven niet op hetzelfde moment te starten, en de uitvoeringstijd hoeft niet
28.     even lang te zijn.
29.     Als de computer minder processoren bevat dan het aantal uit te voeren threads, 
30.     verdeelt het besturingssysteem de threads over de processoren.
31.     Als er twee processoren zijn en vier threads, voert de ene processor twee threads uit en de andere
32.     processor de overige twee threads.
33.             Een processor kan echter op een bepaald moment maar één thread uitvoeren. 
34.             Om dit probleem op te lossen gebruikt het besturingssysteem timeslicing:
35.             de processor voert een aantal milliseconden code uit van de eerste thread en zet dan deze thread op
36.             pauze. De processor voert daarna een aantal milliseconden code uit van de tweede thread en zet dan
37.             deze thread op pauze. De processor voert terug een aantal milliseconden code uit van de eerste thread
38.             en zet dan deze thread op pauze, ...
39.                 Een Java applicatie heeft minstens één thread: de thread die begint bij
40.                 public static void main(String[] args) {
41. Threads in Java
42.     Een thread is een object.
43.     Je kan de class, die een thread object voorstelt, op twee manieren maken:
44.     ● Als een class die erft van de class Thread.
45.             De class die de thread voorstelt:
46.             ● Je maakt een class (bijvoorbeeld MyThread) die erft van de class Thread.
47.             ● Je override de method run (die je erft van Thread).
48.             ● Je schrijft in deze method de code die je in een thread wil uitvoeren.
49.             De thread uitvoeren:
50.             ● Je maakt een object van je thread class:
51.             var myThread = new MyThread();
52.             ● Je voert op dit object de method start uit.
53.             Deze method vraagt aan het besturingssysteem een nieuwe thread en voert met die thread de
54.             code uit in de method run (van de class MyThread).
55.     ● Als een class die de interface Runnable implementeert.
56.             De class die de thread voorstelt:
57.             ● Je implementeert in de class (bijvoorbeeld MyRunner) de interface Runnable.
58.             ● Je implementeert de method run (gedeclareerd in Runnable).
59.             ● Je schrijft in deze method de code die je in een thread wil uitvoeren.
60.             De thread uitvoeren:
61.             ● Je maakt een object van je eigen class: MyRunner myRunner = new MyRunner();
62.             ● Je maakt een Thread object. Je geef het object van je eigen class mee aan de Thread constructor:
63.             Thread thread = new Thread(myRunner);
64.             ● Je voert op dit Thread object de method start uit. Deze method vraagt aan het
65.             besturingssysteem een nieuwe thread en voert met die thread de code uit in de method run
66.             (van het MyRunner object).
67. 
68.                         De tweede manier wordt aangeraden. Bij de tweede manier kan je nog vrij kiezen om de class te laten
69.                         erven van om het even welke class. Bij de eerste manier kan de class niet meer erven van om het even
70.                         welke class, want de class erft al van Thread. (Class cannot extend multiple classes)
71.                         We zien eerst de (wat eenvoudiger) eerste manier, daarna de tweede manier.
72.                         Bij de cursus horen de bestanden insecten1.csv en insecten2.csv. Beide bestanden bevatten informatie
73.                         over insecten. Één regel bevat de naam en de prijs (gescheiden door een ;) van één insect.
74.                         Je plaatst deze bestanden in een directory \data.
75.                         Je toont in een applicatie de regels met een prijs tot en met 3.
76.                         Je zoekt met één thread in insecten1.csv. Je zoekt tegelijk met een tweede thread in insecten2.csv. Om
77.                         de output van de threads te onderscheiden, stuurt de 1° thread zijn output naar System.out. De IDE
78.                         toont deze output met zwarte letters. De 2° thread stuurt zijn output naar System.err (waar je normaal
79.                         foutberichten naar stuurt).
80. System.err vs System.out
81.     Aangezien we voor het eerst in aanraking komen hier met een nieuwe vorm van output naar console is
82.     het aangewezen om hier ook even kort bij stil te staan. Beide vormen zullen standaard hun output
83.     plaatsen op het systeem waar het gebruikt wordt. In een standaard java applicatie zal dit dus altijd de
84.     console zijn. Het enige verschil dat we in onze IDE zien is dat System.out gewoon standaard zwart
85.     gekleurd is en System.err wordt rood gekleurd.
86.     Wat vaak gebeurt is dat we de errormeldingen niet wegschrijven in console, maar naar een bestand.
87.     Voorbeeld:
88.     System.setErr(new PrintStream("/data/fouten.txt"));
89.     System.err.println("Deze error komt in de file fouten.txt terecht");
90.     Op deze manier kan je ook je standaard output wijzigen naar een bestand. Dit doe je dan als volgt:
91.     System.setOut(new PrintStream("/data/output.txt"));
92. De method join van een Thread object
93.     In Java als je een programma uitvoert wordt dit gedaan in de main thread. Als je naast de main thread
94.     ook nog andere threads wilt aanmaken dan maak je hiervoor objecten aan van de klasse Thread.
95.     Wanneer je in een thread a de method join uitvoert op een thread object b, pauzeert Java de uitvoering
96.     van de thread a tot de method run van het object b helemaal uitgevoerd is.
97.     Dit is noodzakelijk als thread a het eindresultaat van het werk van het thread object b nodig heeft.
98.     Thread a mag het resultaat van het thread object b maar opvragen nadat het thread object b zijn
99.     resultaat volledig aangemaakt heeft. Thread a mag het resultaat van het thread object b nog niet
100.     opvragen als het thread object b zijn resultaat nog aan het opbouwen is.
101.     Je past de applicatie aan. De threads tonen niet de regels met een maximum prijs 3,
102.     maar tellen deze regels. De class Main toont de som van deze twee tellers.
103.     Je voegt aan de class InsectenLezer een private variabele toe: private int aantalRegels;
104.     Je vervangt in de method run de regel stream.println(pad.getFileName() + ":" + regel);
105.     door ++aantalRegels;
106.     Je voegt een method toe:
107.     public int getAantalRegels() {
108.     return aantalRegels;
109.     }
110.     Je vraagt in de class Main het eindresultaat aan beide threads, zonder te wachten tot ze hun werk
111.     gedaan hebben, om te zien dat je dan een verkeerd resultaat krijgt.
112.     Je voegt na de regel thread2.start(); volgende regel toe:
113.     System.out.println(lezer1.getAantalRegels()+lezer2.getAantalRegels()+" regels");
114.     Je voert het programma uit. Je krijgt een verkeerd resultaat. Het juiste resultaat is 6121.
115.     Je lost het probleem nu op. Je voegt na de regel thread2.start(); volgende regels toe:
116.     try {
117.     thread1.join();
118.     thread2.join();
119.     } catch (InterruptedException ex) {
120.     // Het uitvoeren van de join method kan een InterruptedException werpen
121.     // Je ziet hierover meer later in de cursus
122.     System.err.println(ex);
123.     }
124.     Je voert het programma uit en je krijgt wel het juiste resultaat (6121).
125. De static method sleep van de class Thread
126.     Je kan in om het even welke thread de static method sleep van de class Thread oproepen.
127.     Je geeft als parameter een aantal milliseconden mee.
128.     Java zet de thread waarin je deze method oproep doet, evenveel milliseconden op pauze.
129.     Je probeert dit uit in een class Klok. Je toont in die class één keer per seconde de systeemtijd.
130. De method interrupt van een Thread object
131.     Je kan aan een thread aangeven dat je zijn uitvoering wil stoppen door de method interrupt uit te
132.     voeren. Deze method stopt de thread niet, maar doet één van volgende handelingen:
133.     ● Als de thread op pauze staat (tijdens het uitvoeren van Thread.sleep() of het uitvoeren van de
134.     join opdracht op een andere thread), krijgt de thread een InterruptedException.
135.     ● Anders komt de thread in de “interrupted” status. In die status blijft de thread lopen. De thread
136.     kan opvragen of het zich in de interrupted status bevindt, via de static method interrupted van
137.     de class Thread. Deze method geeft true terug als de huidige thread zich in de interrupted
138.     status bevindt. De thread kan dan eventueel gestopt worden.
139.     Je wijzigt de applicatie. Wanneer de gebruiker op Enter drukt, stop je de applicatie.
140.     Je controleert het toetsenbord in de thread van de class Main.
141.     Wanneer de gebruiker op Enter drukt, voer je de method interrupt uit op het thread object.
142.     Je voegt in de class Main na de regel thread.start(); volgende regels toe:
143. 22.7 Daemon threads
144.     Normaal stopt een applicatie pas als al zijn threads hun werk gedaan hebben.
145.     Je kan op een Thread object de method setDaemon(true) uitvoeren. Je maakt van die thread een
146.     daemon thread. Een applicatie kan wél stoppen terwijl daemon threads nog niet gestopt zijn.
147.     Je maakt van de thread die de klok afbeeldt een daemon thread.
148.     Je verwijdert in de method run van de class Klok de if structuur.
149.     Je verwijdert in de method main van de class Main de opdracht thread.interrupt();
150.     Je voegt in de method main na de opdracht var thread = new Thread(klok);
151.     volgende opdracht toe: thread.setDaemon(true);
152.     Je kan de applicatie uitproberen.`