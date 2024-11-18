import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static EventManeger eventManeger = new EventManeger();

    static Log log = new Log();


    //Login metoden
    public static void login (){
        System.out.println("Indtast Brugernavn");
        String thisUserInputUsername = scanner.next();
        System.out.println("Indtast password");
        String thisUserInputPassword = scanner.next();

        User user = new User(thisUserInputUsername, thisUserInputPassword, "ThisPrivateUser");
        if (!user.getUserName().equals(eventManeger.getAdminUserName())){
            if (eventManeger.auth(user)) {
                log.loginLog(user.getUserName());
                userMenu();
            }else {
                System.out.println("Kunne ikke login");
            }
        }else {
            if (eventManeger.auth(user)) {
                AdminTools.adminMenu();
            }
        }
    }

    public static void clear(){
        for (int i = 0; i < 20; i++){
            System.out.println(" ");
        }
    }

    //Metode til at oprette admin og shillerx profilen ved run()
    public static void opretAdminBruger(){
        User adminUser = new User("admin","admin","admin@admin.com");
        User shillerx = new User("shillerx","qwerty","shillerx@shillerx.com");

        eventManeger.addUserToArray(adminUser);
        eventManeger.addUserToArray(shillerx);
        run();
    }

    public static void opretTestEvent(){
        User adminUser = new User("admin","admin","admin@admin.com");
        Event testEvent = new Event("fællesspisning", "Hyggelig spisning sammen. Tag med hvad du har", "04/12-2024",adminUser);
        Event testEvent2 = new Event("UdflugtTilFrankrig", "busTur til frankrik med hotelophold", "12/01-2025",adminUser);
        Event testEvent3 = new Event("fodbold", "Fælles fodboldt på plænen", "Hver torsdag", adminUser);

        eventManeger.addEventToArray(testEvent);
        eventManeger.addEventToArray(testEvent2);
        eventManeger.addEventToArray(testEvent3);
    }

    public static void register (){
        System.out.println("Indtast brugernavn");
        String thisUsername = scanner.next().toLowerCase();

        System.out.println("Indtast password");
        String thisPassword = scanner.next();

        System.out.println("Hvad er din mail");
        String thisMail = scanner.next();

        User thisUser = new User(thisUsername,thisPassword,thisMail);

        if (eventManeger != null){
            eventManeger.addUserToArray(thisUser);
            log.writeRegisterLogToTxt(eventManeger.getLoggedInUser().getUserName());
        } else System.out.println("Brugeren blev ikke oprette fordi der allerede er en instanse igang");
    }

    public static void skiftEmail(){
        System.out.println("Indtast dit password:");
        String thisPassword = scanner.next();

        if (thisPassword.equals(eventManeger.getLoggedInUser().getPassword())){
            System.out.println("Indtast din nye mail?");
            String thisMail = scanner.next();

            eventManeger.getLoggedInUser().setEmail(thisMail);
            eventManeger.gemLoggedInUserTilArray();
            System.out.println("dine oplysninger er nu gemt. password: "+ eventManeger.getLoggedInUser().getPassword());
            log.editLog(eventManeger.getLoggedInUser().getUserName()+" ændrede E-Mail");
        }
        System.out.println("Forkert PassWord");
    }

    public static void skiftPassword(){
        String thisPass = "  dgr";
        String thisPass1 = "";

        int attempt = 3;
        while (attempt > 0) {
            System.out.println("Indtast dit password:");
            String thisPassword = scanner.next();
            if (thisPassword.equals(eventManeger.getLoggedInUser().getPassword())) {

                System.out.println("indtast dit nye password");
                thisPass = scanner.next();

                System.out.println("gentag dit nye password");
                thisPass1 = scanner.next();
                attempt = 0;

                if (thisPass.equals(thisPass1)) {
                    eventManeger.getLoggedInUser().setPassword(thisPass);
                    eventManeger.gemLoggedInUserTilArray();
                    log.editLog(eventManeger.getLoggedInUser().getUserName()+" ændrede password");
                }else System.out.println("De indtastet input er ikke ens. Adgangskoden blev ikke ændret");
            } else {
                attempt--;
                System.out.println("Forkert PassWord. du har " + attempt + " forsøg tilbage");
            }
        }
    }

    public static void settings(){
        boolean settingsRun = true;
        while(settingsRun){
            System.out.println("Instillinger for: "+ eventManeger.getLoggedInUser().getUserName());
            System.out.println(" ");
            System.out.println("1: ændre password");
            System.out.println("2: ændre E-mail");
            System.out.println("0: gem og afslut instillinger");

            int valg = scanner.nextInt();

            switch (valg){
                case 1:
                    skiftPassword();
                    break;
                case 2:
                    skiftEmail();
                    break;
                default:
                    eventManeger.gemLoggedInUserTilArray();
                    settingsRun = false;
                    break;


            }
        }
    }

    public static void opretEvent(){
        Scanner input = new Scanner(System.in);

        System.out.println("Indtast navn på event");
        String thisNavn = scanner.next();

        System.out.println("indtast dato (dd/mm-yyyy)");
        String thisDate = scanner.next();

        System.out.println("indtast beskrivelse");
        String thisBeskrivelse = input.nextLine();

        Event event = new Event(thisNavn,thisBeskrivelse,thisDate, eventManeger.getLoggedInUser());

        eventManeger.addEventToArray(event);
        log.editLog(eventManeger.getLoggedInUser().getUserName(), " oprettede et nyt event");
    }

    public static void deleteEvent(){
        boolean found = false;
        eventManeger.displayYourOwnEvents(eventManeger.getLoggedInUser());

        System.out.println("Hvilke event vil du slette? (Indtast navnet på dit event)");
        String eventName = scanner.next().toLowerCase();

        for (int i = 0; i < eventManeger.getEvents().length; i++){
            if (eventManeger.getEvents()[i] != null){
                if (eventManeger.getEvents()[i].getName().toLowerCase().equals(eventName)){
                    log.writeEditLogToTxt(eventManeger.getLoggedInUser().getUserName() + " delete " + eventManeger.getEvents()[i].getName());
                    eventManeger.deleteEventFromArray(eventManeger.getEvents()[i]);
                found = true;
                }
            }
        }
        if (!found)System.out.println("Event kunne ikke findes");
    }

    public static void tilmeldTilEvent(){
        boolean found = false;

        eventManeger.displayEvents();
        System.out.println("Hvilke event vil du deltage i?");
        String eventName = scanner.next().toLowerCase();

        for (int i = 0; i < eventManeger.getEvents().length; i++){
            if (eventManeger.getEvents()[i] != null){
                if (eventManeger.getEvents()[i].getName().toLowerCase().equals(eventName)){
                    eventManeger.addUserToEvent(eventManeger.getLoggedInUser(), eventName);
                    found = true;
                }
            }
        }
    }

    public static void redigereUserEvent(){

        int index = 0;
        Event event = new Event(null,null,null,null);
        eventManeger.displayEvents();
        System.out.println("Hvilke event vil du redigere? (Indtast navn på dit event)");
        String eventName = scanner.next().toLowerCase();
        for (int i = 0; i < eventManeger.getEvents().length; i++){
            if (eventManeger.getEvents()[i] != null){
                if (eventManeger.getEvents()[i].getName().toLowerCase().equals(eventName)){
                    event = eventManeger.getEvents()[i];
                    index = i;
                }
            }
        }
        System.out.println("1: navn");
        System.out.println("2: dato");
        System.out.println("Hvad vil du redigere på dit event?");
        int valg = scanner.nextInt();

        if (valg == 1){
            System.out.println("Indtast nyt event navn");
            event.setName(scanner.next());
            log.writeEditLogToTxt(eventManeger.getLoggedInUser().getUserName() + " edit eventName " + event.getName());
        }else if (valg == 2){
            System.out.println("Indtast nyt event dato");
            log.writeEditLogToTxt(eventManeger.getLoggedInUser().getUserName() + " Event date " + event.getDate());
            event.setDate(scanner.next());
        }
        eventManeger.deleteEventFromArray(eventManeger.getEvents()[index]);
        log.writeSystemLogToTxt("System slettede event");
        eventManeger.addEventToArray(event);
        log.writeSystemLogToTxt("System oprettede event");
    }

    public static void userMenu(){
        boolean userMenuRun = true;

        while(userMenuRun){
            System.out.println("velkommen til userMenu " + eventManeger.getLoggedInUser().getUserName());
            System.out.println("1: opret event");
            System.out.println("2: tilmeld event");
            System.out.println("3: redigere mit event");
            System.out.println("4: slet event");
            System.out.println("5: se alle event");
            System.out.println("6: instillinger");
            System.out.println("0: log ud");
            System.out.println(" ");
            System.out.println("Indtast dit valg");

            int userValg = scanner.nextInt();

            switch (userValg){
                case 1:
                    opretEvent();
                    break;
                case 2:
                    tilmeldTilEvent();
                    break;
                case 3:
                    redigereUserEvent();
                    break;
                case 4:
                    deleteEvent();
                    break;
                case 5:
                    eventManeger.displayEvents();
                    break;
                case 6:
                    settings();
                    break;
                default:
                    userMenuRun = false;

            }

        }
    }

    public static void run(){
        boolean run = true;

        while(run){
            System.out.println("velkommen til eventmaneger");
            System.out.println("1: login");
            System.out.println("2: register");
            System.out.println("3: test");
            System.out.println("0: afslut");
            System.out.println("Indtast dit valg");

            int userValg = scanner.nextInt();

            switch (userValg){
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    eventManeger.displayUsers();
                    break;
                case 0:
                    System.out.println("Afslutter program");
                    System.exit(0);
                default:
                    run = false;
                    break;
            }

        }
    }

    public static void main(String[] args) {
        opretAdminBruger();
        run();
    }
}