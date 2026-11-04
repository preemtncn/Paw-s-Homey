public class AdoptionApplication {
    private String applicationId;
    private Adopter adopter;
    private First animal;
    private String status;          // Pending / Approved / Rejected


    // ATTRIBUTE
    public AdoptionApplication(String applicationId, Adopter adopter, First animal) {
        this.applicationId = applicationId;
        this.adopter = adopter;
        this.animal = animal;
        this.status = "Pending";     // requests always start as Pending
    }


    // METHOD
    public void approve() {
        this.status = "Approved";
    }


    public void reject() {
        this.status = "Rejected";
    }


    public String getStatus() {
        return status;
    }


    // can be called by AdoptionService.
    public String getApplicationId() {
        return applicationId;
    }


    public Adopter getAdopter() {
        return adopter;
    }


    public First getAnimal() {
        return animal;
    }


    public void displayInfo() {
        System.out.println("Application ID: " + applicationId);
        System.out.println("Adopter: " + adopter.getName());
        System.out.println("Animal: " + animal.getName());
        System.out.println("Status: " + status);
    }
}


