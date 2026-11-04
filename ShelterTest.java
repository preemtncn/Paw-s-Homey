/**
 * ShelterTest - ทดสอบ Shelter ด้วยข้อมูลสัตว์ตัวอย่าง (งานเสริมของไอซ์)
 */
public class ShelterTest {
    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        shelter.loadSampleData();

        System.out.println("### Test 1: display all animals");
        shelter.displayAllAnimals();

        System.out.println("\n### Test 2: add duplicate ID (should be rejected)");
        boolean added = shelter.addAnimal(new Dog(1, "Rocky", 5, "Male", "Available", "Husky"));
        System.out.println("Result: " + (added ? "added (WRONG!)" : "rejected (OK)"));

        System.out.println("\n### Test 3: search by ID 3 (expect Milo)");
        printSearch(shelter, "3");

        System.out.println("\n### Test 4: search by name 'luna' (case-insensitive, expect Luna)");
        printSearch(shelter, "luna");

        System.out.println("\n### Test 5: search not found (expect not found)");
        printSearch(shelter, "Ghost");
        printSearch(shelter, "99");

        System.out.println("\n### Test 6: available animals (expect Max, Milo, Bella)");
        for (First a : shelter.getAvailableAnimals()) {
            System.out.println("- " + a.getName());
        }

        System.out.println("\n### Test 7: no applications yet");
        shelter.displayAllApplications();
    }

    private static void printSearch(Shelter shelter, String keyword) {
        First result = shelter.searchAnimal(keyword);
        if (result == null) {
            System.out.println("Not found: " + keyword);
        } else {
            System.out.println("Found: " + result.getName());
        }
    }
}
