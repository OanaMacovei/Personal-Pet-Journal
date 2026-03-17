# Pet Journal App

Desktop application built with Java and MCV architecture that serves as a bridge between pet owners and veterinary clinics. It combines a medical database made with Docker and Dbeaver with an intuitive UI for owners to easily keep track of their pets' information and also be in touch directly with the assigned doctor.

## Functionalities
 * Clinic Management: Role-Based Management for pet owners and medical staff.
 * Medical Journaling: CRUD operations for pet profiles, medical and vaccination entries.
 * Data Integrity: All data is securely handled via JDBC and stored in a MySQL database.
 * Dockerized Environment: The database is containerized via Docker so that it is ensured that the app works out-of-the-box without manual SQL setup.
 * Data Integrity: Optimized relational schema to prevent data loss and ensure consistent medical records.

## How to install
 * Prerequisites: Ensure you have Java JDK 17+ and Docker Desktop installed.
 * Setup:
    * Clone the repository.
    * In the root folder, run docker-compose up -d to start the database.
    * Run the `proiect.sql` script for populating the database.
    * Run: Open the project in NetBeans/IntelliJ and press the Run button.

## How to use
 * Authentication:
   * Log in as a doctor to manage clinical records or as an owner to view your pets' medical history.

 * Managing Pets
   * Owners can add new pets with the necessary details, search for their existing pets, edit if somewhere happaned a type and delete. 
   * Click on a pet to see their full information data structued as vaccines, medical entry and meal program.

 * Clinical Actions (Doctor Mode)
   * Consultations: Doctors can add new entries to a pet's journal, which are instantly visible to the owner.
