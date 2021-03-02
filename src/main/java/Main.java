import model.Veterinarian;
import repository.VeterinarianDao;
import service.PetClinicService;
import util.HibernateUtil;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PetClinicService.showMenu();
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        System.out.println(option);
        PetClinicService.showOption();
        HibernateUtil.shutdown();
    }
}
