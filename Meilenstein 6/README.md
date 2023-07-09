# Meilenstein 6

## Inhalt

*  [Usability Test](#usability-test)
*  [Design Review](#design-review)
*  [Evalutation](#evaluation)
*  [Liste der Verbesserungen](#liste-der-verbesserungen)

## Usability Test

Testgegenstand: MyCook-App

Testdatum: 08.07.2023

Entwickler: Wroblewski, Junker, Köhl, Putranto

Test-Moderatoren: Wroblewski

Test-Benutzer: Galeyev

**Test Aufgaben**

1. Navigieren Sie zwischen den 3 Bildschirmen mithilfe der Menüleiste.
(max. 1 Minute)
- 10s, Selsbtändig und intuitiv durch das Menü gegangen mit nur 3 Klicks.

2. Sie haben keine Idee, was sie heute kochen möchten und wollen sich inspirieren
lassen. (max. 3 Minuten)
- 9s, direkt auf den Recommended Screen gegangen mit 1 Klick. 

3. Sie öffnen ein Rezept, es gefällt ihnen jedoch nicht und sie wollen auf den
vorherigen Screen zurückkehren. (max. 1 Minute)
- 11s, zwei swipes gebraucht, da es bei iOS normalerweise in die andere Richtung für "zurück" ist. 

4. Sie haben ein Rezept gefunden, was sie öfters kochen möchten, jedoch ohne es
erneut suchen zu müssen. (max. 1 Minute)
- 5s, ist selbstständig auf ein Rezept gegangen und hat es als Favorit gespeichert. 

5. Sie wollen wieder auf den Favoriten Bildschirm zurückkehren. (max. 1 Minuten)
- 11s, Intuitiv durch swipen zurückgegangen. (wieder gleiches problem wie bei 3.)

6. Sie tippen auf den „Recommended“-Button und wollen aber nur Gerichte mit der
Zutat „Chicken“ angezeigt bekommen. (max. 2 Minuten)
- 17s, die Suchfunktion direkt genutzt allerdings hat das Tippen auf dem Emulator etwas gedauert daher 17s. 

7. Ihnen schmeckt ein Rezept im Favoriten Bildschirm nicht mehr, also wollen sie es
entfernen. (max. 1 Minute)
- 5s, gleiche wie bei der 4., selbstsändig durch einen Klick auf das Icon das Rezept aus den Favoriten entfernt. 

8. Sie haben ein eigenes (Beispiel-) Rezept und wollen es hinzufügen.
(max. 4 Minuten)
- 70s, dies hat gut geklappt bis ein Bild Vorrausgesetzt wurde und dies nicht direkt ersichtlich war, warum das Rezept nicht erstellt werden konnte. 

9. Ihre Inventarliste ist leer, jedoch haben sie bereits einige Vorräte daheim, die sie
eintragen möchten. (max. 2 Minuten)
- <120s, ohne Hilfestellung nicht hinbekommen, da dies eine Funktion ist die meist durch swipen oder ähnliches durchgeführt wird. 

10. Sie merken, dass ihnen für ein Rezept noch Zwiebeln fehlen. (max. 2 Minuten)
- 19s, wenige Klicks, direkt gefunden nur das schreiben hat gedauert. 

11. Sie haben aus Versehen Knoblauch ihrer Shoppinglist hinzugefügt, obwohl sie es
nicht brauchen und möchten es wieder entfernen. (max. 2 Minuten)
- 58s, tatsächlich hier ähnlich wie bei der 9. da es so "ungewohnt" war, dass es direkt wieder vergessen wurde bis nach einiger Zeit wieder das drücke auspürobiert wurde.

12. Sie waren Einkaufen, haben alle Zutaten auf ihrer Shoppinglist gekauft und
möchten diese dem Inventar hinzufügen. (max. 3 Minuten)
- 9s, durch die Checkboxen einfach ersichtlich und die Bennenung des unteren Knopfs.

13. Sie haben das Rezept gekocht und dabei alle Zwiebeln verbraucht. (mx. 2 Minuten)
- 11s, wieder entfernen allerdings diesmal ohne Probleme. 

Die Aufgabenplan mit Voraussetzungen und Erledigungskriterien finden Sie [hier](https://code.fbi.h-da.de/human-computer-interaction/HCI_SS23_Meyer/HCI_SS23_Meyer_Gruppe3-Junker_Kohl_Putranto_Wroblewski/-/blob/main/Meilenstein%206/Aufgabenplan.pdf)

### Effektifität

- Aufgabe wurde direkt verstanden und erfolgreich gelöst
- - Bis auf die 8, 9 und die 11
- Aufgabe wurde erst nach Fehlversuchen gelöst
- - 8 und 11
- Aufgabe konnte nur mit Hilfestellung gelöst werden:
- - 9
    - Bei jeder Aufgabe steht eine Erwartete Zeitangabe, wird diese überschritten

    - gilt die Aufgabe als nicht gelöst und es wird eine Hilfestellung gegeben.


### Effizienz

1. Durchführungszeit der verschiedenen Aufgaben:

    - Bei jeder Aufgabe wird die gebrauchte Zeit des Benutzers gemessen

2. Anzahl der Aktionen des Benutzers: 
    
    - Zählen der Klicks / Aktionen des Benutzers → weniger = effizientere Benutzererfahrung.

3. Benutzerfreundlichkeit: 

    - Ist die Benutzeroberfläche klar verständlich? 
    Ja

    - Sind alle Buttons und Aktionen leicht nachzuvollziehen? 
    Ja
    
    - Benötigt der Benutzer Hilfe, um die App effizient zu nutzen?
    Nein

## Design Review

1. Schrift gut lesbar?
    - Alles war gut lesbar.
    Punkte: 9/10

2. Icons gut erkennbar und Funktion verständlich?
    - Funktionen der einzelnen Icons wie zmb. Favorit waren verständlich und wurden intuitiv genutzt.
    Punkte: 10/10

3. Farbgebung
    - Farbgebung der App ist angenehm wobei das Icon mit dem Rot nicht so zu dem Lila der App passt.
    Punkte: 8/10

4. Größe der Bedienelemente
    - Die Größe der Bedienelemente war so, dass diese ohne Probleme genutzt werden konnten.
    Punkte: 9/10

5. Sinnvolle Gruppierung der Bedienelemente?
    - Ja, wobei man auch überlegen könnte den Recommended Teil als vierte Option für die Navigationsleiste zu gestalten.
    Punkte: 8/10

6. Abstände und Ränder
    - Gut nur der Unterschied der Buttonbreite und der Eingabefelder zwischen den Screens ist leicht störend.
    Punkte: 7/10

7. Sinnvolles Verhalten beim Drehen des Smartphones (keine Zustandsänderung, kein Abbruch von Abläufen)
    - Keine Änderung, ist geplant für einhändige benutzung beim kochen, daher Ja.
    Punkte: 10/10


    Gesamt: 61/70


## Liste der Verbesserungen

1. Icon-Farbe verändern der App anpassen.
2. Das Delete durch ein Swipe oder durch eine Checkbox erstetzen, da das lange drücken nicht oft benutzt wird.
3. Recommended besser Sichtbar machen, da man zum Beispiel aus dem Inventory Screen nicht direkt auf diesen zugreifen kann.
4. Eine Funktion einfügen durch die man das "Rezept erstellen" abbrechen kann, ohne das man das Rezept fertig stellt oder die App beenden muss.
5. Design vereinheitlichen in Sprache sowie Breite/Höhe der einzelnen Felder wie zum Beispiel der Buttons
