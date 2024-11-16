public class Event {
    private User[] TilmeldteUsers;
    private User createdByUser;
    private String name;
    private String description;
    private String date;

    public Event(String name, String description, String date,User createdByUser) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.createdByUser = createdByUser;
    }
    public void addUserToTilmeldteUsers(User user) {
        if (TilmeldteUsers != null) {
            for (int i = 0; i < TilmeldteUsers.length; i++) {
                if (TilmeldteUsers[i] != null) {
                    TilmeldteUsers[i] = user;
                    i = TilmeldteUsers.length;
                }
            }
        }else TilmeldteUsers = new User[100];
        TilmeldteUsers[0] = user;

    }

    public void displayEvent() {
        System.out.println("Event:       " + getName());
        System.out.println("Beskrivelse: " + getDescription());
        System.out.println("Dato:        " + getDate());
        System.out.println("Dette event er oprettet af: " + getCreatedByUser().getUserName());


        int tilmeldteUserIndex = 0;
        if (TilmeldteUsers != null) {
            for (int i = 0; i < TilmeldteUsers.length; i++) {
                    tilmeldteUserIndex++;
                    i = TilmeldteUsers.length;
            }
        }
            System.out.println("Der er " + tilmeldteUserIndex + " til " + getName());
        System.out.println(" ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User[] getTilmeldteUsers() {
        return TilmeldteUsers;
    }

    public void setTilmeldteUsers(User[] tilmeldteUsers) {
        TilmeldteUsers = tilmeldteUsers;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }
}
