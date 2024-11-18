import java.awt.*;

public class AdminTools {

public static void adminMenu(){
    boolean adminMenuRun = true;

    while(adminMenuRun){
        System.out.println("Admin Menu");
        System.out.println("1: Opret 3 TestEvent");
        System.out.println("2: Bruger menu");

        System.out.println("0: Afslut admin menu");
        int thisAdminValg = Main.scanner.nextInt();
        switch (thisAdminValg){
            case 1:
                Main.opretTestEvent();
                break;
            case 2:
                Main.userMenu();
            default:
                adminMenuRun = false;
        }

    }

}
}
