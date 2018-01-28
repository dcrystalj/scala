# Naloge v devetem tednu

V devetem tednu se boste lotili streaminga twitterja in nekaterih osnovnih analiz twitter toka.

## Priprava terena

 1. Ustvarite si twitter račun (če ga še nimate)
 2. Pojdite na https://dev.twitter.com/apps in se prijavite v svoj TW račun
 3. Kliknite na "Create new application"
 4. Izpolnite formo (za spletno stran lahko date kar nekaj poljubnega)
 5. V zavihku "API Keys"  kliknite na "Create my access token."

Tako boste dobili 4 "ključe", ki jih boste potrebovali za nalogo.
V datoteki Main.scala (v razredu Util) na štiri mesta skopirajte ustrezne ključe.
*Pred oddajo na učilnico pa te ključe obvezno pobrišite.*

Kot primer ene aplikacije imate implementiran razred 'NalogaDemo', ki ga v sbt-ju zaženete z:
```
run-main NalogaDemo
```
Ta razred naj vam služi kot kalup za implementacijo nalog. Za vsako nalogo boste implementirali en tak "izvršljiv" razred. Funkcionalnost pa boste v vsaki nalogi implementirali z razredom, ki bo implemetiral 'TweetManipulator' - za demo nalogo je to 'TrivialManipulator', ki vsak tweet preprosto izpiše.


## Naloga 1.

 Napišite manipulator, ki bo imel implementirano funkcijo
 ```scala
 def isEnglish(tweet:List[String]):Boolean
 ```
 ki vzame seznam besed (ki so v enem tweetu) in vrne true, če je več kot 50% besed iz angleškega jezika in false sicer.

 V direktoriju 'resources' imate datoteko 'wordsEn.txt', ki vsebuje vse (no, mogoče ne vseh, ampak veliko pa vseeno) angleške besede. Te besede preberite kot seznam in ga uporabite znotraj funkcije.

Branje datoteke implementirajte s pomočjo razreda Source. Nekaj navodil in primerov uporabe tega razreda in branja iz datotek najdete na:
http://www.tutorialspoint.com/scala/scala_file_io.htm

In še specifikacija objekta Source:
http://www.scala-lang.org/api/2.11.5/index.html#scala.io.Source$

Ta manipulator naj izpiše tweete, če so zapisani v angleškem jeziku.


## Naloga 2.

Napišite manipulator, ki bo zbiral frekvence pojavnosti besed v toku tweetov.

Izpišite 100 najpogostejših besed v angleških tweetih v zadnjih petih minutah.
Izpišite samo tiste besede, ki niso med najpogostejšimi v angleškem jeziku in so dolge vsaj 3 znake. Ponovno je v direktoriju 'resources' datoteka 'commonEng.txt', v kateri so shranjene najpogostejše besede v angleškem jeziku. Teh besed ne štejte v frekvenci.


## Naloga 3. :crown:

Napišite manipulator, ki bo vodil trenutno vzdušje na tweeterju.

Za vsak tweet izračunajte njegovo vzdušje (sentiment). Vzdušje v enem tweetu je določeno z vsoto sentimentov posameznih besed. V direktoriju 'resources' imate datoteko 'sentiment.txt', ki hrani seznam angleških besed, poleg vsake pa je podan še njen sentiment (negativne vrednosti so negativne besede, pozitivne vrednosti so pozitivne besede).

Vsako minuto izpišite trenutno vzdušje na twitterju, izpišite najbolj pozitiven tweet in najbolj negativen tweet.
