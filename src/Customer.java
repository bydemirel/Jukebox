import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.util.ArrayList;

public class Customer {
    private int ID;
    private String userName;
    private double budget;
    private ArrayList<Song> song_list;
    private PaymentType paymentType;
    private boolean membership;

    public Customer(int ID, String userName) {
        this.ID = ID;
        this.userName = userName;
        song_list = new ArrayList<>();
        this.membership = false;
    }

    void addSong(Song song)
    {
        song_list.add(song);
    }

    boolean removeSong(Song song)
    {
        return song_list.remove(song);
    }

    Song findSong(Song song)
    {
        for (int i = 0; i < song_list.size(); i++)
        {
            if(song_list.get(i).equals(song))
            {
                return song_list.get(i);
            }
        }

        return null;

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public ArrayList<Song> getSong_list() {
        return song_list;
    }

    public void setSong_list(ArrayList<Song> song_list) {
        this.song_list = song_list;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public boolean isMembership() {
        return membership;
    }

    public void setMembership(boolean membership) {
        this.membership = membership;
    }
}
