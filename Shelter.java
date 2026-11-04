import java.util.ArrayList;

/**
 * Shelter - ดูแลรายการสัตว์ทั้งหมดในศูนย์พักพิง และเก็บรายการคำขอรับเลี้ยง
 * แนวคิด OOP: Objects + Collections (ArrayList)
 *
 * หมายเหตุ: ตอนนี้ทุกคนใช้ชื่อคลาสแม่ว่า First (ในเอกสารคือ Animal)
 * ถ้ากลุ่มตกลงเปลี่ยนเป็น Animal ให้ Find & Replace คำว่า First -> Animal ในไฟล์นี้
 */
public class Shelter {

    private ArrayList<First> animals;
    private ArrayList<AdoptionApplication> applications;

    public Shelter() {
        animals = new ArrayList<>();
        applications = new ArrayList<>();
    }

    // ---------- จัดการสัตว์ ----------

    // เพิ่มสัตว์ (กัน null และกันรหัสซ้ำ)
    public boolean addAnimal(First animal) {
        if (animal == null) {
            System.out.println("Cannot add animal: animal is null.");
            return false;
        }
        if (searchAnimal(String.valueOf(animal.getId())) != null) {
            System.out.println("Cannot add animal: ID " + animal.getId() + " already exists.");
            return false;
        }
        animals.add(animal);
        return true;
    }

    // แสดงสัตว์ทั้งหมด
    public void displayAllAnimals() {
        if (animals.isEmpty()) {
            System.out.println("No animals in the shelter.");
            return;
        }
        System.out.println("===== All Animals =====");
        for (First a : animals) {
            a.displayInfo();
            System.out.println("-----------------------");
        }
    }

    // ค้นหาสัตว์จากรหัสหรือชื่อ (ไม่พบจะ return null)
    // ไม่ print เอง เพื่อให้ Main / AdoptionService เอา object ไปใช้ต่อได้
    public First searchAnimal(String keyword) {
        if (keyword == null) {
            return null;
        }
        String key = keyword.trim();
        for (First a : animals) {
            if (String.valueOf(a.getId()).equalsIgnoreCase(key)
                    || a.getName().equalsIgnoreCase(key)) {
                return a;
            }
        }
        return null;
    }

    // ดึงเฉพาะสัตว์ที่ยังรับเลี้ยงได้ (status = Available)
    public ArrayList<First> getAvailableAnimals() {
        ArrayList<First> available = new ArrayList<>();
        for (First a : animals) {
            if (a.getStatus().equalsIgnoreCase("Available")) {
                available.add(a);
            }
        }
        return available;
    }

    public ArrayList<First> getAnimals() {
        return animals;
    }

    // ข้อมูลสัตว์ตัวอย่าง (Main เรียกครั้งเดียวตอนเริ่มโปรแกรม)
    public void loadSampleData() {
        addAnimal(new Dog(1, "Max", 3, "Male", "Available", "Golden Retriever"));
        addAnimal(new Dog(2, "Luna", 2, "Female", "Adopted", "Poodle"));
        addAnimal(new Cat(3, "Milo", 1, "Male", "Available", true));
        addAnimal(new Cat(4, "Bella", 4, "Female", "Available", false));
    }

    // ---------- จัดการคำขอรับเลี้ยง ----------

    public void addApplication(AdoptionApplication application) {
        if (application != null) {
            applications.add(application);
        }
    }

    public ArrayList<AdoptionApplication> getAllApplications() {
        return applications;
    }

    public void displayAllApplications() {
        if (applications.isEmpty()) {
            System.out.println("No adoption applications.");
            return;
        }
        System.out.println("===== All Applications =====");
        for (AdoptionApplication app : applications) {
            app.displayInfo();
            System.out.println("----------------------------");
        }
    }
}
