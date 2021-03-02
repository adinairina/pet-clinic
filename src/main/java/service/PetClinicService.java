package service;

import model.Consult;
import model.Pet;
import model.Veterinarian;
import repository.ConsultDao;
import repository.PetDao;
import repository.VeterinarianDao;

import javax.persistence.SharedCacheMode;
import java.util.List;
import java.util.Scanner;

/**
 * 1.1 Introduceti un constraint pe campurile firstname, lastname din clasa Veterinarian case sa specifice ca
 * lungimea minima a strigului este de 3 caractere.2. Implementati o interfata la consola pentru applicatia pet clinic.
 * Va afisa un meniu cu urmatoarele optiuni:
 * 0. Exit
 * 1. Create
 * 2. Update
 * 3. Delete
 * 4. List/Find
 * Dupe selectia oricarei option utilizatorul va alege tabelul pentru care doreste sa faca aceasta operatie.
 * •	In cazul unui create utilizatorul va trebui sa introduca de la tastatura toare campurile necesare pentru
 * crearea acelei entitati.
 * •	In cazul unui update utilizatorul va selecta ce camp doreste sa updateze si va introduce noua valoare.
 * •	In cazul unui delete utilizatorul va introduce id-ul entitatii ce doreste sa o stearga.
 * •	In cazul unui list/find utilizatorul poate alege sa afiseze toate intrarile din db sau sa introduca id-ul
 * entitatii ce doreste sa o afiseze.
 */

public class PetClinicService {

    public static void showMenu() {
        System.out.println("\nPress ");
        System.out.println("\t 0 - Print choice options.");
        System.out.println("\t 1 - Create");
        System.out.println("\t 2 - Update");
        System.out.println("\t 3 - Delete");
        System.out.println("\t 4 - List/Find");
    }

    public static void showOption(int option) {

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        //int option
        System.out.println("Enter your choice: ");
        option = scanner.nextInt();

        switch (option) {
            case 0:
                quit = true;
                break;
            case 1:
                create();
                break;
            case 2:
                update();
                break;
            case 3:
                delete();
                break;
            case 4:
                find();
                break;
        }
    }

    public static void showCreateMenu() {
        System.out.println("\t 0 - Veterinarian");
        System.out.println("\t 1 - Pet");
        System.out.println("\t 2 - Consult");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        System.out.println("Ai ales optiunea: " + option);


    }

    public static void create() {

        System.out.println("\n Press");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Create Veterinarian");
        System.out.println("\t 2- Create Pet");
        System.out.println("\t 3- Create Consult");
        System.out.println("\t 4- Return to Main Menu");

        System.out.println("Enter your choice: ");
        int createOption;
        Scanner scanner = new Scanner(System.in);
        createOption = scanner.nextInt();
        boolean quit = false;

        switch (createOption) {
            case 0:
                quit = true;
                break;
            case 1:
                System.out.println("Introduceti numele veterinarului:");
                String lastName = scanner.next();
                System.out.println("Introduceti prenumele:");
                String firstname = scanner.next();
                System.out.println("Introduceti adresa:");
                String address = scanner.next();
                System.out.println("Introduceti specialitatea:");
                String speciality = scanner.next();

                VeterinarianDao veterinarianDao = new VeterinarianDao();
                Veterinarian veterinarian = new Veterinarian(lastName, firstname, address, speciality);
                veterinarianDao.createVeterinarian(veterinarian);
                break;

            case 2:
                System.out.println("Introduceti rasa animalului:");
                String race = scanner.next();
                System.out.println("Introduceti data nasterii:");
                String birthDate = scanner.next();
                System.out.println("Introduceti daca este vaccinat sau nu:");
                boolean isVaccinated = scanner.nextBoolean();
                System.out.println("Introduceti numele proprietarului:");
                String ownerName = scanner.next();


                PetDao petDao = new PetDao();
                Pet pet = new Pet(race, birthDate, isVaccinated, ownerName);
                petDao.createPet(pet);
                break;

            case 3:
                System.out.println("Introduceti data consultatiri:");
                String consultDate = scanner.next();
                System.out.println("Introduceti descrierea:");
                String description = scanner.next();


                VeterinarianDao veterinarianDao1 = new VeterinarianDao();
                PetDao petDao1 = new PetDao();
                System.out.println("Introduceti id-ul veterinarului:");
                Long idVeterinarian = scanner.nextLong();
                System.out.println("Introduceti id-ul animalului");
                Long idPet = scanner.nextLong();

                ConsultDao consultDao = new ConsultDao();
                Consult consult = new Consult(consultDate, description, veterinarianDao1.findByIdVeterinarian(idVeterinarian), petDao1.findByIdPet(idPet));
                consultDao.createConsult(consult);
                break;

            case 4:
                System.out.println("Enter your choice");
                showMenu();
                int x = scanner.nextInt();
                showOption(x);
                break;
        }
    }

    public static void update() {

        int updateOptions;

        System.out.println("\n Press");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Update Veterinarian");
        System.out.println("\t 2- Update Pet");
        System.out.println("\t 3- Update Consult");
        System.out.println("\t 4- Return to Main Menu");

        Scanner scanner = new Scanner(System.in);
        updateOptions = scanner.nextInt();


        System.out.println("Enter your choice: ");

        switch (updateOptions) {
            case 0:
                return;
            case 1:
                System.out.println("Please update veterinarian!");
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                System.out.println("please insert vet id which you want to update:");
                Long idVet = scanner.nextLong();

                Veterinarian veterinarian1 = veterinarianDao.findByIdVeterinarian(idVet);
                System.out.println("\n Press");
                System.out.println("\t 0 - Exit.");
                System.out.println("\t 1 - Update FirstName");
                System.out.println("\t 2- Update LastName");
                System.out.println("\t 3- Update Address");
                System.out.println("\t 4- Update Speciality");
                System.out.println("\t 5- Return to Update Menu");
                System.out.println("\t 6- Return to Main Menu");

                System.out.println("\t Enter your choice: ");
                int updateVeterinarian = scanner.nextInt();

                switch (updateVeterinarian) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Please insert veterinarian's first name");
                        veterinarian1.setFirstName(scanner.next());
                        veterinarianDao.updateVeterinarian(veterinarian1);
                        break;

                    case 2:
                        System.out.println("Please insert veterinarian's last name");
                        veterinarian1.setLastName(scanner.next());
                        veterinarianDao.updateVeterinarian(veterinarian1);
                        break;
                    case 3:
                        System.out.println("Please insert address of the veterinarian");
                        veterinarian1.setAddress(scanner.next());
                        veterinarianDao.updateVeterinarian(veterinarian1);
                        break;
                    case 4:
                        System.out.println("Please insert the speciality for the veterinarian");
                        veterinarian1.setSpeciality(scanner.next());
                        veterinarianDao.updateVeterinarian(veterinarian1);
                        break;
                    case 5:
                        update();
                        break;
                    case 6:
                        System.out.println("Return to Update Menu");
                        showMenu();
                        int x = scanner.nextInt();
                        showOption(x);
                        break;
                }
            case 2:
                System.out.println("Please update pet!");
                PetDao petDao = new PetDao();
                System.out.println("please insert vet id  you want to update:");
                Long idPet = scanner.nextLong();

                Pet pet1 = petDao.findByIdPet(idPet);
                System.out.println("\n Press");
                System.out.println("\t 0 - Exit.");
                System.out.println("\t 1 - Update Race");
                System.out.println("\t 2- Update BirthDate");
                System.out.println("\t 3- Update IsVaccinated");
                System.out.println("\t 4- Update OwnerName");
                System.out.println("\t 5- Return to Update Menu");
                System.out.println("\t 6- Return to Main Menu");

                System.out.println("\t Enter your choice: ");
                int updatePet = scanner.nextInt();

                switch (updatePet) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Please insert pet's race");
                        pet1.setRace(scanner.next());
                        petDao.updatePet(pet1);
                        break;

                    case 2:
                        System.out.println("Please insert date of birth");
                        pet1.setBirthDate(scanner.next());
                        petDao.updatePet(pet1);
                        break;
                    case 3:
                        System.out.println("Please insert if is vaccinated or not");
                        pet1.setVaccinated(scanner.nextBoolean());
                        petDao.updatePet(pet1);
                        break;
                    case 4:
                        System.out.println("Please insert the owner");
                        pet1.setOwnerName(scanner.next());
                        petDao.updatePet(pet1);
                        break;
                    case 5:
                        update();
                        break;
                    case 6:
                        System.out.println("Return to Update Menu");
                        showMenu();
                        int x = scanner.nextInt();
                        showOption(x);
                        break;
                }

            case 3:
                System.out.println("Please update consult!");
                ConsultDao consultDao = new ConsultDao();
                System.out.println("please insert consult id you want to update:");
                Long idConsult = scanner.nextLong();

                Consult consult1 = consultDao.findByConsultId(idConsult);
                System.out.println("\n Press");
                System.out.println("\t 0 - Exit.");
                System.out.println("\t 1 - Update Date");
                System.out.println("\t 2 - Update Description");
                System.out.println("\t 3 - Return to Update Menu");
                System.out.println("\t 4 - Return to Main Menu");

                System.out.println("\t Enter your choice: ");
                int updateConsult = scanner.nextInt();

                switch (updateConsult) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Please insert consult date");
                        consult1.setDate(scanner.next());
                        consultDao.updateConsult(consult1);
                        break;

                    case 2:
                        System.out.println("Please insert description");
                        consult1.setDescription(scanner.next());
                        consultDao.updateConsult(consult1);
                        break;

                    case 3:
                        update();
                        break;
                    case 4:
                        System.out.println("Return to Update Menu");
                        showMenu();
                        int x = scanner.nextInt();
                        showOption(x);
                        break;
                }
        }
    }

    public static void delete() {
        int deleteOptions;

        System.out.println("\n Press");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Delete Veterinarian");
        System.out.println("\t 2 - Delete Pet");
        System.out.println("\t 3 - Delete Consult");
        System.out.println("\t 4 - Return to Main Menu");

        Scanner scanner = new Scanner(System.in);
        deleteOptions = scanner.nextInt();


        System.out.println("Enter your choice: ");

        switch (deleteOptions) {
            case 0:
                return;

            case 1:
                System.out.println("Please delete veterinarian!");
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                System.out.println("Please insert vet id  you want to delete:");
                Long idVeterinarian = scanner.nextLong();

                Veterinarian veterinarian = veterinarianDao.findByIdVeterinarian(idVeterinarian);
                veterinarianDao.deleteVeterinarian(veterinarian);

            case 2:
                System.out.println("Please delete pet!");
                PetDao petDao = new PetDao();
                System.out.println("Please insert pet id you want to delete");
                Long idPet = scanner.nextLong();

                Pet pet = petDao.findByIdPet(idPet);
                petDao.deletePet(pet);

            case 3:
                System.out.println("Please delete consult!");
                ConsultDao consultDao = new ConsultDao();
                System.out.println("Please insert consult id you want to delete");
                Long idConsult = scanner.nextLong();

                Consult consult = consultDao.findByConsultId(idConsult);
                consultDao.deleteConsult(consult);
        }
    }

    public static void find() {
        int findOption;

        System.out.println("\n Press");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Find Veterinarian");
        System.out.println("\t 2 - Find Pet");
        System.out.println("\t 3 - Find Consult");
        System.out.println("\t 4 - Return to Main Menu");

        Scanner scanner = new Scanner(System.in);
        findOption = scanner.nextInt();


        System.out.println("Enter your choice: ");

        switch (findOption) {
            case 0:
                return;

            case 1:
                System.out.println("Introduceti id-ul veterinarului pe care vreti sa-l afisati!");
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                Long idVeterinarian = scanner.nextLong();
                Veterinarian veterinarian = veterinarianDao.findByIdVeterinarian(idVeterinarian);
                System.out.println(veterinarian.toString());
                break;
            case 2:
                System.out.println("Introduceti id-ul animalului pe care vreti sa-l afisati!");
                PetDao petDao = new PetDao();
                Long idPet = scanner.nextLong();
                Pet pet = petDao.findByIdPet(idPet);
                System.out.println(pet.toString());
                break;
            case 3:
                System.out.println("Introduceti id-ul consultului pe care vreti sa-l afisati!");
                ConsultDao consultDao = new ConsultDao();
                Long idConsult = scanner.nextLong();
                Consult consult = consultDao.findByConsultId(idConsult);
                System.out.println(consult.toString());
                break;
            case 4:  //add in menu
                System.out.println("Introduceti numele vet pe care vreti sa-l afisati!");
                VeterinarianDao veterinarianDao1 = new VeterinarianDao();
                String firstName = scanner.nextLine();
                List<Veterinarian> veterinarians = veterinarianDao1.findByNameVet(firstName);
                System.out.println(veterinarians.toString());
                break;
        }
    }
}
