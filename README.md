# GMihai: Nota descriere proiect 10

## Jurnal medical pentru animale - Java SE

Macovei Oana-Georgiana

## Descriere
Scopul aplicatiei este de ca utilizatorii ce detin animale sa aiba la indemana intr-un mod organizat toate informatiile necesare despre acestea, printre care si fisa medicala actualizata chiar de medicul in sine. Utilizatorul va putea adauga informatii de baza, despre vaccinuri, stilul de mancare si un istoric legat de interventii medicale.

Aplicatia va fi pe desktop cu interfata grafica Java Swing si va implementa o baza de date locala SQLite impreuna cu utilizarea DBeaver.
Aplicatia va fi pe desktop cu interfata grafica Java Swing si va implementa o baza de date relationala MySQL impreuna cu utilizarea DBeaver.

## Obiective
* operatii CRUD pentru fiecare sectiune din aplicatie
* rapoarte pentru vizualizarea vaccinurilor, dietei si istoricului medical al unui animal
* cautarea unui animal in functie de specie, rasa, nume
* autentificare si gestionarea conturilor
* salvarea datelor intr-o baza de date locala
* implementarea unei interfete grafice
* implementarea testelor JUnit
* documentare codului prin utilizarea JavaDoc

## Arhitectura

<img width="787" height="563" alt="image" src="https://github.com/user-attachments/assets/218afde6-3799-46c1-990f-b8f32047c1e4" />

* Folosirea arhitecturii Modelv-View-Controller (MCV).

* Clasa Animal are relatie de asociere cu fiecare dintre clasele Vaccin, IstoricMedical, Hrana.
* 'idAnimal' va reprezenta legatura dintre clasa respectiva si clasa Animal.

----------------------------------------------------------------------------
Proiectul adopta modelul Model-View-Controller (MCV) si are in plus inca 3:
  * DAO: Clase pentru interogarea bazei de date
  * Styles: Clasa unitara ce contine metode bazate pe stilizarea aplicatiei

1. Adaugare animal nou
* se introduc informatii despre un animal, precum nume, specie, rasa, varsta greutate, iar aplicatia le salveaza in baza de date SQLite.
## Cerinte Tehnice
  * Java: JDK 17 sau o versiune mai noua
  * IDE: NetBeans Apache
  * Baza de date: Server MySQL
    
## Instalare si Configurare
1. Baza de date:
   * Deschide un client MySQL (DBeaver, HeidiSQL)
   * Ruleaza scriptul `proiect.sql` pentru creearea si popularea tabelelor

2. Proiect Java:
   * Importa proiectul ca **Maven Project** intr-un IDE ce ruleaza Java
   * Ruleaza clasa LoginView.java
  
## Manual de Utilizare
1. Autentificare - Se introduc **username**-ul si **parola** din `proiect.sql`
2. Ecranul principal:
  * Tabelul central contine animalele detinute de user-ul logat
  * Navigarea se face prin butoanele **Vaccinuri**, **Fisa Medicala**, **Hrana** (intai se selecteaza un animal facand click pe un rand din tabel!)
  * Adaugarea / stergerea / editarea animalelor se realizeaza prin butoanele din josul ecranului

3. Rapoarte
* selectarea unui animal va incarca automat datele sale despre vaccinuri, istoricul medical si hrana
* vizualizarea vaccinurilor pentru un animal selectat
* vizualizarea istoricului medical 
* vizualizarea hranei unui animal

4. Configurarea hranei
* adaugarea / stergerea orelor de administrare
3. Vaccinuri si Hrana
   * In panoul **Vaccinuri**, se poate adauga / sterge / edita un vaccin
   * In panoul **Hrana**, se poate configura mancarea si o lista personalizata de ore de administrare a acesteia

4. Fisa Medicala
  * Sectiune dedicata tuturor operatiunilor medicale
  * Atentie! Aici doar medicii pot sa adauge, editeze, sterge, iar stapanii doar sa vizualizeze!
    
### Resurse
Markdown Guide, [Online] Available: https://www.markdownguide.org/basic-syntax/ [accesed: Mar 14, 1706]

Documentatie MySQL - https://dev.mysql.com/doc/

Documentatie Java Oracle - https://docs.oracle.com/javase/8/docs/api/
Documentatie DBeaver - https://dbeaver.com/docs/

Documentatie Java Oracle - https://docs.oracle.com/javase/8/docs/api/

StackOverflow - https://stackoverflow.com/questions
