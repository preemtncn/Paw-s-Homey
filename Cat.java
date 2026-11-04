public class Cat extends First {
    private boolean indoorOnly;


    public Cat(int id, String name, int age, String gender, String status, boolean indoorOnly){
        super(id, name, age, gender, status);
        this.indoorOnly = indoorOnly;
    }


    public void makeSound(){
        System.out.println("Meow!");
    }
   
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Indoor Only:" + indoorOnly);
    }
}

