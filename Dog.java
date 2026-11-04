public class Dog extends First {
    private String breed;


    public Dog(int id, String name, int age, String gender, String status, String breed){
        super(id, name, age, gender, status);
        this.breed = breed;
    }
   
    public void makeSound(){
        System.out.println("Woof!");
    }


    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Breed: " + breed);
    }
}
