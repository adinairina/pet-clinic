package service;

import model.Consult;
import model.Pet;
import model.Veterinarian;
import repository.ConsultDao;
import repository.PetDao;
import repository.VeterinarianDao;
import util.ImportCSV;
import java.io.IOException;
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
        System.out.println("\t 0 - Exit");
        System.out.println("\t 1 - Create");
        System.out.println("\t 2 - Update");
        System.out.println("\t 3 - Delete");
        System.out.println("\t 4 - List/Find");
        System.out.println("\t 5 - Import from .csv");
    }

    public static void showOption() throws IOException {
        showMenu();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int option;

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
            case 5:
                importCSV();
                break;
        }
    }

        public static void importCSV () throws IOException {
            System.out.println("\nPress ");
            System.out.println("\t 0 - Exit.");
            System.out.println("\t 1 - Import Veterinarian");
            System.out.println("\t 2 - Import Pet");
            System.out.println("\t 3 - Import Consult");
            System.out.println("\t 4 - return to main menu");

            System.out.println("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int createOption = scanner.nextInt();
            boolean quit = false;

            switch (createOption) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    ImportCSV.importCsvVet();
                    break;
                case 2:
                    ImportCSV.importCsvPet();
                    break;
                case 3:
                    ImportCSV.importCsvConsult();
                    break;
                case 4:
                    showOption();
                    break;
            }
        }

    public static void create() throws IOException{

        System.out.println("\n Press:");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Create Veterinarian");
        System.out.println("\t 2 - Create Pet");
        System.out.println("\t 3 - Create Consult");
        System.out.println("\t 4 - Return to Main Menu");

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
                System.out.println("Please insert veterinarian's last name:");
                String lastName = scanner.next();
                System.out.println("Please insert veterinarian's first name:");
                String firstname = scanner.next();
                System.out.println("Please insert veterinarian's address:");
                String address = scanner.next();
                System.out.println("Please insert veterinarian's speciality:");
                String speciality = scanner.next();

                VeterinarianDao veterinarianDao = new VeterinarianDao();
                Veterinarian veterinarian = new Veterinarian(lastName, firstname, address, speciality);
                veterinarianDao.createVeterinarian(veterinarian);
                System.out.println("The vet with lastName " + lastName + " firstName " + firstname +" address " + address +
                        " and speciality " + speciality + " was successfully created!");
                break;

            case 2:
                System.out.println("Please insert pet's name: ");
                String race = scanner.next();
                System.out.println("Please insert birthdate of the pet:");
                String birthDate = scanner.next();
                System.out.println("Please advise if he's vaccinated:");
                boolean isVaccinated = scanner.nextBoolean();
                System.out.println("Please insert owner's name");
                String ownerName = scanner.next();

                PetDao petDao = new PetDao();
                Pet pet = new Pet(race, birthDate, isVaccinated, ownerName);
                petDao.createPet(pet);
                System.out.println("The pet with race " + race + " with birthdate " + birthDate + " vaccinated " + isVaccinated +
                        " and owner's name " + ownerName + " was successfully created!");
                showOption();
                break;

            case 3:
                System.out.println("Please insert consults date:");
                String consultDate = scanner.next();
                System.out.println("Please insert the description");
                String description = scanner.next();


                VeterinarianDao veterinarianDao1 = new VeterinarianDao();
                PetDao petDao1 = new PetDao();
                System.out.println("Please insert the vet's id:");
                Long idVeterinarian = scanner.nextLong();
                System.out.println("Please insert pet's id");
                Long idPet = scanner.nextLong();

                ConsultDao consultDao = new ConsultDao();
                Consult consult = new Consult(consultDate, description, veterinarianDao1.findByIdVeterinarian(idVeterinarian), petDao1.findByIdPet(idPet));
                consultDao.createConsult(consult);
                showOption();
                break;

            case 4:
                showOption();
                break;
        }
    }

    public static void update() throws IOException{
        int updateOption;

        System.out.println("\n Press");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Update Veterinarian");
        System.out.println("\t 2 - Update Pet");
        System.out.println("\t 3 - Update Consult");
        System.out.println("\t 4 - Return to Main Menu");

        System.out.println("Enter your choice: ");

        Scanner scanner = new Scanner(System.in);
        updateOption = scanner.nextInt();

        switch (updateOption) {
            case 0:
                break;
            case 1:
                System.out.println("Please update veterinarian!");
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                System.out.println("Please insert vet id you want to update:");
                Long idVet = scanner.nextLong();

                Veterinarian veterinarian1 = veterinarianDao.findByIdVeterinarian(idVet);
                System.out.println("\n Press");
                System.out.println("\t 0 - Exit.");
                System.out.println("\t 1 - Update FirstName");
                System.out.println("\t 2 - Update LastName");
                System.out.println("\t 3 - Update Address");
                System.out.println("\t 4 - Update Speciality");
                System.out.println("\t 5 - Return to Update Menu");
                System.out.println("\t 6 - Return to Main Menu");

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
                        showOption();
                        break;
                }
                break;

            case 2:
                System.out.println("Please update pet!");
                PetDao petDao = new PetDao();
                System.out.println("Please insert pet id  you want to update:");
                Long idPet = scanner.nextLong();
                Pet pet = petDao.findByIdPet(idPet);

                System.out.println("\n Press");
                System.out.println("\t 0 - Exit.");
                System.out.println("\t 1 - Update Race");
                System.out.println("\t 2 - Update BirthDate");
                System.out.println("\t 3 - Update isVaccinated");
                System.out.println("\t 4 - Update OwnerName");
                System.out.println("\t 5 - Return to Update Menu");
                System.out.println("\t 6 - Return to Main Menu");

                System.out.println("\t Enter your choice: ");
                int updatePet = scanner.nextInt();

                switch (updatePet) {
                    case 0:
                        showOption();
                    case 1:
                        System.out.println("Please insert pet race");
                        pet.setRace(scanner.next());
                        petDao.updatePet(pet);
                        update();
                        break;
                    case 2:
                        System.out.println("Please insert pet's birthdate!");
                        pet.setBirthDate(scanner.next());
                        petDao.updatePet(pet);
                        update();
                        break;
                    case 3:
                        System.out.println("Please advise if he's vaccinated");
                        pet.setVaccinated(scanner.nextBoolean());
                        petDao.updatePet(pet);
                        update();
                        break;
                    case 4:
                        System.out.println("Please insert pet's owner");
                        pet.setOwnerName(scanner.next());
                        petDao.updatePet(pet);
                        update();
                        break;
                    case 5:
                        update();
                        break;
                    case 6:
                        showOption();
                        break;
                }
                break;

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
                        showOption();
                        break;
                } break;
        }
    }

    public static void delete() throws IOException {
        int deleteOptions;

        System.out.println("\n Press");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Delete Veterinarian");
        System.out.println("\t 2 - Delete Pet");
        System.out.println("\t 3 - Delete Consult");
        System.out.println("\t 4 - Return to Main Menu");

        System.out.println("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        deleteOptions = scanner.nextInt();

        switch (deleteOptions) {
            case 0:
                showOption();
            case 1:
                System.out.println("Please delete veterinarian!");
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                System.out.println("Please insert vet id you want to delete:");
                long idVeterinarian = scanner.nextLong();

                Veterinarian veterinarian = veterinarianDao.findByIdVeterinarian(idVeterinarian);
                veterinarianDao.deleteVeterinarian(veterinarian);
                delete();
                break;
            case 2:
                System.out.println("Please delete pet!");
                PetDao petDao = new PetDao();
                System.out.println("Please insert pet id you want to delete");
                long idPet = scanner.nextLong();

                Pet pet = petDao.findByIdPet(idPet);
                petDao.deletePet(pet);
                delete();
                break;
            case 3:
                System.out.println("Please delete consult!");
                ConsultDao consultDao = new ConsultDao();
                System.out.println("Please insert consult id you want to delete");
                Long idConsult = scanner.nextLong();

                Consult consult = consultDao.findByConsultId(idConsult);
                consultDao.deleteConsult(consult);
                delete();
                break;
        }
    }

    public static void find() throws IOException{
        int findOption;

        System.out.println("\n Press");
        System.out.println("\t 0 - Exit.");
        System.out.println("\t 1 - Find Veterinarian");
        System.out.println("\t 2 - Find Pet");
        System.out.println("\t 3 - Find Consult");
        System.out.println("\t 4 - Find by Name");

        System.out.println("Enter your choice: ");
        Scanner scanner = new Scanner(System.in);
        findOption = scanner.nextInt();

        switch (findOption) {
            case 0:
                showOption();

            case 1:
                System.out.println("Please insert vet id you want to select!");
                VeterinarianDao veterinarianDao = new VeterinarianDao();
                long idVeterinarian = scanner.nextLong();
                Veterinarian veterinarian = veterinarianDao.findByIdVeterinarian(idVeterinarian);
                System.out.println(veterinarian.toString());
                showOption();
                break;
            case 2:
                System.out.println("Please insert pet id you want to select!");
                PetDao petDao = new PetDao();
                long idPet = scanner.nextLong();
                Pet pet = petDao.findByIdPet(idPet);
                System.out.println(pet.toString());
                showOption();
                break;
            case 3:
                System.out.println("Please insert consult id you want to select!");
                ConsultDao consultDao = new ConsultDao();
                long idConsult = scanner.nextLong();
                Consult consult = consultDao.findByConsultId(idConsult);
                System.out.println(consult.toString());
                showOption();
                break;
            case 4:  //add in menu
                System.out.println("Please insert vet name you want to select!");
                VeterinarianDao veterinarianDao1 = new VeterinarianDao();
                String firstName = scanner.nextLine();
                List<Veterinarian> veterinarians = veterinarianDao1.findByNameVet(firstName);
                System.out.println(veterinarians.toString());
                showOption();
                break;
        }
    }
}
