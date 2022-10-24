22.3.3 Een class die de interface Runnable implementeert
De class die de thread voorstelt:
● Je implementeert in de class (bijvoorbeeld MyRunner) de interface Runnable.
● Je implementeert de method run (gedeclareerd in Runnable).
● Je schrijft in deze method de code die je in een thread wil uitvoeren.
De thread uitvoeren:
● Je maakt een object van je eigen class: MyRunner myRunner = new MyRunner();
206
Cursus Java Programming Fundamentals
● Je maakt een Thread object. Je geef het object van je eigen class mee aan de Thread constructor:
Thread thread = new Thread(myRunner);
● Je voert op dit Thread object de method start uit. Deze method vraagt aan het
besturingssysteem een nieuwe thread en voert met die thread de code uit in de method run
(van het MyRunner object).
Je probeert dit uit.