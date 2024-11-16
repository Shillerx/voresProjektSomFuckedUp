public class EventManeger {
    private Event [] events = new Event[100];
    private User [] users = new User[100];
    private User loggedInUser = null;
    int usersArrayIndex = 0;
    int eventsArrayIndex = 0;

    public EventManeger() {}

    public void addUserToEvent(User user, String sEvent) {

        for (int i = 0; i < events.length; i++) {
            if (events[i] != null) {
                if (events[i].getName().equals(sEvent)) {
                    events[i].addUserToTilmeldteUsers(user);
                    i = eventsArrayIndex;
                }
            }
        }
    }
    public void addEventTilArray(Event e){
        events[eventsArrayIndex] = e;
        eventsArrayIndex++;
    }

    public boolean auth(User u) {
        boolean authenticated = false;

        for (User user : users) {
            if (user != null) {
                if (user.getUserName().equals(u.getUserName())) {
                    if (user.getPassword().equals(u.getPassword())) {
                        loggedInUser = u;
                        authenticated = true;

                    }
                }
            }
        }

        return authenticated;
    }

    public void gemLoggedInUserTilArray() {
        int index = findBrugerensPladsIArray(loggedInUser);
        users[index] = loggedInUser;
    }
    public int findBrugerensPladsIArray(User u) {
        boolean found = false;
        int plads = 0;

        while (!found) {
            for (int i = 0; i < users.length; i++) {
                if (users[i] != null) {
                    if (u.getUserName().equals(users[i].getUserName())) {
                        found = true;
                        plads = i;
                    }
                }
            }
        }
        return plads;
    }

    public void addUserToArray(User user) {
        users[usersArrayIndex] = user;
        usersArrayIndex++;
    }
    public void addEventToArray(Event event) {
        events[eventsArrayIndex] = event;
        eventsArrayIndex++;
    }
    public void displayYourOwnEvents(User u) {
        for (int i = 0; i < events.length; i++) {
            if (events[i] != null) {
                if (events[i].getCreatedByUser().getUserName().equals(u.getUserName())){
                    events[i].displayEvent();
                }

            }
        }
    }
    public void displayEvents(){
        for (int i = 0; i < events.length; i++) {
            if (events[i] != null) {
                events[i].displayEvent();
            }
        }
    }
    public void displayUsers() {
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                System.out.println(users[i].getUserName());
                System.out.println(users[i].getPassword());
                System.out.println(users[i].getEmail());
            }
        }
    }
    public void deleteEventFromArray(Event event) {
        boolean found = false;

        for (int i = 0; i < events.length; i++) {
            if (events[i] != null) {
                if (events[i].equals(event)) {
                    found = true;
                    events[i] = null;
                }
            }
        }
        if (found) {
            System.out.println("Event deleted from the array");
        }else {
            System.out.println("Event not found");
        }
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
    public String getAdminUserName() {
        String adminInfo = users[0].getUserName();
        return adminInfo;
    }
}
