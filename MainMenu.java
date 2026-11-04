import java.util.ArrayList;
import java.util.Scanner;
public class MainMenu {
    private static final Shelter shelter = new Shelter();
    private static final Scanner sc = new Scanner(System.in);
    private static final AdoptionService adoptionService =
            new AdoptionService(shelter);
    private static final ArrayList<Adopter> adopters = new ArrayList<>();
    public static void main(String[] args) {


        shelter.loadSampleData();
        boolean running = true;
        //Keep showing the menu until the user exits


        while (running) {
            showMenu();
            System.out.print("Enter your choice: ");


            if (!sc.hasNextInt()) {
                System.out.println("Please enter a number.");
                sc.nextLine();
                continue;
            }
           
            int choice = sc.nextInt();
            sc.nextLine();


            switch (choice) {
                // Execute the selected menu option
                case 1:
                    viewAllAnimals();
                    break;
                case 2:
                    searchAnimal();
                    break;
                case 3:
                    registerAdopter();
                    break;
                case 4:
                    submitApplication();
                    break;
                case 5:
                    reviewApplications();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }


        sc.close();
    }
    public static void showMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("૮ • ﻌ - ა Animal Adoption System 𑄝੭");
        System.out.println("========================================");
        System.out.println("1. View All Animals");
        System.out.println("2. Search Animals");
        System.out.println("3. Register Adopter");
        System.out.println("4. Submit Adoption Request");
        System.out.println("5. Review Adoption Requests");
        System.out.println("6. Exit");
    }


    public static void viewAllAnimals() {
        // Display all animals in the shelter
        shelter.displayAllAnimals();
    }


    public static void searchAnimal() {
        System.out.print("Enter animal ID or name: ");
        String keyword = sc.nextLine();
        First found = shelter.searchAnimal(keyword);
        if (found == null) { System.out.println("Animal not found."); } else { found.displayInfo(); }
    }


    public static void registerAdopter() {
        System.out.print("Enter adopter ID: ");
        String id = sc.nextLine();
        for (Adopter adopter : adopters) {
            if (adopter.getAdopterId().equalsIgnoreCase(id)) {
                System.out.println("Adopter ID already exists.");
                return;
            }
        }
        System.out.print("Enter name: ");
        String name = sc.nextLine();


        System.out.print("Enter phone: ");
        String phone = sc.nextLine();


        adopters.add(new Adopter(id, name, phone));
        System.out.println("Adopter registered successfully.");
    }


    public static void submitApplication() {
        // Submit a new adoption application
        if (adopters.isEmpty()) {
            System.out.println("Please register an adopter first.");
            return;
        }


        System.out.print("Enter adopter ID: ");
        String adopterId = sc.nextLine();


        Adopter selectedAdopter = null;


        // Find the adopter by id
        for (Adopter adopter : adopters) {
            if (adopter.getAdopterId().equals(adopterId)) {
                selectedAdopter = adopter;
                break;
            }
        }


        if (selectedAdopter == null) {
            System.out.println("Adopter not found.");
            return;
        }


        System.out.print("Enter animal ID: ");
        int animalId = readInt();


        // Find the selected animal
        First selectedAnimal =
                shelter.searchAnimal(String.valueOf(animalId));


        // Let AdoptionService handle the application
        AdoptionApplication application =
                adoptionService.submitApplication(selectedAdopter, selectedAnimal);


        if (application == null) {
            System.out.println("Animal not found or unavailable.");
            return;
        }


        System.out.println("Application submitted successfully.");
        application.displayInfo();
    }


    public static void reviewApplications() {
        if (shelter.getAllApplications().isEmpty()) {
            System.out.println("No adoption applications.");
            return;
        }


        shelter.displayAllApplications();


        System.out.print("Enter application ID to review: ");
        String id = sc.nextLine();


        AdoptionApplication selected =
                adoptionService.findApplication(id);


        if (selected == null) {
            System.out.println("Application not found.");
            return;
        }


        if (!selected.getStatus().equals("Pending")) {
            System.out.println("This application has already been reviewed.");
            return;
        }


        System.out.print("Approve or reject? (A/R): ");
        String decision = sc.nextLine();


        // Process the decision through AdoptionService
        if (decision.equalsIgnoreCase("A")) {
            if (adoptionService.approveApplication(id)) {
                System.out.println("Application approved.");
            } else {
                System.out.println("Could not approve application.");
            }


        } else if (decision.equalsIgnoreCase("R")) {
            if (adoptionService.rejectApplication(id)) {
                System.out.println("Application rejected.");
            } else {
                System.out.println("Could not reject application.");
            }
        } else {
            System.out.println("Invalid decision.");
        }
    }
    public static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a number: ");
            sc.nextLine();
        }


        int number = sc.nextInt();
        sc.nextLine();
        return number;
    }
}