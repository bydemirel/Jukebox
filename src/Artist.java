

public class Artist {
    private long ID;
    private String name;



    public Artist(long ID, String name) {
        this.ID = ID;
        this.name = name;

    }



    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
