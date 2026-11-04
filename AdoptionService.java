/**
 * AdoptionService (คนที่ 7) - จัดการกระบวนการรับเลี้ยง
 * เชื่อม "คำขอ" (AdoptionApplication) กับ "สถานะสัตว์" (First) เข้าด้วยกัน
 *
 * หมายเหตุ: ตอนนี้ทุกคนใช้ชื่อคลาสแม่ว่า First (ในเอกสารคือ Animal)
 * ถ้ากลุ่มเปลี่ยนเป็น Animal ให้ Find & Replace คำว่า First -> Animal ในไฟล์นี้
 *
 * กฎของระบบ
 *  1. ยื่นคำขอได้เฉพาะสัตว์ที่ยัง Available
 *  2. อนุมัติคำขอแล้ว สัตว์เปลี่ยนเป็น Adopted
 *  3. สัตว์ที่ Adopted แล้วต้องไม่ถูกรับเลี้ยงซ้ำ
 *  4. ปฏิเสธคำขอแล้ว สัตว์ยัง Available (ไม่แตะสถานะสัตว์)
 */
public class AdoptionService {

    private static final String AVAILABLE = "Available";
    private static final String ADOPTED = "Adopted";
    private static final String PENDING = "Pending";

    private final Shelter shelter;
    private int nextApplicationNumber = 1;

    public AdoptionService(Shelter shelter) {
        this.shelter = shelter;
    }

    // รับคำขอใหม่ คืน null ถ้ายื่นไม่ได้ (ไม่พบสัตว์ / สัตว์ไม่ว่าง / ไม่มีผู้ยื่น)
    public AdoptionApplication submitApplication(Adopter adopter, First animal) {
        if (adopter == null || animal == null) {
            return null;
        }
        // กฎข้อ 1 และ 3: ยื่นได้เฉพาะสัตว์ที่ยัง Available
        if (!animal.getStatus().equalsIgnoreCase(AVAILABLE)) {
            return null;
        }

        String id = String.format("APP%03d", nextApplicationNumber++);
        AdoptionApplication application = new AdoptionApplication(id, adopter, animal);
        shelter.addApplication(application);
        return application;
    }

    // อนุมัติคำขอ คืน true ถ้าอนุมัติสำเร็จ
    public boolean approveApplication(String applicationId) {
        AdoptionApplication application = findApplication(applicationId);
        if (application == null) {
            return false;
        }
        // ตรวจแล้วห้ามตรวจซ้ำ
        if (!application.getStatus().equalsIgnoreCase(PENDING)) {
            return false;
        }
        // กฎข้อ 3: สัตว์ที่ Adopted ไปแล้ว (เช่น คำขออื่นอนุมัติไปก่อน) อนุมัติซ้ำไม่ได้
        First animal = application.getAnimal();
        if (!animal.getStatus().equalsIgnoreCase(AVAILABLE)) {
            return false;
        }

        application.approve();
        animal.setStatus(ADOPTED); // กฎข้อ 2
        return true;
    }

    // ปฏิเสธคำขอ คืน true ถ้าปฏิเสธสำเร็จ
    public boolean rejectApplication(String applicationId) {
        AdoptionApplication application = findApplication(applicationId);
        if (application == null) {
            return false;
        }
        if (!application.getStatus().equalsIgnoreCase(PENDING)) {
            return false;
        }

        // กฎข้อ 4: คำขอที่ยัง Pending ไม่เคยเปลี่ยนสถานะสัตว์ จึงไม่ต้องแตะสถานะสัตว์
        // (ถ้ามีคำขออื่นอนุมัติไปแล้ว สัตว์จะยังเป็น Adopted ต่อไปเอง)
        application.reject();
        return true;
    }

    // ค้นหาคำขอจากรหัส คืน null ถ้าไม่พบ
    public AdoptionApplication findApplication(String applicationId) {
        if (applicationId == null) {
            return null;
        }
        String key = applicationId.trim();
        for (AdoptionApplication application : shelter.getAllApplications()) {
            if (application.getApplicationId().equalsIgnoreCase(key)) {
                return application;
            }
        }
        return null;
    }
}