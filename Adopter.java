public class Adopter {
   private String adopterId;
   private String name;
   private String phone;

   public Adopter(String adopterId, String name, String phone) {
       this.adopterId = adopterId;
       this.name = name;
       this.phone = phone;
   }

   public String getAdopterId() {
       return adopterId;
   }

   public String getName() {
       return name;
   }

   public String getPhone() {
       return phone;
   }

   public void displayInfo() {
       System.out.println("Adopter ID: " + adopterId);
       System.out.println("Name: " + name);
       System.out.println("Phone: " + phone);
   }
}
