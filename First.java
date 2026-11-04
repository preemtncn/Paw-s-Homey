public class First {


        private int id;
        private String name;
        private int age;
        private String gender;
        private String status;
    public First (int id, String name, int age, String gender, String status){
        this.id = id;
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.status = status;
    }
    public int getId(){
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getGender() {
        return gender;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void displayInfo(){
        System.out.println("ID:" + id);
        System.out.println("Name:" + name);
        System.out.println("Age:" + age);
        System.out.println("Gender:" + gender);
        System.out.println("Status:" + status);
    }
}

