## Jurnal medical pentru animale - Java SE

Macovei Oana-Georgiana

## Descriere
Scopul aplicatiei este de a avea la indemana intr-un mod organizat toate informatiile necesare pentru animal. Utilizatorul va putea adauga informatii de baza, despre vaccinuri, stilul de mancare si un istoric legat de interventii medicale.

Aplicatia va fi pe desktop cu interfata grafica JavaFX si va implementa o baza de date locala SQLite

## Obiective

* operatii CRUD pentru fiecare sectiune din aplicatie
* rapoarte pentru vizualizarea vaccinurilor si istoricul medical al unui animal
* cautarea unui animal in functie de specie, rasa, nume
* salvarea datelor intr-o baza de date locala
* implementarea unei interfete grafice
* implementarea testelor JUnit
* documentare codului prin utilizarea JavaDoc

## Arhitectura

<img width="787" height="563" alt="image" src="https://github.com/user-attachments/assets/218afde6-3799-46c1-990f-b8f32047c1e4" />

* Clasa Animal are relatie de asociere cu fiecare dintre clasele Vaccin, IstoricMedical, Hrana
* 'idAnimal' va reprezenta legatura dintre clasa respectiva si clasa Animal

## Functionalitati/Exemple utilizare
Cateva din functionalitati sunt:

1. Adaugare animal nou
* se introduc informatii despre un animal, precum nume, specie, rasa, varsta greutate, iar aplicatia le salveaza in baza de date SQLite

2. Cautare
* se poate cauta un animal dupa nume, specie, rasa, iar rezultatele se actualizeaza in lista

3. Rapoarte
* vizualizarea vaccinurilor pentru un animal selectat
* vizualizarea istoricului medical 
* vizualizarea hranei unui animal
### Resurse
Markdown Guide, [Online] Available: https://www.markdownguide.org/basic-syntax/ [accesed: Mar 14, 1706]

Documentatie SQLite - https://sqlite.org/docs.html

Documentatie JavaFX - https://openjfx.io
